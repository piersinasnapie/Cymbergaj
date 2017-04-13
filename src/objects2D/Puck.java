package objects2D;

import java.awt.*;

public class Puck extends Shape
{
    public Puck(double radius)
    {
        this(radius,Color.blue);
    }
    public Puck(double radius, Color shapeColor)
    {
        this.width = radius;
        this.height = radius;
        this.shapeColor = shapeColor;
    }
    @Override
    public void draw(int x, int y, int width, int height, Graphics g)
    {
        Color last = g.getColor();
        g.setColor(this.shapeColor);
        g.fillOval(x, y, width, height);
        g.setColor(last);
    }
}
