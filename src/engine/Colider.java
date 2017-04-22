package engine;

import plane.Movable;

public class Colider
{
    public static Flank flankColiding(Movable source, Movable intruder)
    {
        final double multiplier = 0.9;

        double sourceX = source.getPoint().getX();
        double sourceY = source.getPoint().getY();
        double intruderX = intruder.getPoint().getX();
        double intruderY = intruder.getPoint().getY();

        double sourceWidth = source.getArea().getWidth();
        double sourceHeight = source.getArea().getHeight();
        double intruderWidth = intruder.getArea().getWidth();
        double intruderHeight = intruder.getArea().getHeight();

        double sourceEndX = sourceWidth + sourceX;
        double sourceEndY = sourceHeight + sourceY;
        double intruderEndX = intruderWidth + intruderX;
        double intruderEndY = intruderHeight + intruderY;

        boolean rightColiding = intruderX > sourceX+sourceWidth*multiplier && intruderX <= sourceEndX;
        boolean leftColiding = (intruderEndX < sourceEndX-sourceWidth*multiplier) && (intruderEndX >= sourceX);
        boolean bottomColiding = intruderY > sourceY+sourceHeight*multiplier && intruderY <= sourceEndY;
        boolean topColiding = (intruderEndY < sourceEndY-sourceHeight*multiplier) && (intruderEndY >= sourceY);

        boolean intruderYSource =  (intruderY <= sourceEndY && intruderY >= sourceY) || (intruderEndY <= sourceEndY && intruderEndY >= sourceY);
        boolean sourceYIntruder = (sourceY <= intruderEndY && sourceY >= intruderY) || (sourceEndY <= intruderEndY && sourceEndY >= intruderY);
        boolean intruderXSource = (intruderX <= sourceEndX && intruderX >= sourceX) || (intruderEndX <= sourceEndX && intruderEndY >= sourceX);
        boolean sourceXIntruder = (sourceX <= intruderEndX && sourceX >= intruderX) || (sourceEndX <= intruderEndX && sourceEndX >= intruderX);

        if(rightColiding && (intruderYSource || sourceYIntruder))
        {
            return Flank.RIGHT;
        }
        if(leftColiding && (intruderYSource || sourceYIntruder))
        {
            return Flank.LEFT;
        }
        if(bottomColiding && (intruderXSource || sourceXIntruder))
        {
            return Flank.BOTTOM;
        }
        if(topColiding && (intruderXSource || sourceXIntruder))
        {
            return Flank.TOP;
        }
        return null;
    }

    public static void main(String[] args)
    {

    }
}