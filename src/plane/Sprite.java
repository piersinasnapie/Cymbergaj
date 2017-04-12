package plane;

import objects2D.Shape;

class Sprite
{
    Shape shape;
    PhysicalObject physicalObject;

    Sprite(Shape shape)
    {
        this(shape,new PhysicalObject());
    }

    Sprite(Shape shape, PhysicalObject physicalObject)
    {
        this.shape = shape;
        this.physicalObject = physicalObject;
    }
}
