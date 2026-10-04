"""Exercise real pushes to disposable local remotes; never contact GitHub."""

import os
from pathlib import Path
import shutil
import subprocess
import tempfile
import unittest


PROJECT = Path(__file__).resolve().parents[1]
PREFIX = "Lab2-Java/arrays-cs-project"


class PushSyncTests(unittest.TestCase):
    def setUp(self):
        temporary = tempfile.TemporaryDirectory(prefix="course-push-test-")
        self.addCleanup(temporary.cleanup)
        self.root = Path(temporary.name)
        self.repo = self.root / "course"
        self.origin = self.root / "origin.git"
        self.fork = self.root / "fork.git"
        seed = self.root / "seed"
        for bare, branch in ((self.origin, "main"), (self.fork, "master")):
            self.git("init", "--bare", "--initial-branch=" + branch, str(bare), cwd=self.root)
        for repo, branch in ((seed, "master"), (self.repo, "main")):
            self.git("init", "--initial-branch=" + branch, str(repo), cwd=self.root)
            self.git("config", "user.name", "Sync Test", cwd=repo)
            self.git("config", "user.email", "sync-test@example.invalid", cwd=repo)
            self.git("config", "commit.gpgSign", "false", cwd=repo)
            self.git("config", "core.hooksPath", ".githooks", cwd=repo)
        (seed / "arrays.txt").write_text("initial arrays\n")
        self.git("add", ".", cwd=seed)
        self.git("commit", "-m", "Initial arrays", cwd=seed)
        self.base_fork = self.git("rev-parse", "HEAD", cwd=seed).stdout.strip()
        self.git("push", str(self.fork), "master", cwd=seed)
        self.commit("course.txt", "initial course\n")
        self.git("remote", "add", "origin", str(self.origin))
        self.git("remote", "add", "lab2-fork", str(self.fork))
        self.git("subtree", "add", "--prefix=" + PREFIX, "lab2-fork", "master")
        hooks = self.repo / ".githooks"
        hooks.mkdir()
        shutil.copyfile(PROJECT / ".githooks/pre-push", hooks / "pre-push")
        (hooks / "pre-push").chmod(0o755)
        self.git("add", ".githooks")
        self.git("commit", "-m", "Enable fork synchronization")

    def git(self, *args, cwd=None, ok=True):
        result = subprocess.run(
            ["git", *args], cwd=cwd or self.repo, text=True,
            stdout=subprocess.PIPE, stderr=subprocess.PIPE,
            env={**os.environ, "GIT_TERMINAL_PROMPT": "0"}, timeout=30,
        )
        if ok and result.returncode:
            self.fail(f"git {' '.join(args)} failed:\n{result.stdout}{result.stderr}")
        return result

    def commit(self, relative, content):
        path = self.repo / relative
        path.parent.mkdir(parents=True, exist_ok=True)
        path.write_text(content)
        self.git("add", relative)
        self.git("commit", "-m", "Update " + relative)
        return self.git("rev-parse", "HEAD").stdout.strip()

    def revision(self, repo, ref):
        return self.git("rev-parse", ref, cwd=repo).stdout.strip()

    def assert_synced(self, commit):
        self.assertEqual(self.revision(self.origin, "main"), commit)
        self.assertEqual(
            self.revision(self.fork, "master^{tree}"),
            self.revision(self.repo, commit + ":" + PREFIX),
        )
        self.git("merge-base", "--is-ancestor", self.base_fork, "master", cwd=self.fork)

    def test_first_push_and_plain_push_publish_only_committed_arrays(self):
        commit = self.commit(PREFIX + "/arrays.txt", "first update\n")
        self.git("push", "-u", "origin", "main")
        self.assert_synced(commit)
        commit = self.commit(PREFIX + "/arrays.txt", "second update\n")
        (self.repo / PREFIX / "arrays.txt").write_text("uncommitted edit\n")
        self.git("add", PREFIX + "/arrays.txt")
        (self.repo / "course.txt").write_text("unstaged course edit\n")
        before = self.git("status", "--porcelain").stdout
        self.git("push")
        self.assert_synced(commit)
        self.assertEqual(before, self.git("status", "--porcelain").stdout)
        self.assertEqual(commit, self.revision(self.repo, "HEAD"))
        self.assertEqual(
            self.git("show", "master:arrays.txt", cwd=self.fork).stdout,
            "second update\n",
        )
        self.assertEqual(self.git("ls-tree", "--name-only", "master", cwd=self.fork).stdout,
                         "arrays.txt\n")

    def test_course_only_update_leaves_fork_commit_unchanged(self):
        self.git("push", "origin", "main")
        before = self.revision(self.fork, "master")
        commit = self.commit("course.txt", "course-only update\n")
        self.git("push", "origin", "main")
        self.assert_synced(commit)
        self.assertEqual(before, self.revision(self.fork, "master"))

    def test_push_uses_selected_commit_instead_of_checked_out_head(self):
        selected = self.commit(PREFIX + "/arrays.txt", "publish this\n")
        self.git("switch", "-c", "experiment")
        self.commit(PREFIX + "/arrays.txt", "do not publish this\n")
        head = self.revision(self.repo, "HEAD")
        self.git("push", "origin", "main")
        self.assert_synced(selected)
        self.assertEqual(head, self.revision(self.repo, "HEAD"))

    def test_feature_branches_tags_and_deletion_do_not_sync(self):
        self.git("push", "origin", "main")
        before = self.revision(self.fork, "master")
        self.commit(PREFIX + "/arrays.txt", "unpublished arrays\n")
        self.git("tag", "test-tag")
        self.git("push", "origin", "HEAD:refs/heads/experiment", "refs/tags/test-tag")
        self.git("symbolic-ref", "HEAD", "refs/heads/experiment", cwd=self.origin)
        self.git("push", "origin", ":refs/heads/main")
        self.assertEqual(before, self.revision(self.fork, "master"))

    def test_fork_rejection_blocks_even_forced_course_push(self):
        self.git("push", "origin", "main")
        before = self.revision(self.origin, "main")
        seed = self.root / "seed"
        (seed / "arrays.txt").write_text("independent fork update\n")
        self.git("add", ".", cwd=seed)
        self.git("commit", "-m", "Independent fork update", cwd=seed)
        self.git("push", str(self.fork), "master", cwd=seed)
        fork_commit = self.revision(self.fork, "master")
        self.commit(PREFIX + "/arrays.txt", "conflicting course update\n")
        result = self.git("push", "--force", "origin", "main", ok=False)
        self.assertNotEqual(result.returncode, 0)
        self.assertIn("fork push failed", result.stderr)
        self.assertEqual(before, self.revision(self.origin, "main"))
        self.assertEqual(fork_commit, self.revision(self.fork, "master"))

    def test_missing_fork_remote_blocks_course_push(self):
        self.git("remote", "remove", "lab2-fork")
        result = self.git("push", "origin", "main", ok=False)
        self.assertNotEqual(result.returncode, 0)
        self.assertIn("missing lab2-fork", result.stderr)
        self.assertNotEqual(self.git("rev-parse", "--verify", "main", cwd=self.origin,
                                     ok=False).returncode, 0)

    def test_missing_arrays_folder_blocks_course_push(self):
        self.git("rm", "-r", PREFIX)
        self.git("commit", "-m", "Remove arrays")
        result = self.git("push", "origin", "main", ok=False)
        self.assertNotEqual(result.returncode, 0)
        self.assertIn("missing from the commit", result.stderr)
        self.assertEqual(self.base_fork, self.revision(self.fork, "master"))

    def test_safe_dry_run_changes_neither_remote(self):
        self.commit(PREFIX + "/arrays.txt", "preview only\n")
        self.git("-c", "core.hooksPath=/dev/null", "push", "--dry-run", "origin", "main")
        self.assertEqual(self.base_fork, self.revision(self.fork, "master"))
        self.assertNotEqual(self.git("rev-parse", "--verify", "main", cwd=self.origin,
                                     ok=False).returncode, 0)

    def test_course_rejection_can_be_retried_after_fork_succeeds(self):
        self.git("push", "origin", "main")
        previous = self.revision(self.origin, "main")
        commit = self.commit(PREFIX + "/arrays.txt", "retryable update\n")
        rejection = self.origin / "hooks/pre-receive"
        rejection.write_text("#!/bin/sh\nexit 1\n")
        rejection.chmod(0o755)
        result = self.git("push", "origin", "main", ok=False)
        self.assertNotEqual(result.returncode, 0)
        self.assertEqual(previous, self.revision(self.origin, "main"))
        fork_commit = self.revision(self.fork, "master")
        self.assertEqual(self.revision(self.fork, "master^{tree}"),
                         self.revision(self.repo, commit + ":" + PREFIX))
        rejection.unlink()
        self.git("push", "origin", "main")
        self.assert_synced(commit)
        self.assertEqual(fork_commit, self.revision(self.fork, "master"))

    def test_follow_tags_does_not_send_course_tags_to_fork(self):
        self.git("config", "push.followTags", "true")
        self.git("-c", "tag.gpgSign=false", "tag", "-a", "course-tag", "-m", "Course tag")
        commit = self.commit(PREFIX + "/arrays.txt", "tagged course update\n")
        self.git("push", "origin", "main")
        self.assert_synced(commit)
        self.assertEqual(self.git("tag", "--list", cwd=self.fork).stdout, "")
        self.assertEqual(self.git("tag", "--list", cwd=self.origin).stdout, "course-tag\n")

    def test_fork_updates_can_be_pulled_and_pushed_again(self):
        self.commit(PREFIX + "/arrays.txt", "local arrays update\n")
        self.git("push", "origin", "main")
        seed = self.root / "seed"
        self.git("pull", "--ff-only", str(self.fork), "master", cwd=seed)
        (seed / "new-exercise.txt").write_text("fork exercise\n")
        self.git("add", ".", cwd=seed)
        self.git("commit", "-m", "Add fork exercise", cwd=seed)
        self.git("push", str(self.fork), "master", cwd=seed)
        self.git("subtree", "split", "--prefix=" + PREFIX, "--rejoin")
        self.git("subtree", "pull", "--prefix=" + PREFIX, "lab2-fork", "master")
        self.commit(PREFIX + "/arrays.txt", "update after merging fork\n")
        commit = self.revision(self.repo, "HEAD")
        self.git("push", "origin", "main")
        self.assert_synced(commit)
        self.assertEqual(self.git("show", "master:new-exercise.txt", cwd=self.fork).stdout,
                         "fork exercise\n")


if __name__ == "__main__":
    unittest.main()
