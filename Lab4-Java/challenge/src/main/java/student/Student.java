package student;

public class Student extends Person
{
    private static Major defaultMajor = new Major("23", "Computer science");
    private String cne;
    private Major major;

    public Student()
    {
        this("", "", "", "", "");
    }

    public Student(String firstName, String lastName, String phone, String email, String cne, Major major)
    {
        super(firstName, lastName, phone, email);
        this.cne = cne;
        major.addStudent(this);
    }

    public Student(String firstName, String lastName, String phone, String email, String cne)
    {
        this(firstName, lastName, phone, email, cne, defaultMajor);
    }

    public String getCne()
    {
        return cne;
    }

    public void setCne(String cne)
    {
        this.cne = cne;
    }

    public Major getMajor()
    {
        return major;
    }

    public void setMajor(Major major)
    {
        this.major = major;
    }

    public String getFullNameFormatted()
    {
        return String.format("%s, %s", lastName.toUpperCase(), firstName);
    }

    public String toString()
    {
        return cne + " " + lastName.toUpperCase() + " " + firstName;
    }
}
