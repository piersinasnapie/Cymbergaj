package plane;

/**
 * Created by mateusz on 12/04/2017.
 */

//Origin points to left-upper corner of sprite
public class Point {
    protected double x;
    protected double y;
    public Point(double x, double y)
    {
        this.x=x;
        this.y=y;
    }
    public Point()
    {
        this(0,0);
    }

    public double getX()
    {
        return x;
    }
    public void setX(double x)
    {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    public void setY(double y)
    {
        this.y = y;
    }

    public String toString()
    {
        return "(" + x + "," + y + ")";
    }

    // test
    public static void main(String[] args)
    {
        System.out.println(new Point(1,2));
    }
}
