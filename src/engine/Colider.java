package engine;

import plane.Moveable;

public interface Colider
{
    boolean colisionDetected(Moveable object1, Moveable object2);
}