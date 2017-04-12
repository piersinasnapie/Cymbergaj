package plane;

import java.util.ArrayList;

public class CoordinatePlane
{
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
        for(ObjectInCoordinateSystem objectOnPlane : this.objectsInPlane)
        {
            //if()
        }
        return objectsInArea;
    }
}
