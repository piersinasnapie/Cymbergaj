package objects2D;

import java.awt.*;

public class Paddle extends Shape
{
    Paddle()
    {
        this.width = 20;
        this.height = 5;
    }

    @Override
    public void draw(int x, int y, int width, int height, Graphics g)
    {
        g.fillRect(x, y, (int)this.width*width, (int)this.height*height );
    }
}
