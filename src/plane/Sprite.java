package plane;

import objects2D.Puck;
import objects2D.Shape;

public class Sprite
{
    Shape shape;
    PhysicalObject physicalObject;

    public Sprite(Shape shape)
    {
        this(shape,new PhysicalObject());
    }

    public Sprite(Shape shape, PhysicalObject physicalObject)
    {
        this.shape = shape;
        this.physicalObject = physicalObject;
    }

    @Override
    public String toString()
    {
        return "Sprite: " + physicalObject;
    }

    // test
    public static void main(String[] args)
    {
        System.out.println(new Sprite(new Puck(20)));
    }
}
