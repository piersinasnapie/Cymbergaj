package plane;

class Vector
{
    double xBegin;
    double yBegin;
    double xEnd;
    double yEnd;

    Vector()
    {
        this.xBegin = 0;
        this.yBegin = 0;
        this.xEnd = 0;
        this.yEnd = 0;
    }

    Vector(double xBegin, double yBegin, double xEnd, double yEnd)
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

    public String toString()
    {
        return "begin = [" + xBegin + "," + yBegin + "], end = [" + xEnd + "," + yEnd + "]";
    }

    public static void main(String[] args)
    {
        Vector v1 = new Vector(1,1,4,4);
        System.out.println(v1 + ", " + v1.length());
        v1.multiply(2);
        System.out.println(v1 + ", " + v1.length());
    }
}
