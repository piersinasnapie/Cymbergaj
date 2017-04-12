package objects2D;

public class Puck extends Shape
{
    public static double radius = 10;

    Puck()
    {
        this.width = radius;
        this.height = radius;
    }

    @Override
    public Shape getGraphics()
    {
        return this;
    }
}
