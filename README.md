# Software Development Course Labs

Java coursework in one Git repository and one IntelliJ IDEA project. Each lab or
existing exercise project has its own module, so exercises can be built and run
independently.

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
│   └── arrays-cs-project/       Linked Gradle project
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

## Push to GitHub

The course repository uses `main`. Create an **empty** GitHub repository (without
an initial README, license, or `.gitignore`), then run these commands from the
course root, replacing the example URL with your new repository's URL:

```bash
git remote add origin https://github.com/YOUR_USERNAME/software-development-course-labs.git
git push -u origin main
```

After each new lab or update:

```bash
git status
git add .
git diff --cached --stat
git commit -m "Add Lab 4 exercises"
git push
```

The shared IntelliJ configuration and plain Java `.iml` files are versioned.
Generated files, Gradle caches, personal IDE settings, and local backups are
ignored. Keep the Gradle wrapper scripts and wrapper JAR in Git.

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

On the original machine, `.local-backups/` also contains a complete Git bundle,
the arrays project's previous Git metadata, and the previous per-project IntelliJ
settings. These backups stay local and are excluded from GitHub.
