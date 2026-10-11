package student;

public class Person
{
    private static int nextId = 1;
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String phone;
    protected String email;

    public Person()
    {
        this("", "", "", "");
    }

    public Person(String firstName, String lastName, String phone, String email)
    {
        this.id = nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String toString()
    {
        return id + " " + lastName + " " + firstName + " " + phone + " " + email;
    }

    public void display()
    {
        System.out.println(this);
    }
}

