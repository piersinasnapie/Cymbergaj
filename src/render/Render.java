package render;

import plane.*;
import plane.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Render extends JPanel implements Runnable
{
    protected CoordinatePlane planeToRender;
    protected plane.Area cameraSight;
    protected Dimension screenResolution;
    Render(CoordinatePlane plane, plane.Area camera, int xRes, int yRes)
    {
        this.planeToRender=plane;
        this.cameraSight=camera;
        this.screenResolution = new Dimension(xRes,yRes);
    }
    @Override
    public void run() {
        double factor = 0.1;
        int iter=0;
        while(true)
        {

            if(Math.abs(iter)>=50)
            {
                factor*=-1;
                iter=0;
            }
            cameraSight.setOrigin(new plane.Point(cameraSight.getOrigin().getX()+factor,cameraSight.getOrigin().getY()));
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            iter++;
        }
    }

    protected void renderGraphics(Graphics g)
    {
        ArrayList<ObjectInCoordinateSystem> objectsSeenByCamera=planeToRender.getObjectsInArea(cameraSight);
        for(ObjectInCoordinateSystem objToRen: objectsSeenByCamera)
        {
            Area coordinatesRelativeToCamera = new Area(objToRen.getPoint(),objToRen.getWidth(),objToRen.getHeight()).getRelativePosition(cameraSight);
            int xOriginOnScreen = (int)((coordinatesRelativeToCamera.getOrigin().getX()/cameraSight.getWidth())*(double)this.screenResolution.getWidth());
            int yOriginOnScreen = (int)((coordinatesRelativeToCamera.getOrigin().getY()/cameraSight.getHeight())*(double)this.screenResolution.getHeight());
            int widthInPixels = (int)(coordinatesRelativeToCamera.getWidth()*(double)this.screenResolution.getWidth());
            int heightInPixels = (int)(coordinatesRelativeToCamera.getHeight()*(double)this.screenResolution.getHeight());
            objToRen.getShape().draw(xOriginOnScreen,yOriginOnScreen,widthInPixels,heightInPixels,g);
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
        p.addObjectToPlane(new ObjectInCoordinateSystem(new plane.Sprite(new objects2D.Puck(2)),new Point(2,3)));
        p.addObjectToPlane(new ObjectInCoordinateSystem(new plane.Sprite(new objects2D.Paddle(2.0,2.0)),new Point(-1,-1)));
        Render r = new Render(p,new Area(new Point(-2,1.0),10,10),500,500);
        some.add(r);
        some.setVisible(true);
        some.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        some.setSize(new Dimension(500,500));
        new Thread(r).start();
    }
    //END OF TEST                 END OF TEST
}
