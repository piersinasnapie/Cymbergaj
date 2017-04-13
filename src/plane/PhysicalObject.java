package plane;

public class PhysicalObject
{
    Vector vector;
    double slowingDownRatio;

    public PhysicalObject()
    {
        this(new Vector(),1);
    }

    public PhysicalObject(Vector vector) { this(vector,1); }

    public PhysicalObject(Vector vector, double slowingDownRatio)
    {
        this.vector = vector;
        this.slowingDownRatio = slowingDownRatio;
    }

    @Override
    public String toString()
    {
        return "v= " +vector.length() + ", " + vector;
    }

    // test
    public static void main(String[] args)
    {
        PhysicalObject o = new PhysicalObject();
        System.out.println(o);
    }
}
