package objects2D;

import java.awt.*;

public class Obstacle extends Shape
{
    public Obstacle(double width, double height)
    {
        this(width,height,Color.magenta);
    }
    public Obstacle(double width, double height, Color shapeColor)
    {
        this.width = 0.5*width;
        this.height = 0.5*height;
        this.shapeColor=shapeColor;
    }
    @Override
    public void draw(int x, int y, int width, int height, Graphics g)
    {
        Color last = g.getColor();
        g.setColor(this.shapeColor);
        g.fillRect(x, y, width, height);
        g.setColor(last);
    }
}
