package plane;

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
}
