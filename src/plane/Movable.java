package plane;

import objects2D.Shape;

public interface Movable
{
    void updatePoint(Point point);
    Point getPoint();
    double getVelocity();
    Vector getDirection();
    void updateVelocity();
    void setVelocity(double v);
    void updateDirection(Vector vector);
    Area getArea();
    Shape getShape();
    State getState();
}
