package plane;

public interface Moveable
{
    double getVelocity();
    Vector getDirection();
    void updateVelocity(double velocity);
    void updateDirection(Vector vector);
}
