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
    ArrayList<ObjectInCoordinateSystem> getObjectsInArea(Area a)
    {
        ArrayList<ObjectInCoordinateSystem> objectsInArea = new ArrayList<>();
        for(ObjectInCoordinateSystem objectToExaminate : this.objectsInPlane)
        {
            if(Area.intersect(new Area(objectToExaminate.getPoint(),objectToExaminate.getWidth(),objectToExaminate.getHeight()),a)==true)
            {
                objectsInArea.add(objectToExaminate);
            }
        }
        return objectsInArea;
    }
}
