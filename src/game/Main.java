package game;

import engine.PhysicalEngine;
import handlers.MouseMotionSpeed;
import objects2D.Obstacle;
import objects2D.Paddle;
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
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        CoordinatePlane coordinatePlane = new CoordinatePlane();

        Obstacle o1 = new Obstacle(2,10,Color.DARK_GRAY);
        Obstacle o2 = new Obstacle(2,10,Color.DARK_GRAY);


        Shape shape = new Puck(5, Color.blue);
        Shape shape2 = new Puck(5, Color.red);
        Shape shape3 = new Paddle(10,1,Color.black);

        Vector v = new Vector(0.1,0);
        PhysicalObject physicalObject = new PhysicalObject(v);
        PhysicalObject po2 = new PhysicalObject(new Vector(-0.1,0));

//        ObjectInCoordinateSystem object = new ObjectInCoordinateSystem(shape,physicalObject,new Point(0,0));
        ObjectInCoordinateSystem object2 = new ObjectInCoordinateSystem(shape2,po2,new Point(20,0));

        ObjectInCoordinateSystem obstacle1 = new ObjectInCoordinateSystem(o1, new PhysicalObject(),new Point(-10,0));
        ObjectInCoordinateSystem obstacle2 = new ObjectInCoordinateSystem(o2, new PhysicalObject(),new Point(10,0));


//        coordinatePlane.addObjectToPlane(obstacle1,obstacle2);
        Render render = new Render(coordinatePlane,new Area(new Point(-20,-20),50,50),600,600);

//        coordinatePlane.addObjectToPlane(object,object2);
        ObjectInCoordinateSystem paddle = new ObjectInCoordinateSystem(shape3,new PhysicalObject(),new Point(10,10));

        coordinatePlane.addObjectToPlane(paddle,object2);

        MouseMotionSpeed mouseMotionSpeed = new MouseMotionSpeed(paddle);

        Thread engineThread = new Thread(PhysicalEngine.getPhysicalEngine(coordinatePlane));
        Thread renderThread = new Thread(render);
        Thread mouseThread = new Thread(mouseMotionSpeed);

        JPanel transparentPanel = new JPanel();
        transparentPanel.setSize(new Dimension(600,600));
        transparentPanel.setBackground(new Color(0,0,0,0));
        transparentPanel.addMouseMotionListener(mouseMotionSpeed);

        JFrame window = new JFrame("TEST 1");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.add(transparentPanel);
        window.add(render);
        window.setSize(500,500);
        window.setVisible(true);

        try
        {
            Thread.sleep(1000);
            engineThread.start();
            renderThread.start();
            mouseThread.start();
        }
        catch (InterruptedException e){ e.printStackTrace(); }
    }
}
