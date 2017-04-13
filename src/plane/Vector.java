package plane;

public class Vector
{
    double xBegin;
    double yBegin;
    double xEnd;
    double yEnd;

    public Vector()
    {
        this(0,0,0,0);
    }

    Vector(double xBegin, double yBegin, double xEnd, double yEnd)
    {
        this.xBegin = xBegin;
        this.yBegin = yBegin;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    public double length()
    {
        double x = xEnd - xBegin;
        double y = yEnd - yBegin;
        return Math.sqrt(x*x + y*y);
    }

    public void multiply(double multiplier)
    {
        this.xEnd = multiplier*(xEnd - xBegin) + xBegin;
        this.yEnd = multiplier*(yEnd - yBegin) + yBegin;
    }

    public String toString()
    {
        return "begin = [" + xBegin + "," + yBegin + "], end = [" + xEnd + "," + yEnd + "]";
    }


    // test
    public static void main(String[] args)
    {
        Vector v1 = new Vector(1,1,4,4);
        System.out.println(v1 + ", " + v1.length());
        v1.multiply(2);
        System.out.println(v1 + ", " + v1.length());
    }
}
