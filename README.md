# Software Development Course Labs

Java coursework in one Git repository and one IntelliJ IDEA project. Each lab or
existing exercise project has its own module, so exercises can be built and run
independently.

The arrays project remains connected to
[my Lab 2 fork](https://github.com/I-l-y-a-Z-z/arrays-cs-project), which is forked
from [the professor's repository](https://github.com/fahd-kalloubi/arrays-cs-project).
It is included here as a **Git subtree**: its actual files live in this repository,
and Git can send just that folder's changes back to the fork. A normal clone of
the course repository includes every lab; no submodule setup is needed.

## Open and run in IntelliJ IDEA

1. Open **this repository's root folder** (`Software Development Course Labs`).
2. Use **JDK 17** as the project SDK under **File → Project Structure → Project**.
   The saved SDK name is `17 (3)`, matching the original machine's OpenJDK 17.
   On another machine, select its installed JDK 17 if IntelliJ shows an SDK warning.
3. Allow the linked `Lab2-Java/arrays-cs-project` Gradle project to sync. Its wrapper
   supplies Gradle 8.14; the first sync may need internet access. The Gradle JVM
   should use **Project SDK**.
4. Open a Java class with a `main` method and click the green Run arrow beside it.
   For example, run `Lab3-Java/src/ex1/Main.java` or
   `Lab2-Java/SpeedConverter/src/SpeedConverter.java`.

The plain Java modules are already registered in `.idea/modules.xml`. IntelliJ
generates the arrays project's modules during Gradle sync. If that project is not
shown in the Gradle window, right-click its `build.gradle.kts` and select
**Import Gradle Project**.

IntelliJ's own builder is selected for the linked Gradle project so the plain Java
modules and the Gradle modules can be built together using **Build → Build Project**.
Gradle tasks remain available in the Gradle tool window.

## Layout

```text
.
├── .idea/                       Shared course project settings
├── Lab2-Java/
│   ├── EqualSumChecker/         Plain Java module
│   ├── LeapYearCalculator/      Plain Java module
│   ├── SpeedConverter/          Plain Java module
│   ├── TeenNumberChecker/       Plain Java module
│   └── arrays-cs-project/       Linked Gradle project + Git subtree
│       └── src/main/java/samplearrays/
└── Lab3-Java/                   Plain Java module
    ├── Lab3.iml
    └── src/
        ├── ex1/
        ├── ex2/
        ├── ex3/
        └── ex4/
```

Lab 3 exercise 4 currently contains an unfinished `ManageMatrix.copy` method and
has no `main` method. It compiles, but it is not a runnable exercise yet.

## Add the next lab

1. In the root IntelliJ project, create a **New Module** using **Java**, the
   **IntelliJ** build system, and **Project SDK**.
2. For Lab 4, use the module name `Lab4` and set its location to
   `<repository>/Lab4-Java`.
3. Put exercises in packages such as `ex1`, `ex2`, and `ex3` under that module's
   `src` folder. If necessary, right-click `src` and choose
   **Mark Directory as → Sources Root**.
4. Commit the new lab's sources and `.iml` file together with the updated
   `.idea/modules.xml`.

Use one module per new lab. Modules do not need dependencies on earlier labs.
Repeated class names in different labs remain separate when you run each class
with its own module's classpath. Initialize Git only at the course root.

## Connect the course repository to GitHub

The two GitHub repositories serve different purposes:

| Remote | Repository | Branch | What it receives |
| --- | --- | --- | --- |
| `origin` | New `software-development-course-labs` repository | `main` | Every lab and the shared course setup |
| `lab2-fork` | Existing `I-l-y-a-Z-z/arrays-cs-project` fork | `master` | Only `Lab2-Java/arrays-cs-project`, exported with Git subtree |

Create an **empty**, separate GitHub repository named
`software-development-course-labs` under `I-l-y-a-Z-z` (without an initial README,
license, or `.gitignore`). From the course root, connect it and publish:

```bash
git remote add origin https://github.com/I-l-y-a-Z-z/software-development-course-labs.git
git push -u origin main
```

Use a different URL if you choose a different course repository name.
Keep `origin` pointed at the course repository. The existing fork retains its
standalone layout and its relationship with the professor's repository.

### After a normal lab update

```bash
git status
git add .
git diff --cached --stat
git commit -m "Add Lab 4 exercises"
git push origin main
```

The shared IntelliJ configuration and plain Java `.iml` files are versioned.
Generated files, Gradle caches, personal IDE settings, and local backups are
ignored. Keep the Gradle wrapper scripts and wrapper JAR in Git.

### Automatic synchronization with the Lab 2 fork

The versioned `.githooks/pre-push` [Git hook](https://git-scm.com/docs/githooks#_pre_push)
is enabled in this working copy. Whenever
you push an update to `origin/main`, it first exports the arrays folder from the
exact commit being pushed and pushes that export to `lab2-fork/master`. This
also runs on the first course push. Commit your edits, then push normally:

```bash
git add Lab2-Java/arrays-cs-project
git commit -m "Update Lab 2 array exercises"
git push origin main
```

After the initial `git push -u origin main`, plain `git push` works too. Git clients
such as IntelliJ also use the hook when Git hooks are enabled. Only committed
files are published; uncommitted edits are left alone. The fork keeps its standalone layout
with `src`, `build.gradle.kts`, and the other arrays files at its root. The hook
does not change your branch or working files, and other branches and tags do not
trigger synchronization. Commit arrays changes separately when possible so their
commit messages also make sense in the fork.

If the fork push fails (for example, authentication fails or the fork has newer
commits), the course push stops. Bring newer fork commits into the course using
the instructions below, then retry. The hook never force-pushes the fork. The two
pushes are separate: if the fork succeeds but the course push subsequently fails,
fix the course push error and retry the same command.

This automation runs locally for pushes to the remote named `origin`; edits made
on GitHub or pushes from another clone without the hook do not run it. Enable it
in each fresh clone using the setup below. Avoid `git push --no-verify` for normal
publishing because that skips the hook.

Git also invokes pre-push hooks during `--dry-run` without telling the hook that
it is a dry run. To preview a push without updating either repository, use:

```bash
git -c core.hooksPath=/dev/null push --dry-run origin main
```

To rerun fork synchronization manually, including when the course branch is
already up to date on GitHub, use:

```bash
git subtree push --prefix=Lab2-Java/arrays-cs-project lab2-fork master
```

**Use the subtree command to update the fork.** A regular `git push lab2-fork
main:master` would send the entire course layout to it. The hook handles the
subtree export automatically during normal course pushes.

### Bring updates from the fork into the course

With a clean working tree, run:

```bash
git subtree split --prefix=Lab2-Java/arrays-cs-project --rejoin
git subtree pull --prefix=Lab2-Java/arrays-cs-project lab2-fork master
git push origin main
```

The split records any local arrays commits before merging changes from the fork.
If you also need changes from the professor, use GitHub's **Sync fork** on your
existing fork first, then run these commands. Resolve any merge conflicts before
pushing. If the subtree pull is in conflict and you want to cancel it, use
`git merge --abort`.

### On a fresh clone

Cloning the course repository gives you all the files and the subtree history.
Git only creates the `origin` remote automatically, so add the fork remote once:

```bash
git remote add lab2-fork https://github.com/I-l-y-a-Z-z/arrays-cs-project.git
git config remote.pushDefault origin
git fetch lab2-fork
git config --local core.hooksPath .githooks
```

These settings are already configured in the original working folder. The push
default keeps ordinary pushes directed at the course repository. The hook and
manual subtree commands require Git's `subtree` command to be installed and
permission to push to both repositories.

The hook's integration checks use temporary local repositories and never contact
GitHub. Run them with
`PYTHONDONTWRITEBYTECODE=1 python3 -m unittest discover -s tests -v`.

## Build outside IntelliJ

With JDK 17 available, the arrays project can be built from the course root:

```bash
./Lab2-Java/arrays-cs-project/gradlew -p Lab2-Java/arrays-cs-project build
```

For example, compile and run Lab 3 from the course root on Linux/macOS:

```bash
mkdir -p out/Lab3
javac --release 17 -d out/Lab3 Lab3-Java/src/ex*/*.java
java -cp out/Lab3 ex1.Main
```

## Existing history and local backups

The arrays project's original commits are retained as ancestors of the course
repository's `main` branch. The course setup commit relocates its files under
`Lab2-Java/arrays-cs-project` and adds the other labs. Use `git log --follow -- path`
to inspect a file across the move.

A subsequent subtree registration joins the export history to the original fork
commit, so Lab 2 updates can be pushed without rewriting the fork's history.
Use `git log --first-parent --oneline` to see the course's main line of commits.

On the original machine, `.local-backups/` also contains a complete Git bundle,
the arrays project's previous Git metadata, and the previous per-project IntelliJ
settings. These backups stay local and are excluded from GitHub.
