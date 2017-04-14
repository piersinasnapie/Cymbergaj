package engine;

import objects2D.Puck;
import plane.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhysicalEngine implements Runnable, Colider
{
    private boolean isWorking;

    ArrayList<Moveable> objects;

    private static PhysicalEngine physicalEngine = null;

    private PhysicalEngine(CoordinatePlane coordinatePlane)
    {
        objects = new ArrayList<>();
        objects.addAll(coordinatePlane.getObjectsInPlane());
        this.isWorking = true;
    }


    public static PhysicalEngine getPhysicalEngine(CoordinatePlane coordinatePlane)
    {
        if(physicalEngine == null) return new PhysicalEngine(coordinatePlane);
        return physicalEngine;
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
                moveObjects();
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

    void resolveColision(Moveable object1, Moveable object2)
    {


        object1.updateDirection(object1.getDirection().multiply(-1));
        object2.updateDirection(object2.getDirection().multiply(-1));
    }

    private void searchColision()
    {
        Map<Moveable,Boolean> resolvedCollisionMap = new HashMap<>();
        for(Moveable moveableObject : objects)
        {
            resolvedCollisionMap.put(moveableObject,false);
        }
        for (Moveable object1 : objects)
        {
            for (Moveable object2 : objects)
            {
                if(object1 != object2)
                {
                    if (colisionDetected(object1, object2) && resolvedCollisionMap.get(object1)!=true && resolvedCollisionMap.get(object2)!=true)
                    {
                        System.out.println("†††\nCOLISION COLISION COLISION COLISION COLISION COLISION \n†††");
                        synchronized(object1)
                        {
                            synchronized(object2)
                            {
                                resolveColision(object1, object2);
                                resolvedCollisionMap.replace(object1, true);
                                resolvedCollisionMap.replace(object2, true);
                            }
                        }
                        System.out.println("1. " + object1);
                        System.out.println("2. " + object2);
                    }
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

        ObjectInCoordinateSystem object1 = new ObjectInCoordinateSystem(new Puck(20));
        ObjectInCoordinateSystem object2 = new ObjectInCoordinateSystem(new Puck(20), new PhysicalObject(new Vector(1,1),0.9), new Point(5,5));

        cp.addObjectToPlane(object1,object2);

        new Thread(PhysicalEngine.getPhysicalEngine(cp)).start();
    }
}
