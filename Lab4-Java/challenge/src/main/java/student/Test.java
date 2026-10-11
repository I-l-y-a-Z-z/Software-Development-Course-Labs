package student;

public class Test
{
    public static void main(String[] args)
    {
        Major computerScience = new Major("23", "Computer science");
        Major mathematics = new Major("24", "Mathematics");

        Student youness = new Student("Youness", "Latif", "0612345678", "youness@example.com", "22885676", computerScience);
        Student anass = new Student("Anass", "Fertat", "0623456789", "anass@example.com", "23585976", computerScience);
        Student amine = new Student("Amine", "Chrif", "0634567890", "amine@example.com", "24567890", mathematics);

        System.out.println("The list of students in the computer science major is:");
        computerScience.displayStudents();
        System.out.println("\nFormatted name: " + youness.getFullNameFormatted());
        System.out.println("Student found: " + computerScience.findStudentByCNE("23585976"));
        System.out.println("\nComputer science capacity: 50 students");
        System.out.println("Current enrollment: " + computerScience.getStudentCount() + " students");
        System.out.println("Occupancy rate = " + computerScience.getOccupancyRate() + "%");
        System.out.println("\nStudent list:\n" + computerScience.getStudentListAsString());
        System.out.println("\nStudent removed: " + computerScience.removeStudent("23585976"));
    }
}

