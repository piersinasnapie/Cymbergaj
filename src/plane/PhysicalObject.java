package plane;

class PhysicalObject
{
    double velocity;
    Vector vector;

    PhysicalObject()
    {
        this.vector = new Vector();
        velocity = vector.length();
    }

    PhysicalObject(Vector vector)
    {
        this.vector = vector;
        velocity = vector.length();
    }
}
