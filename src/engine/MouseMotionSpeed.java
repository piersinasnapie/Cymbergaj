package engine;

import plane.Point;
import plane.Vector;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionSpeed implements MouseMotionListener, Runnable
{
    private boolean isWorking = true;
    private Point begin;
    private Point current;

    public MouseMotionSpeed()
    {
        this.begin = new Point();
        this.current = new Point();
    }

    public Vector getDirection()
    {
        return new Vector(begin,current);
    }

    @Override
    public void run()
    {
        while (isWorking)
        {
            try
            {
                this.begin = current;

                System.out.println("begin: " + this.begin);
                Thread.sleep(3000);
            }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void mouseMoved(MouseEvent me)
    {
        try
        {
            this.current = new Point(me.getX(), me.getY());
            Thread.sleep(1000);
            System.out.println("> current: " + this.current);
        }
        catch (InterruptedException exception) { exception.printStackTrace(); }
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {

    }
}
