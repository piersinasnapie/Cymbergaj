package engine;

import plane.Movable;

public interface Colider
{
    boolean colisionDetected(Movable object1, Movable object2);
}