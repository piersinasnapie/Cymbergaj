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

    }
    public static boolean canLinesBeIntersectWithPerpendicularLine(Point firstLineOrigin, double firstLineLength,Point secondLineOrigin, double secondLength)
    {
        //nothing here
    }
}
