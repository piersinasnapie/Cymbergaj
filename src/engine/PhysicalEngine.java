package engine;

import objects2D.Puck;
import plane.Area;
import plane.CoordinatePlane;
import plane.Moveable;
import plane.ObjectInCoordinateSystem;
import plane.PhysicalObject;
import plane.Point;
import plane.Vector;

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


    public static PhysicalEngine getPhysicalEngine(CoordinatePlane coordinatePlane)
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
                moveObjects();
//                searchColision();
                Thread.sleep(20);
            }
        }
        catch (InterruptedException e){ e.printStackTrace(); }
    }

    void moveObjects()
    {
        for(Moveable object : objects)
        {
            synchronized (object)
            {
                object.updatePoint(object.getPoint().add(object.getDirection().getEndPoint()));
                slowDownObject(object);
                System.out.println(object);
            }
        }
    }

    void slowDownObject(Moveable object)
    {
        if(object.getVelocity() > 0.01)
        {
            object.updateVelocity();
        }
        else object.setVelocity(0);
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

//        ObjectInCoordinateSystem object1 = new ObjectInCoordinateSystem(new Puck(20));
        ObjectInCoordinateSystem object2 = new ObjectInCoordinateSystem(new Puck(20), new PhysicalObject(new Vector(1,1),0.9), new Point(5,5));

//        cp.addObjectToPlane(object1,object2);

        cp.addObjectToPlane(object2);

        new Thread(PhysicalEngine.getPhysicalEngine(cp)).start();
    }
}
