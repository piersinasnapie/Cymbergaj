package handlers;

import plane.Movable;
import plane.Vector;
import render.Render;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMotionSpeed implements MouseMotionListener, Runnable
{
    private boolean isWorking = true;
    private plane.Point begin;
    private plane.Point current;
    private final Movable object;

    public MouseMotionSpeed(Movable object)
    {
        this.begin = new plane.Point();
        this.current = new plane.Point();
        this.object = object;
    }

    public Vector getDirection()
    {
        return new Vector(begin,current);
    }

    public plane.Point getCurrent() { return this.current; }

    void calculateMotionCausedByUser()
    {
        if(object != null)
        {
            synchronized (this.object) {
                Vector vector = new Vector(object.getPoint().getX()-current.getX(),object.getPoint().getY()-current.getY());
                object.updateDirection(vector);
            }
        }
    }

    @Override
    public void run()
    {
        while (isWorking)
        {
            try
            {
                calculateMotionCausedByUser();
                this.begin = current;
                Thread.sleep(5);
            }
            catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    @Override
    public void mouseMoved(MouseEvent me)
    {
        try
        {
            this.current = Render.render.parseAwtPoint(me.getPoint());
            Thread.sleep(10);
//            System.out.println("> current: " + this.current);
        }
        catch (InterruptedException exception) { exception.printStackTrace(); }
    }

    @Override
    public void mouseDragged(MouseEvent me) {}
}
