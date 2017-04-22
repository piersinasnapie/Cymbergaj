package engine;

import objects2D.Puck;
import plane.Area;
import plane.CoordinatePlane;
import plane.Movable;
import plane.ObjectInCoordinateSystem;
import plane.PhysicalObject;
import plane.Point;
import plane.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhysicalEngine implements Runnable
{
    private boolean isWorking;

    ArrayList<Movable> objects;

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

    @Override
    public void run()
    {
        try
        {
            while (isWorking)
            {
                searchColision();
                moveObjects();
                Thread.sleep(5);
            }
        }
        catch (InterruptedException e){ e.printStackTrace(); }
    }

    void moveObjects()
    {
        for(Movable object : objects)
        {
            synchronized (object)
            {
                object.updatePoint(object.getPoint().add(object.getDirection().getEndPoint()));
                slowDownObject(object);
            }
        }
    }

    void slowDownObject(Movable object)
    {
        if(object.getVelocity() > 0.01)
        {
            object.updateVelocity();
        }
        else object.setVelocity(0);
    }

    void resolveColision(Movable object1, Movable object2)
    {
        Flank flank = Colider.flankColiding(object1,object2);
        System.out.println(flank);
        if(flank == Flank.LEFT || flank == Flank.RIGHT)
        {
            object1.updateDirection(object1.getDirection().reverseX());
            object2.updateDirection(object2.getDirection().reverseX());
        }
        else if(flank == Flank.BOTTOM || flank == Flank.TOP)
        {
            object1.updateDirection(object1.getDirection().reverseY());
            object2.updateDirection(object2.getDirection().reverseY());
        }
//        object1.updateDirection(object1.getDirection().multiply(-1));
//        object2.updateDirection(object2.getDirection().multiply(-1));
    }

    private void searchColision()
    {
        Map<Movable,Boolean> resolvedCollisionMap = new HashMap<>();
        for(Movable movableObject : objects)
        {
            resolvedCollisionMap.put(movableObject,false);
        }
        for (Movable object1 : objects)
        {
            for (Movable object2 : objects)
            {
                if(object1 != object2)
                {
                    if (colisionDetected(object1, object2) && !resolvedCollisionMap.get(object1) && !resolvedCollisionMap.get(object2))
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

    private boolean colisionDetected(Movable object1, Movable object2)
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
        ObjectInCoordinateSystem object2 = new ObjectInCoordinateSystem(new Puck(20), new PhysicalObject(new Vector(1,1),0.9), new Point(5,5), plane.State.MOVING);

        cp.addObjectToPlane(object1,object2);

        new Thread(PhysicalEngine.getPhysicalEngine(cp)).start();
    }
}
