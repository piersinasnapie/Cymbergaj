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
    private final Long secondInNano = 1000_000_000l;

    protected CoordinatePlane planeToRender;
    protected plane.Area cameraSight;
    protected Dimension screenResolution;


    protected int fpsWantedByUsr;
    protected int fpsGeneratedWithinSecond;
    protected double lastAmountOfFPS;
    protected Long dateOfLastSecond;
    protected Long currentFrameRenderingDate;

    protected Boolean shouldDrawFPS;
    protected Boolean shouldRun;

    public static Render render;

    public Render(CoordinatePlane plane, plane.Area camera, int xRes, int yRes, int fps,boolean shouldDrawFPS)
    {
        render = this;
        this.planeToRender=plane;
        this.cameraSight=camera;
        this.screenResolution = new Dimension(xRes,yRes);

        this.fpsWantedByUsr=fps;
        this.dateOfLastSecond=System.nanoTime();
        this.shouldRun=true;
        this.shouldDrawFPS=shouldDrawFPS;
    }

    public Render(CoordinatePlane plane, plane.Area camera, int xRes, int yRes)
    {
        this(plane,camera,xRes,yRes,60,true);
    }


    @Override
    public void run() {
        while(shouldRun)
        {
            try {
                generateGraphicsWithWantedFPS();
            } catch (RenderException e) {
                e.printStackTrace();
                shouldRun=false;
            }
        }
    }
    protected void generateGraphicsWithWantedFPS() throws RenderException
    {
        startTimeMeasurement();
        repaint();
        waitIfNeed();
        updateFPSData();
    }
    void startTimeMeasurement()
    {
        this.currentFrameRenderingDate=System.nanoTime();
    }
    void waitIfNeed() throws RenderException
    {
        Long timeElapsedToRenderAFrame = System.nanoTime()-currentFrameRenderingDate;
        if(timeElapsedToRenderAFrame*(fpsWantedByUsr/secondInNano)>secondInNano)
        {
            throw new RenderException("too little time to generate one frame");
        }
        else
        {
           if(timeElapsedToRenderAFrame>secondInNano)
           {
              throw new RenderException("too little time to generate frames");
           }
           int amountOfRemainingFramesToRenderWithinSecond = fpsWantedByUsr-fpsGeneratedWithinSecond;
           Long remainingTime=secondInNano-(System.nanoTime()-dateOfLastSecond);
           remainingTime-=((fpsWantedByUsr/secondInNano)*amountOfRemainingFramesToRenderWithinSecond*timeElapsedToRenderAFrame);
           if(amountOfRemainingFramesToRenderWithinSecond>0)
           {
               remainingTime/=amountOfRemainingFramesToRenderWithinSecond;
           }
           try {
                Thread.sleep(Math.abs(remainingTime/1000000));
           }catch (InterruptedException e) {
                e.printStackTrace();
           }
        }
    }
    void updateFPSData()
    {
        Long deltaFromLastFrame = System.nanoTime()-dateOfLastSecond;
        if(deltaFromLastFrame>secondInNano)
        {
            lastAmountOfFPS=((double)fpsGeneratedWithinSecond)/((double)deltaFromLastFrame/secondInNano);
            fpsGeneratedWithinSecond=0;
            dateOfLastSecond=System.nanoTime();
        }
        fpsGeneratedWithinSecond+=1;
    }
    void drawFPS(boolean shouldDrawFPS, Graphics g)
    {
        if(shouldDrawFPS)
        {
            g.drawString(String.format("%.2f FPS",lastAmountOfFPS),10,20);
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
        drawFPS(this.shouldDrawFPS,g);
    }

    public plane.Point parseAwtPoint(java.awt.Point point)
    {
        double x = point.getX();
        double y = point.getY();

        double xCameraPosition = x/screenResolution.getWidth()*cameraSight.getWidth();
        double yCameraPosition = y/screenResolution.getHeight()*cameraSight.getHeight();

        return new plane.Point(xCameraPosition+cameraSight.getOrigin().getX(), yCameraPosition+cameraSight.getOrigin().getY());
    }

    public java.awt.Point parsePlanePoint(plane.Point point)
    {
        double x = point.getX();
        double y = point.getY();

        int xOnScreen = (int)((x/cameraSight.getWidth()*(double)screenResolution.width));
        int yOnScreen = (int)((y/cameraSight.getWidth()*(double)screenResolution.height));

        return new java.awt.Point(xOnScreen,yOnScreen);
    }

//    public MouseMotionSpeed getMouseListener()
//    {
//        return this.mouseMotionSpeed;
//    }

    public static void main(String[] args)
    {
        Render r = new Render(new CoordinatePlane(),new Area(new Point(),20,20),600,600);
        plane.Point planePoint = new Point(0,0);
        java.awt.Point awtPoint = r.parsePlanePoint(planePoint);
        System.out.println(awtPoint);
        planePoint = new Point(20,20);
        awtPoint = r.parsePlanePoint(planePoint);
        System.out.println(awtPoint);
        System.out.println(r.parseAwtPoint(awtPoint));
    }
}


class RenderException extends Exception
{
    protected String messageToUser;
    RenderException(String messageToUser)
    {
        this.messageToUser=messageToUser;
    }
    RenderException()
    {
        this.messageToUser="Undefined Render Exception";
    }
    @Override
    public String getMessage()
    {
        return this.messageToUser;
    }
}