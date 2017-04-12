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

    @Override
    public String toString()
    {
        return "v= " + velocity + ", " + vector;
    }

    // test
    public static void main(String[] args)
    {
        PhysicalObject o = new PhysicalObject();
        System.out.println(o);
    }
}
