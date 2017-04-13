package game;

import engine.PhysicalEngine;
import objects2D.Puck;
import objects2D.Shape;
import plane.*;
import plane.Point;
import render.Render;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        CoordinatePlane coordinatePlane = new CoordinatePlane();



//        Shape shape = new Paddle(3,3,Color.BLUE);
//        Shape shape2 = new Paddle(3,3,Color.red);

        Shape shape = new Puck(3, Color.blue);
        Shape shape2 = new Puck(3, Color.red);
        Vector v = new Vector(0.1,0.1);
        PhysicalObject physicalObject = new PhysicalObject(v);
        PhysicalObject po2 = new PhysicalObject(new Vector(-0.1,-0.1));

        ObjectInCoordinateSystem object = new ObjectInCoordinateSystem(shape,physicalObject,new Point(0,0));
        ObjectInCoordinateSystem object2 = new ObjectInCoordinateSystem(shape2,po2,new Point(20,20));

        coordinatePlane.addObjectToPlane(object,object2);

        Render render = new Render(coordinatePlane,new Area(new Point(-5,-5),30,30),600,600);

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
