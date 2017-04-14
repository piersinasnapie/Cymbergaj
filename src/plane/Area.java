package plane;

/**
 * Created by mateusz on 12/04/2017.
 */


//Origin points to left-upper corner of sprite
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
        return Area.intersectLines(a.origin.getX(), a.width, b.origin.getX(), b.width)
                && Area.intersectLines(a.origin.getY(), a.height, b.origin.getY(), b.height);
    }
    public static boolean intersectLines(double fLineOrigin, double fLineLength,double sLineOrigin, double sLineLength)
    {
        return ((fLineOrigin >= sLineOrigin) && (fLineOrigin <= sLineOrigin + sLineLength))
                || ((sLineOrigin >= fLineOrigin) && (sLineOrigin <= fLineOrigin + fLineLength));
    }


    @Override
    public String toString()
    {
        return "x: "+this.getOrigin().getX()+" y: "+this.getOrigin().getY()+" w: "+this.width+" h: "+this.height;
    }
    public double getWidth()
    {
        return this.width;
    }
    public double getHeight()
    {
        return this.height;
    }
    public Point getOrigin()
    {
        return this.origin;
    }
    public void setOrigin(Point p){this.origin=p;}
    public Area getRelativePosition(Area toObject)
    {
        Point newOrigin = new Point(this.getOrigin().getX()-toObject.getOrigin().getX(),this.getOrigin().getY()-toObject.getOrigin().getY());
        double width = this.width/toObject.width;
        double height = this.height/toObject.height;
        return new Area(newOrigin,width,height);
    }
    public static void main(String [] args)
    {
        Area a = new Area(new Point(-2,2),2,2);
        Area b = new Area(new Point(0,1),1,1);
        System.out.println(intersect(a,b));
    }
}
