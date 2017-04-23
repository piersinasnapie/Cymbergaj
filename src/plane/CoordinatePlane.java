package plane;

import java.util.ArrayList;

public class CoordinatePlane
{
    public static final double maxVectorLength = 0.08;
    protected ArrayList<ObjectInCoordinateSystem> objectsInPlane;

    public CoordinatePlane()
    {
        this.objectsInPlane=new ArrayList<>();
    }

    public void addObjectToPlane(ObjectInCoordinateSystem... objectsToAdd)
    {
        for(int i=0; i<objectsToAdd.length; i++)
        {
            this.objectsInPlane.add(objectsToAdd[i]);
        }
    }
    public ArrayList<ObjectInCoordinateSystem> getObjectsInPlane()
    {
        return this.objectsInPlane;
    }
  
    public ArrayList<ObjectInCoordinateSystem> getObjectsInArea(Area a)
    {
        ArrayList<ObjectInCoordinateSystem> objectsInArea = new ArrayList<>();
        for(ObjectInCoordinateSystem objectToExaminate : this.objectsInPlane)
        {
            if(Area.intersect(new Area(objectToExaminate.getPoint(), objectToExaminate.getWidth(), objectToExaminate.getHeight()), a))
            {
                objectsInArea.add(objectToExaminate);
            }
        }
        return objectsInArea;
    }
}
