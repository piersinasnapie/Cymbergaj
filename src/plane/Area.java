package plane;

/**
 * Created by mateusz on 12/04/2017.
 */
public class Area {
    protected Point origin;
    protected double width;
    protected double height;
    public Area(Point origin,double widthOfObject,double heightOfObject)
    {
        this.origin=origin;
        this.height=heightOfObject;
        this.width=widthOfObject;
    }
    public static boolean intersect(Area a, Area b)
    {
        return Area.intersectLines(a.origin.getX(), a.width, b.origin.getX(), b.width) && Area.intersectLines(a.origin.getY(), a.height, b.origin.getY(), b.height);
    }
    public static boolean intersectLines(double fLineOrigin, double fLineLength,double sLineOrigin, double sLineLength)
    {
        return ((fLineOrigin >= sLineOrigin) && (fLineOrigin <= sLineOrigin + sLineLength))
                || ((sLineOrigin >= fLineOrigin) && (sLineOrigin <= fLineOrigin + fLineLength));
    }

    public static void main(String [] args)
    {
        Area a = new Area(new Point(101,0),50,50);
        Area b = new Area(new Point(0,0),100,100);
        System.out.println(intersect(a,b));
    }
}
