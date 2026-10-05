# Software Development Course Labs

Java exercises for the software development course.

- `Lab2-Java/`: individual exercises and the arrays project.
- `Lab3-Java/src/`: exercises `ex1` through `ex10`, each with a `Main` class.

## Run the exercises

Open the repository root in IntelliJ, select JDK 17, let the arrays Gradle project
sync, and run the exercise's `Main` class. The shared `.idea` settings and `.iml`
files keep the labs registered as separate modules.

From a terminal at the repository root, for example:

```bash
mkdir -p out/Lab3
javac -d out/Lab3 Lab3-Java/src/ex10/*.java
java -cp out/Lab3 ex10.Main
```

Replace `ex10` with the exercise you want. Generated class files and build folders
are ignored by Git. The arrays project keeps its Gradle wrapper so it can also
be opened and built on its own.

To add another lab, create a Java module in IntelliJ and commit its `src` folder,
`.iml` file, and the updated `.idea/modules.xml`.

## Save and push

```bash
git add .
git commit -m "Update exercises"
git push
```

The two remotes are:

- `origin`: [the course repository](https://github.com/I-l-y-a-Z-z/Software-Development-Course-Labs), branch `main`.
- `lab2-fork`: [the arrays fork](https://github.com/I-l-y-a-Z-z/arrays-cs-project), branch `master`.

The `.githooks/pre-push` hook is enabled on this device. When you push an update
to `origin/main`, it first sends only the committed `Lab2-Java/arrays-cs-project`
folder to the fork using Git subtree. Git hooks must be enabled in your Git
client; `--no-verify` skips synchronization. Other branches do not sync the fork.

If the fork push fails, the course push stops. If the fork succeeds but the
course push fails, fix the error and retry. The hook never force-pushes the fork.
Never push the full course branch directly to `lab2-fork`.

## On a fresh clone

Install Git with `git subtree` support, configure your Git name/email and GitHub
authentication, then run:

```bash
git clone https://github.com/I-l-y-a-Z-z/Software-Development-Course-Labs.git
cd Software-Development-Course-Labs
git remote add lab2-fork https://github.com/I-l-y-a-Z-z/arrays-cs-project.git
git fetch lab2-fork
git config --local remote.pushDefault origin
git config --local core.hooksPath .githooks
```

Repeat this setup for each clone. When switching devices, push before leaving
one device and run `git pull --ff-only` on the other before editing.

## Fork updates and push previews

To bring changes made directly in the fork into the course, first commit or stash
your work, then run:

```bash
git subtree split --prefix=Lab2-Java/arrays-cs-project --rejoin
git subtree pull --prefix=Lab2-Java/arrays-cs-project lab2-fork master
git push
```

To send arrays changes to the fork manually:

```bash
git subtree push --prefix=Lab2-Java/arrays-cs-project lab2-fork master
```

Git runs pre-push hooks even during `--dry-run`. Use this command to preview a
course push without updating either repository:

```bash
git -c core.hooksPath=/dev/null push --dry-run origin main
```
