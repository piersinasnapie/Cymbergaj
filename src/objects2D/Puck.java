package objects2D;

import java.awt.*;

public class Puck extends Shape
{
    public static final double radius = 10;

    Puck()
    {
        this.width = radius;
        this.height = radius;
    }

    @Override
    public void draw(int x, int y, int width, int hight, Graphics g)
    {
        g.fillOval(x, y, (int)this.width*width, (int)this.height*hight);
    }
}
