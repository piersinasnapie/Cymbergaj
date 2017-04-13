package objects2D;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape
{
    public double width;
    public double height;
    Color shapeColor;
    public abstract void draw(int x, int y, int width, int height, Graphics g);
}
