package plane;

public class ObjectInCoordinateSystem implements Moveable
{
    private Sprite sprite;
    private Point point;

    ObjectInCoordinateSystem(Sprite sprite)
    {
        this.sprite = sprite;
        this.point = new Point();
    }

    Point getPoint(){ return this.point; }
    double getWidth(){ return this.sprite.shape.width; }
    double getHeight(){ return this.sprite.shape.height; }

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
}
