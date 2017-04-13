package objects2D;

import java.awt.*;

public class Paddle extends Shape
{
    public Paddle(double width, double height)
    {
        this(width,height,Color.magenta);
    }
    public Paddle(double width, double height, Color shapeColor)
    {
        this.width = width;
        this.height = height;
        this.shapeColor=shapeColor;
    }
    @Override
    public void draw(int x, int y, int width, int height, Graphics g)
    {
        Color last = g.getColor();
        g.setColor(this.shapeColor);
        g.fillRect(x, y, (int)this.width*width, (int)this.height*height );
        g.setColor(last);
    }
}
