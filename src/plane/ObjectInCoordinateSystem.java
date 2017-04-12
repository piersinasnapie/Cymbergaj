package plane;

import objects2D.Puck;

public class ObjectInCoordinateSystem implements Moveable
{
    private Sprite sprite;
    private Point point;

    public ObjectInCoordinateSystem(Sprite sprite)
    {
        this(sprite,new Point());
    }

    public ObjectInCoordinateSystem(Sprite sprite, Point point)
    {
        this.sprite = sprite;
        this.point = point;
    }

    public Point getPoint(){ return this.point; }
    public double getWidth(){ return this.sprite.shape.width; }
    public double getHeight(){ return this.sprite.shape.height; }

    @Override
    public double getVelocity()
    {
        return sprite.physicalObject.velocity;
    }

    @Override
    public Vector getDirection()
    {
        return sprite.physicalObject.vector;
    }

    @Override
    public void updateVelocity(double velocity)
    {
        this.sprite.physicalObject.velocity = velocity;
    }

    @Override
    public void updateDirection(Vector vector)
    {
        this.sprite.physicalObject.vector = vector;
    }

    @Override
    public Area getArea()
    {
        return new Area(point, sprite.shape.width, sprite.shape.height);
    }

    public String toString()
    {
        return "Object :: origin point: " + point + ",\n    " + sprite;
    }

    // test
    public static void main(String[] args)
    {
        System.out.println(new ObjectInCoordinateSystem(new Sprite(new Puck(20))));
    }
}
