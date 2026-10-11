package problem7;

public class TestMetiers
{
    public static void main(String[] args)
    {
        Person[] people = new Person[3];
        people[0] = new Carpenter("Paul");
        people[1] = new Plumber("Jean");
        people[2] = new Carpenter("Adrien");
        for (int i=0; i<people.length; i++)
            people[i].display();
    }
}
