package plane;

import objects2D.Puck;
import objects2D.Shape;

public class ObjectInCoordinateSystem implements Moveable
{
    private Shape shape;
    private PhysicalObject physicalObject;
    private Point point;

    public ObjectInCoordinateSystem(Shape shape)
    {
        this(shape,new PhysicalObject(), new Point());
    }

    public ObjectInCoordinateSystem(Shape shape, Point point)
    {
        this(shape, new PhysicalObject(), point);
    }

    public ObjectInCoordinateSystem(Shape shape, PhysicalObject physicalObject, Point point)
    {
        this.shape = shape;
        this.physicalObject = physicalObject;
        this.point = point;
    }

    public double getWidth(){ return this.shape.width; }
    public double getHeight(){ return this.shape.height; }
    public objects2D.Shape getShape(){return shape;}

    @Override
    public void updatePoint(Point point)
    {
        this.point = point;
    }

    @Override
    public Point getPoint()
    {
        return this.point;
    }

    @Override
    public double getVelocity()
    {
        return physicalObject.vector.length();
    }

    @Override
    public Vector getDirection()
    {
        return physicalObject.vector;
    }

    @Override
    public void updateVelocity()
    {
        this.physicalObject.vector.multiply(physicalObject.slowingDownRatio);
    }

    @Override
    public void setVelocity(double v)
    {
        this.physicalObject.vector.multiply(v);
    }

    @Override
    public void updateDirection(Vector vector)
    {
        this.physicalObject.vector = vector;
    }

    @Override
    public Area getArea()
    {
        return new Area(point,shape.width,shape.height);
    }

    public String toString()
    {
        return "Object :: origin point: " + point + ",\n    " + physicalObject;
    }

    // test
    public static void main(String[] args)
    {
        System.out.println(new ObjectInCoordinateSystem((new Puck(20))));
    }
}
