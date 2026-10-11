package problem6;

public class Circle extends Forme
{
    private double radius;

    public Circle(double radius)
    {
        this.radius = radius;
    }

    public double getSurface()
    {
        return radius * radius * 3.14;
    }

    public String toString()
    {
        return "Circle (radius " + radius + " cm)";
    }
}
