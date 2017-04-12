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
        if(Area.canLinesBeIntersectedWithPerpendicularLine(a.origin,a.height,b.origin,b.height)&&Area.canLinesBeIntersectedWithPerpendicularLine(a.origin,a.width,b.origin,b.width))
        {
            return true;
        }
        return false;
    }
    public static boolean canLinesBeIntersectedWithPerpendicularLine(Point firstLineOrigin, double firstLineLength,Point secondLineOrigin, double secondLineLength)
    {
        if((firstLineOrigin.getX()>=secondLineOrigin.getX()) && (firstLineOrigin.getX()+firstLineLength<=secondLineOrigin.getX()))
        {
            return true;
        }
        return false;
    }
}
