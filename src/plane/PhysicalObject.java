package plane;

class PhysicalObject
{
    double velocity;
    Vector vector;

    PhysicalObject()
    {
        this(new Vector());
    }

    PhysicalObject(Vector vector)
    {
        this.vector = vector;
        velocity = vector.length();
    }
}
