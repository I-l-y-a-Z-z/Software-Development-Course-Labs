package instructor;

public class Subject
{
    private static int nextId = 1;
    private int id;
    private String code;
    private String title;
    private Instructor instructor;

    public Subject()
    {
        this("", "", null);
    }

    public Subject(String code, String title, Instructor instructor)
    {
        id = nextId++;
        this.code = code;
        this.title = title;
        this.instructor = instructor;
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Instructor getInstructor()
    {
        return instructor;
    }

    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }

    public String normalizedCode()
    {
        return code.trim().toUpperCase();
    }

    public String properTitle()
    {
        String[] words = title.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i=0; i<words.length; i++)
        {
            if (words[i].length() > 0)
            {
                if (result.length() > 0) {result.append(" ");}
                result.append(words[i].substring(0, 1).toUpperCase());
                result.append(words[i].substring(1));
            }
        }
        return result.toString();
    }

    public boolean isIntroCourse()
    {
        return title.toLowerCase().contains("intro") || code.startsWith("INTRO-");
    }

    public String syllabusLine(Instructor instructor)
    {
        StringBuilder result = new StringBuilder();
        result.append(code).append(" - ").append(title);
        result.append(" (Instructor: ").append(instructor.getLastName());
        result.append(" ").append(instructor.getFirstName()).append(")");
        return result.toString();
    }

    public String toString()
    {
        return id + " " + code + " " + title;
    }
}
