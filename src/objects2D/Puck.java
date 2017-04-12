package objects2D;

public class Puck extends Shape
{
    public static final double radius = 10;

    Puck()
    {
        this.width = radius;
        this.height = radius;
    }

    @Override
    public Shape getShape()
    {
        return this;
    }
}
