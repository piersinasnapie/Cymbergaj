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
import plane.State;
import plane.Vector;
import render.Render;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        CoordinatePlane coordinatePlane = new CoordinatePlane();


        Shape shape = new Puck(5, Color.blue);
        Shape shape2 = new Puck(5, Color.red);
        Shape shape3 = new Paddle(10,1,Color.black);

        Vector v = new Vector(0.1,0);
        PhysicalObject physicalObject = new PhysicalObject(v);
        PhysicalObject po2 = new PhysicalObject(new Vector(-0.1,0.1));

//        ObjectInCoordinateSystem object = new ObjectInCoordinateSystem(shape,physicalObject,new Point(0,0));
        ObjectInCoordinateSystem puck = new ObjectInCoordinateSystem(shape2,po2,new Point(10,0), State.MOVING);


        Render render = new Render(coordinatePlane,new Area(new Point(-20,-20),50,50),600,600);

//        coordinatePlane.addObjectToPlane(object,puck);
        ObjectInCoordinateSystem paddle = new ObjectInCoordinateSystem(shape3,new PhysicalObject(),new Point(10,10), State.STATIC);


        // walls

        Obstacle verticalWall = new Obstacle(2,70, Color.cyan);
        Obstacle horizontalWall = new Obstacle(70,2, Color.cyan);
        PhysicalObject physicalWall = new PhysicalObject(new Vector(),0);

        ObjectInCoordinateSystem left = new ObjectInCoordinateSystem(verticalWall,physicalWall,new Point(-20,-20), State.STATIC);
        ObjectInCoordinateSystem right = new ObjectInCoordinateSystem(verticalWall,physicalWall,new Point(21,-20), State.STATIC);
        ObjectInCoordinateSystem up = new ObjectInCoordinateSystem(horizontalWall,physicalWall,new Point(-16,-20), State.STATIC);
        ObjectInCoordinateSystem down = new ObjectInCoordinateSystem(horizontalWall,physicalWall,new Point(-16,16), State.STATIC);

        coordinatePlane.addObjectToPlane(left,right,up,down);

        coordinatePlane.addObjectToPlane(puck,paddle);
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
            renderThread.start();
            Thread.sleep(1000);
            engineThread.start();
            mouseThread.start();
        }
        catch (InterruptedException e){ e.printStackTrace(); }
    }
}
