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

### Send Lab 2 changes to the existing fork

Edit the arrays project in its current folder. Commit its changes separately so
the commit message also makes sense in the fork:

```bash
git add Lab2-Java/arrays-cs-project
git commit -m "Update Lab 2 array exercises"
git subtree push --prefix=Lab2-Java/arrays-cs-project --rejoin lab2-fork master
git push origin main
```

Commit or stash any other pending changes before the subtree command. The subtree
push exports only the arrays project, with `src`, `build.gradle.kts`, and the other
project files at the fork's root. `--rejoin` records the synchronization in the
course history; the final `git push origin main` also publishes that record.

**Use the subtree command to update the fork.** A regular `git push lab2-fork
main:master` would send the entire course layout to it. Synchronization is manual:
publishing the course repository alone does not update the fork.

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
```

These settings are already configured in the original working folder. The push
default keeps ordinary pushes directed at the course repository. The subtree
commands require Git's `subtree` command to be installed.

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
