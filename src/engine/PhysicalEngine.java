package engine;

import plane.*;

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
                Thread.sleep(2);
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
                Vector vector = object.getDirection();
                if (vector.length() > CoordinatePlane.maxVectorLength) {
                    vector.multiply(-CoordinatePlane.maxVectorLength / vector.length());
                }
                object.updatePoint(object.getPoint().add(vector.getEndPoint()));
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
        Movable objectToManipulate;
        Movable staticObject;
        if(object1.getState() == State.MOVING){
            objectToManipulate=object1;
            staticObject=object2;
        }
        else
        {
            objectToManipulate=object2;
            staticObject=object1;
        }
        Flank flank = Colider.flankColiding(staticObject,objectToManipulate);
        System.out.println(flank);
        double x;
        double y;
        if(flank == Flank.LEFT)
        {
            x=staticObject.getLeftUpper().getX()-objectToManipulate.getArea().getWidth();
            y=objectToManipulate.getPoint().getY();
            objectToManipulate.updatePoint(new plane.Point(x,y));
            object1.updateDirection(object1.getDirection().reverseX());
            object2.updateDirection(object2.getDirection().reverseX());
            return;
        }
        if(flank == Flank.RIGHT)
        {
            x=staticObject.getLeftUpper().getX()+staticObject.getArea().getWidth();
            y=objectToManipulate.getPoint().getY();
            objectToManipulate.updatePoint(new plane.Point(x,y));
            object1.updateDirection(object1.getDirection().reverseX());
            object2.updateDirection(object2.getDirection().reverseX());
            return;
        }
        if(flank == Flank.TOP)
        {
            x=objectToManipulate.getPoint().getX();
            y=staticObject.getPoint().getY()-objectToManipulate.getArea().getHeight();
            objectToManipulate.updatePoint(new plane.Point(x,y));
            object1.updateDirection(object1.getDirection().reverseY());
            object2.updateDirection(object2.getDirection().reverseY());
            return;
        }
        if(flank == Flank.BOTTOM)
        {
            x=objectToManipulate.getPoint().getX();
            y=staticObject.getPoint().getY()+staticObject.getArea().getHeight();
            objectToManipulate.updatePoint(new plane.Point(x,y));
            object1.updateDirection(object1.getDirection().reverseY());
            object2.updateDirection(object2.getDirection().reverseY());
            return;
        }
    }


    private void searchColision()
    {
        Map<Movable,Boolean> resolvedCollisionMap = new HashMap<>();
        for(Movable movableObject : objects)
        {
            resolvedCollisionMap.put(movableObject,false);
        }
        for (Movable object : objects)
        {
            Movable collidingObject = isColliding(object);
            if (collidingObject!=null && !resolvedCollisionMap.get(object) && !resolvedCollisionMap.get(collidingObject))
            {
                System.out.println("†††\nCOLISION COLISION COLISION COLISION COLISION COLISION \n†††");
                synchronized(object)
                {
                    synchronized(collidingObject)
                    {
                        resolveColision(object, collidingObject);
                        resolvedCollisionMap.replace(object, true);
                        resolvedCollisionMap.replace(collidingObject, true);
                    }
                }
                System.out.println("1. " + object);
                System.out.println("2. " + collidingObject);
            }
        }
    }

    private Movable isColliding(Movable object){
        for (Movable object2 : objects)
        {
            if(object != object2)
            {
                if (collisionDetected(object, object2))
                {
                    return object2;
                }
            }
        }
        return null;
    }
    private boolean collisionDetected(Movable object1, Movable object2)
    {
        return Area.intersect(object1.getArea(), object2.getArea());
    }
}
