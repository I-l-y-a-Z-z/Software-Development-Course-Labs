package problem6;

public class TestFormes
{
    public static void main(String[] args)
    {
        Forme[] figures = new Forme[3];
        figures[0] = new Square(2);
        figures[1] = new Circle(3);
        figures[2] = new Square(5.2);
        for (int i=0; i<figures.length; i++)
            System.out.println(figures[i] + ": area = " + figures[i].getSurface() + " cm2");
    }
}
