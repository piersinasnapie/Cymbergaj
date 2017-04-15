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

    public Vector(Point begin, Point end)
    {
        this.xBegin = begin.getX();
        this.yBegin = begin.getY();
        this.xEnd = end.getX();
        this.yEnd = end.getY();
    }

    public Vector(Vector vector)
    {
        this.xBegin = vector.xBegin;
        this.yBegin = vector.yBegin;
        this.xEnd = vector.xEnd;
        this.yEnd = vector.yEnd;
    }

    public double length()
    {
        double x = xEnd - xBegin;
        double y = yEnd - yBegin;
        return Math.sqrt(x*x + y*y);
    }

    public Vector multiply(double multiplier)
    {
        this.xEnd = multiplier*(xEnd - xBegin) + xBegin;
        this.yEnd = multiplier*(yEnd - yBegin) + yBegin;
        return this;
    }

    public Vector getXComponent() { return new Vector(xBegin,0,xEnd,0); }
    public Vector getYComponent() { return new Vector(0,yBegin,0,yEnd); }

    public static Vector add(Vector v1, Vector v2)
    {
        double xBegin = v1.xBegin;
        double yBegin = v1.yBegin;

        double xEnd = v1.xEnd + v2.getXComponent().length();
        double yEnd = v1.yEnd + v2.getYComponent().length();

        return new Vector(xBegin,yBegin,xEnd,yEnd);
    }

    public Vector add(Vector vector)
    {
        this.xEnd += vector.getXComponent().length();
        this.yEnd += vector.getYComponent().length();
        return this;
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
        Vector bigVector = new Vector(0,0,5,5);

        System.out.println(bigVector.getXComponent());
        System.out.println(bigVector.getYComponent());

        Vector v2 = new Vector(1,1,2,1);

        System.out.println(bigVector.add(v2));
    }
}
