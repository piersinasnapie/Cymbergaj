package plane;


import java.util.ArrayList;




public class CoordinatePlane
{
    protected ArrayList<ObjectInCoordinateSystem> objectsInPlane;
    public CoordinatePlane()
    {
        this.objectsInPlane=new ArrayList<>();
    }
    public void addObjectToPlane(ObjectInCoordinateSystem objectToAdd){
        this.objectsInPlane.add(objectToAdd);
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
            if(Area.intersect(new Area(objectToExaminate.getPoint(),objectToExaminate.getWidth(),objectToExaminate.getHeight()),a)==true)
            {
                objectsInArea.add(objectToExaminate);
            }
        }
        return objectsInArea;
    }
}
