package render;

import plane.Area;
import plane.CoordinatePlane;
import plane.ObjectInCoordinateSystem;
import plane.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Render extends JPanel implements Runnable
{
    protected CoordinatePlane planeToRender;
    protected plane.Area cameraSight;
    protected Dimension screenResolution;
    public Render(CoordinatePlane plane, plane.Area camera, int xRes, int yRes)
    {
        this.planeToRender=plane;
        this.cameraSight=camera;
        this.screenResolution = new Dimension(xRes,yRes);
    }
    @Override
    public void run() {
//        double factor = 0.1;
        while(true)
        {
//            moveCamera(new plane.Vector(0.1,0.1));
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void moveCamera(plane.Vector directionOfMovement)
    {
        cameraSight.setOrigin(new Point(cameraSight.getOrigin().getX()+directionOfMovement.getEndPoint().getX(),cameraSight.getOrigin().getY()+directionOfMovement.getEndPoint().getY()));
    }
    protected int getOnScreenPoisiton(double singleCoordinateRelativeToClipping, double clippingLenght, int screenLength)
    {
        return (int)((singleCoordinateRelativeToClipping/clippingLenght*(double)screenLength));
    }
    protected int lenghtInPixelsOnScreen(double lengthOfObj, int screenLenght)
    {
        return (int)(lengthOfObj*(double) screenLenght);
    }
    protected void renderGraphics(Graphics g)
    {
        ArrayList<ObjectInCoordinateSystem> objectsSeenByCamera=planeToRender.getObjectsInArea(cameraSight);
        for(ObjectInCoordinateSystem objToRen: objectsSeenByCamera)
        {
            synchronized (objToRen)
            {
                Area coordinatesRelativeToCamera = new Area(objToRen.getPoint(),objToRen.getWidth(),objToRen.getHeight()).getRelativePosition(cameraSight);
                int yOriginOnScreen = getOnScreenPoisiton(coordinatesRelativeToCamera.getOrigin().getY(),cameraSight.getHeight(),this.screenResolution.height);
                int xOriginOnScreen = getOnScreenPoisiton(coordinatesRelativeToCamera.getOrigin().getX(),cameraSight.getWidth(),this.screenResolution.width);
                int widthInPixels = lenghtInPixelsOnScreen(coordinatesRelativeToCamera.getWidth(),(int)screenResolution.getWidth());
                int heightInPixels = lenghtInPixelsOnScreen(coordinatesRelativeToCamera.getHeight(),(int)screenResolution.getHeight());
                objToRen.getShape().draw(xOriginOnScreen,yOriginOnScreen,widthInPixels,heightInPixels,g);
            }
        }
    }
    int getAmountOfObjectsSeenBy(Area a)
    {
        return this.planeToRender.getObjectsInArea(a).size();
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        renderGraphics(g);
    }
    //TEST TEST TEST TEST TEST TEST TEST
    public static void main(String [] args)
    {
        JFrame some = new JFrame("dd");
        CoordinatePlane p = new CoordinatePlane();
        p.addObjectToPlane(new ObjectInCoordinateSystem(new objects2D.Puck(2),new Point(2,3)));
        p.addObjectToPlane(new ObjectInCoordinateSystem(new objects2D.Paddle(2.0,2.0),new Point(-1,-1)));
        Render r = new Render(p,new Area(new Point(-15,-2),100,100),500,500);
        some.add(r);
        some.setVisible(true);
        some.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        some.setSize(new Dimension(500,500));
        new Thread(r).start();
    }
    //END OF TEST                 END OF TEST
}
