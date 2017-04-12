package objects2D;

import java.awt.*;

public abstract class Shape
{
    public double width;
    public double height;
    public abstract void draw(int x, int y, int width, int height, Graphics g);
}
