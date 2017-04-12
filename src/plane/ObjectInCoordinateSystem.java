package plane;

public class ObjectInCoordinateSystem implements Moveable
{
    private Sprite sprite;

    ObjectInCoordinateSystem(Sprite sprite)
    {
        this.sprite = sprite;
    }

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
