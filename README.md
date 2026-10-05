# Software Development Course Labs

Java exercises for the software development course.

- `Lab2-Java/`: individual exercises and the arrays project.
- `Lab3-Java/src/`: exercises `ex1` through `ex10`, each with a `Main` class.
- `Lab4-Java/challenge/`: Lab 4 Gradle project, imported from
  [Lab4-problems-](https://github.com/fahd-kalloubi/Lab4-problems-).

## Run the exercises

Open the repository root in IntelliJ, select JDK 17, let the arrays and Lab 4
Gradle projects sync, and run the exercise's `main` method. The shared `.idea`
settings and `.iml` files keep the labs registered as separate modules.

From a terminal at the repository root, for example:

```bash
mkdir -p out/Lab3
javac -d out/Lab3 Lab3-Java/src/ex10/*.java
java -cp out/Lab3 ex10.Main
```

Replace `ex10` with the exercise you want. Generated class files and build folders
are ignored by Git. Both Gradle projects keep their wrappers so they can also
be opened and built on their own. For Lab 4, open `Lab4-Java/challenge` or run:

```bash
cd Lab4-Java/challenge
./gradlew build
```

To add another lab, create a Java module in IntelliJ and commit its `src` folder,
`.iml` file, and the updated `.idea/modules.xml`.

## Save and push

```bash
git add .
git commit -m "Update exercises"
git push
```

The remotes are:

- `origin`: [the course repository](https://github.com/I-l-y-a-Z-z/Software-Development-Course-Labs), branch `main`.
- `lab2-fork`: [the arrays fork](https://github.com/I-l-y-a-Z-z/arrays-cs-project), branch `master`.
- `lab4-fork`: [the Lab 4 fork](https://github.com/I-l-y-a-Z-z/Lab4-Java), branch `main`.

The `.githooks/pre-push` hook is enabled on this device. When you push an update
to `origin/main`, it first sends each committed lab folder to its fork using Git
subtree: `Lab2-Java/arrays-cs-project` to `lab2-fork/master`, then `Lab4-Java` to
`lab4-fork/main`. Each folder becomes the root of its standalone fork. If a lab
has no new commits, its fork is already up to date. Git hooks must be enabled in
your Git client; `--no-verify` skips synchronization. Other branches do not sync
the forks.

If either fork push fails, the course push stops. These pushes are sequential,
so a fork can succeed before a later push fails; fix the error and retry. The
hook never force-pushes the forks. Never push the full course branch directly
to `lab2-fork` or `lab4-fork`.

## On a fresh clone

Install Git with `git subtree` support, configure your Git name/email and GitHub
authentication, then run:

```bash
git clone https://github.com/I-l-y-a-Z-z/Software-Development-Course-Labs.git
cd Software-Development-Course-Labs
git remote add lab2-fork https://github.com/I-l-y-a-Z-z/arrays-cs-project.git
git remote add lab4-fork https://github.com/I-l-y-a-Z-z/Lab4-Java.git
git fetch lab2-fork
git fetch lab4-fork
git config --local remote.pushDefault origin
git config --local core.hooksPath .githooks
```

Repeat this setup for each clone. When switching devices, push before leaving
one device and run `git pull --ff-only` on the other before editing.

## Fork updates and push previews

To bring changes made directly in the arrays fork into the course, first commit
or stash your work, then run:

```bash
git subtree split --prefix=Lab2-Java/arrays-cs-project --rejoin
git subtree pull --prefix=Lab2-Java/arrays-cs-project lab2-fork master
git push
```

For changes made directly in the Lab 4 fork, use:

```bash
git subtree split --prefix=Lab4-Java --rejoin
git subtree pull --prefix=Lab4-Java lab4-fork main
git push
```

If a subtree pull reports conflicts, resolve them, stage the resolved files,
and run `git commit --no-edit` before pushing. This preserves the subtree merge
message used to track the fork's history.

To send changes to either fork manually:

```bash
git subtree push --prefix=Lab2-Java/arrays-cs-project lab2-fork master
git subtree push --prefix=Lab4-Java lab4-fork main
```

Git runs pre-push hooks even during `--dry-run`. Use this command to preview a
course push without updating any repository:

```bash
git -c core.hooksPath=/dev/null push --dry-run origin main
```
