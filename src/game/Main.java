package game;

import engine.PhysicalEngine;
import objects2D.Puck;
import objects2D.Shape;
import plane.Area;
import plane.CoordinatePlane;
import plane.ObjectInCoordinateSystem;
import plane.PhysicalObject;
import plane.Point;
import plane.Vector;
import render.Render;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        CoordinatePlane coordinatePlane = new CoordinatePlane();

        Shape shape = new Puck(3);
        Vector v = new Vector(1,1);
        PhysicalObject physicalObject = new PhysicalObject(v);
        PhysicalObject po2 = new PhysicalObject(new Vector(-1,-1));

        ObjectInCoordinateSystem object = new ObjectInCoordinateSystem(shape,physicalObject,new Point(0,0));
        ObjectInCoordinateSystem object2 = new ObjectInCoordinateSystem(shape,po2,new Point(60,60));

        coordinatePlane.addObjectToPlane(object,object2);

        Render render = new Render(coordinatePlane,new Area(new Point(0,0),300,300),600,600);

        Thread engineThread = new Thread(PhysicalEngine.getPhysicalEngine(coordinatePlane));
        Thread renderThread = new Thread(render);

        JFrame window = new JFrame("TEST 1");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.add(render);
        window.setSize(500,500);
        window.setVisible(true);

        try
        {
            Thread.sleep(1000);
            engineThread.start();
            renderThread.start();
        }
        catch (InterruptedException e){ e.printStackTrace(); }
    }
}
