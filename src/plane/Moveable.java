package plane;

public interface Moveable
{
    void updatePoint(Point point);
    Point getPoint();
    double getVelocity();
    Vector getDirection();
    void updateVelocity();
    void setVelocity(double v);
    void updateDirection(Vector vector);
    Area getArea();
}
