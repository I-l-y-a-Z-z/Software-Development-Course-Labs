package samplearrays;

import java.util.Arrays;
import java.util.Comparator;

class Student {
    private int id;
    private String name;
    private int age;
    private int grade; // 0..20 or 0..100 as you prefer

    // keep counter private; expose getter only
    private static int numStudent = 0;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        numStudent++;
    }
    public Student(int id, String name, int age) {
        this(id, name);
        this.age = age;
    }
    public Student(int id, String name, int age, int grade) {
        this(id, name, age);
        this.grade = grade;
    }

    // Getters / setters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getGrade() { return grade; }
    public void setAge(int age) { this.age = age; }
    public void setGrade(int grade) { this.grade = grade; }
    public static int getNumStudent() { return numStudent; }

    public boolean isAdult() { return age >= 18; }

    @Override
    public String toString() {
        return "Student{id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade + '}';
    }
}
