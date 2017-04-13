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
    public Vector(double xEnd,double yEnd)
    {
        this(0,0,xEnd,yEnd);
    }
    public Vector(double xBegin, double yBegin, double xEnd, double yEnd)
    {
        this.xBegin = xBegin;
        this.yBegin = yBegin;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    double length()
    {
        double x = xEnd - xBegin;
        double y = yEnd - yBegin;
        return Math.sqrt(x*x + y*y);
    }

    void multiply(double multiplier)
    {
        this.xEnd = multiplier*(xEnd - xBegin) + xBegin;
        this.yEnd = multiplier*(yEnd - yBegin) + yBegin;
    }
    public plane.Point getEndPoint()
    {
        return new plane.Point(this.xEnd,this.yEnd);
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
