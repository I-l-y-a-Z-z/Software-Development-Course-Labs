package student;

public class Major
{
    private static int nextId = 1;
    private int id;
    private String code;
    private String name;
    private Student[] students;
    private int studentCount;

    public Major()
    {
        this("", "");
    }

    public Major(String code, String name)
    {
        id = nextId++;
        this.code = code;
        this.name = name;
        students = new Student[50];
        studentCount = 0;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void addStudent(Student s)
    {
        if (studentCount == students.length)
        {
            System.out.println("A major cannot exceed 50 students");
            return;
        }
        students[studentCount] = s;
        studentCount++;
        s.setMajor(this);
    }

    public void displayStudents()
    {
        for (int i=0; i<studentCount; i++)
            System.out.println((i+1) + ". " + students[i]);
    }

    public Student findStudentByCNE(String cne)
    {
        for (int i=0; i<studentCount; i++)
            if (students[i].getCne().equals(cne))
                return students[i];
        return null;
    }

    public int getStudentCount()
    {
        return studentCount;
    }

    public boolean removeStudent(String cne)
    {
        Student s = findStudentByCNE(cne);
        if (s == null) {return false;}
        for (int i=0; i<studentCount; i++)
        {
            if (students[i] == s)
            {
                for (int j=i; j<studentCount-1; j++)
                    students[j] = students[j+1];
                studentCount--;
                students[studentCount] = null;
                s.setMajor(null);
                return true;
            }
        }
        return false;
    }

    public double getOccupancyRate()
    {
        return (double) studentCount / students.length * 100;
    }

    public String getStudentListAsString()
    {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<studentCount; i++)
        {
            result.append(i+1).append(". ").append(students[i]);
            if (i < studentCount-1) {result.append("\n");}
        }
        return result.toString();
    }

    public String toString()
    {
        return id + " " + code + " " + name;
    }
}
