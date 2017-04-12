package objects2D;

import java.awt.*;

public abstract class Shape
{
    protected double width;
    protected double height;
    public abstract void draw(int x, int y, int width, int hight, Graphics g);
}
