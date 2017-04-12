package engine;

import objects2D.Puck;
import plane.Area;
import plane.CoordinatePlane;
import plane.Moveable;
import plane.ObjectInCoordinateSystem;
import plane.Point;
import plane.Sprite;

import java.util.ArrayList;

public class PhysicalEngine implements Runnable, Colider
{
    private boolean isWorking;

    ArrayList<Moveable> objects;

    private static PhysicalEngine physicalEngine = null;

    private PhysicalEngine(CoordinatePlane coordinatePlane)
    {
        objects = new ArrayList<>();
        for(ObjectInCoordinateSystem object : coordinatePlane.getObjectsInPlane())
        {
            objects.add(object);
        }
        this.isWorking = true;
    }


    static PhysicalEngine getPhysicalEngine(CoordinatePlane coordinatePlane)
    {
        if(physicalEngine == null) return new PhysicalEngine(coordinatePlane);
        return physicalEngine;
    }

    void resolveColision(Moveable object1, Moveable object2)
    {

    }

    void calculateMotionCausedByUser(Moveable object)
    {

    }

    @Override
    public void run()
    {
        try
        {
            while (isWorking)
            {
                searchColision();
                Thread.sleep(2000);
            }
        }
        catch (InterruptedException e){ e.printStackTrace(); }
    }

    private void searchColision()
    {
        for (Moveable object1 : objects)
        {
            for (Moveable object2 : objects)
            {
                if (colisionDetected(object1, object2))
                {
                    resolveColision(object1, object2);

                    System.out.println("Colision detected for:  \n" + object1 + "\n" + object2);
                }
            }
        }
    }

    @Override
    public boolean colisionDetected(Moveable object1, Moveable object2)
    {
        synchronized (object1)
        {
            synchronized (object2)
            {
                return Area.intersect(object1.getArea(), object2.getArea());
            }
        }
    }


    // Test
    public static void main(String[] args)
    {
        CoordinatePlane cp = new CoordinatePlane();

        ObjectInCoordinateSystem object1 = new ObjectInCoordinateSystem(new Sprite(new Puck(20)));
        ObjectInCoordinateSystem object2 = new ObjectInCoordinateSystem(new Sprite(new Puck(20)), new Point(5,5));

        cp.addObjectToPlane(object1,object2);

        new Thread(PhysicalEngine.getPhysicalEngine(cp)).start();
    }
}
