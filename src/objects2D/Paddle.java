package objects2D;

import java.awt.*;

public class Paddle extends Shape
{
    Paddle(double width, double height)
    {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(int x, int y, int width, int height, Graphics g)
    {
        g.fillRect(x, y, (int)this.width*width, (int)this.height*height );
    }
}
