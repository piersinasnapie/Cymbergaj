package plane;

import objects2D.Shape;

public class Sprite
{
    Shape shape;
    PhysicalObject physicalObject;

    Sprite(Shape shape)
    {
        this.shape = shape;
        this.physicalObject = new PhysicalObject();
    }

    Sprite(Shape shape, PhysicalObject physicalObject)
    {
        this.shape = shape;
        this.physicalObject = physicalObject;
    }
}
