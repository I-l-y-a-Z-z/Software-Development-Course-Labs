package instructor;

import student.Person;

public class Instructor extends Person
{
    private String employeeNumber;

    public Instructor()
    {
        this("", "", "", "", "");
    }

    public Instructor(String firstName, String lastName, String phone, String email, String employeeNumber)
    {
        super(firstName, lastName, phone, email);
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber()
    {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber)
    {
        this.employeeNumber = employeeNumber;
    }

    public String cleanEmployeeNumber()
    {
        return employeeNumber.trim().replace(" ", "");
    }

    public String summaryLine()
    {
        return String.format("Instructor[employeeNumber=%s, lastName=%s, firstName=%s]",
                employeeNumber, lastName, firstName);
    }

    public String toCard()
    {
        StringBuilder card = new StringBuilder();
        card.append("Instructor\n----------\n");
        card.append("Employee #: ").append(employeeNumber).append("\n");
        card.append("Name : ").append(lastName).append(", ").append(firstName).append("\n");
        card.append("Email : ").append(email).append("\n");
        card.append("Phone : ").append(phone);
        return card.toString();
    }

    public String displayName()
    {
        StringBuilder name = new StringBuilder();
        if (lastName != null) {name.append(lastName);}
        if (firstName != null)
        {
            if (name.length() > 0) {name.append(" ");}
            name.append(firstName);
        }
        return name.toString();
    }

    public String toString()
    {
        return summaryLine();
    }
}
