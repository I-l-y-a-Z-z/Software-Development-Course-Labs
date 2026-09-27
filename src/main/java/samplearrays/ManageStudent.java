package samplearrays;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.Comparator;

import static java.lang.Float.NaN;

public class ManageStudent {

    // 2) Find the Oldest Student
    public static Student findOldest(Student[] students) {
        Student oldest = new Student(0, "", 0);
        for (Student student : students){
            if (student.getAge() > oldest.getAge()) oldest = student;
        }
        return oldest;
    }

    // 3) Count Adult Students (age >= 18)
    public static int countAdults(Student[] students) {
        int out = 0;
        for (Student student : students){
            if (student.getAge() >= 18) out++;
        }
        return out;
    }

    // 4) Average Grade (returns NaN if no students or grades)
    public static double averageGrade(Student[] students) {
        if (students.length == 0) return NaN;
        double average = 0D;
        for (Student student : students){
            average += student.getGrade();
        }
        return average / students.length;
    }

    // 5) Search by Name (case-sensitive; change to equalsIgnoreCase if desired)
    public static Student findStudentByName(Student[] students, String name) {
        for (Student student : students) {
            if (student.getName() == name) return student;
        }
        return null;
    }

    // 6) Sort Students by Grade (descending)
    public static void sortByGradeDesc(Student[] students) {
        Arrays.sort(students, Comparator.comparingInt(Student::getGrade).reversed());
    }

    // 7) Print High Achievers (grade >= 15)
    public static void printHighAchievers(Student[] students) {
        for (Student student : students) {
            if (student.getGrade() >= 15) System.out.println(student.toString());
        }
    }

    // 8) Update Student Grade by id
    public static boolean updateGrade(Student[] students, int id, int newGrade) {
        for (Student student : students){
            if (student.getId() == id) {
                student.setGrade(newGrade);
                return true;
            }
        }
        return false;
    }

    // 9) Find Duplicate Names
    public static boolean hasDuplicateNames(Student[] students) {
        Set<String> names = Arrays.stream(students).map(Student::getName).collect(Collectors.toSet());
        return !(students.length == names.size());
    }

    // 10) Expandable Array: return a new array with one more slot and append student
    public static Student[] appendStudent(Student[] students, Student newStudent) {
        Student[] newStudents = new Student[students.length + 1];
        System.arraycopy(students, 0, newStudents, 0, students.length);
        newStudents[students.length] = newStudent;
        return newStudents;
    }

    // 1) Create an Array of Students + demos for all tasks
    public static void main(String[] args) {
        // Create & initialize array of 5 students
        Student[] arr = new Student[5];
        arr[0] = new Student(0, "Ilyas");
        arr[1] = new Student(1, "Yasser", 19);
        arr[2] = new Student(2, "Anass", 20, 18);
        arr[3] = new Student(3, "Latif", 18);
        arr[4] = new Student(4, "Amine");
        // Print all
        System.out.println("== All Students ==");
        for (Student s : arr) System.out.println(s);
        System.out.println("Total created: " + Student.getNumStudent());

        // 2) Oldest
        Student oldest = findOldest(arr);
        System.out.println("The oldest student is : \n" + oldest.toString());

        // 3) Count adults
        int count = countAdults(arr);
        System.out.println("The number of Adults in the class is : " + count);

        // 4) Average grade
        double average = averageGrade(arr);
        System.out.println("The average grade is : " + average);

        // 5) Find by name
        Student containsLatif = findStudentByName(arr, "Latif");
        if (containsLatif == null) System.out.println("The student list does not contain Latif");
        else System.out.println("The student list contains Latif");

        // 6) Sort by grade desc
        // sort function
        sortByGradeDesc(arr);
        System.out.println("\n== Sorted by grade (desc) ==");
        for (Student s : arr) System.out.println(s.toString());

        // 7) High achievers >= 15
        System.out.println("\nHigh achievers:");
        printHighAchievers(arr);

        // 8) Update grade by id
        // function
        boolean updated = updateGrade(arr, 4, 20);
        System.out.println("\nUpdated id=4? " + updated);
        System.out.println(findStudentByName(arr, "Amine"));

        // 9) Duplicate names
        boolean containsDuplicates = hasDuplicateNames(arr);
        System.out.println("Contains Duplicate names ? : " + containsDuplicates);

        // 10) Append new student
        Student hamid = new Student(5, "Hamid", 19);
        appendStudent(arr, hamid);
    }
}

