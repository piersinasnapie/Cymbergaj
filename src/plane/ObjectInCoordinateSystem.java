package plane;

import objects2D.Puck;
import objects2D.Shape;

public class ObjectInCoordinateSystem implements Movable
{
    private Shape shape;
    private PhysicalObject physicalObject;
    private Point point;
    private State state;

    public ObjectInCoordinateSystem(Shape shape)
    {
        this(shape,new PhysicalObject(), new Point(), State.STATIC);
    }

    public ObjectInCoordinateSystem(Shape shape, Point point, State state)
    {
        this(shape, new PhysicalObject(), point, state);
    }

    public ObjectInCoordinateSystem(Shape shape, PhysicalObject physicalObject, Point point, State state)
    {
        this.shape = shape;
        this.physicalObject = physicalObject;
        this.point = point;
        this.state = state;
    }

    public double getWidth(){ return this.shape.width; }
    public double getHeight(){ return this.shape.height; }


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
    public Point getRightUpper()
    {
        return new Point(this.shape.width+this.getPoint().getX(),this.getPoint().getY());
    }
    @Override
    public Point getLeftUpper()
    {
        return this.getPoint();
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

    @Override
    public Shape getShape()
    {
        return this.shape;
    }

    @Override
    public State getState()
    {
        return this.state;
    }

    public String toString()
    {
        return "Object :: origin point: " + point + ",\n    " + physicalObject + "State: " + state;
    }

    // test
    public static void main(String[] args)
    {
        System.out.println(new ObjectInCoordinateSystem((new Puck(20))));
    }
}
