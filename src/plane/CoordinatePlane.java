package plane;


import java.util.ArrayList;




public class CoordinatePlane
{
    protected ArrayList<ObjectInCoordinateSystem> objectsInPlane;
    CoordinatePlane()
    {
        this.objectsInPlane=new ArrayList<>();
    }
    void addObjectToPlane(ObjectInCoordinateSystem objectToAdd){
        this.objectsInPlane.add(objectToAdd);
    }
    ArrayList<ObjectInCoordinateSystem> getObjectsInPlane()
    {
        return this.objectsInPlane;
    }

}
