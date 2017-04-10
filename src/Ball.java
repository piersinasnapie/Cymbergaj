public class Ball extends Thread
{
    final int radius = 40;
    private boolean isWorking = true;

    int x;
    int y;

    int xTransform = 1;
    int yTransform = 1;

    private int xSpawn = 100;
    private int ySpawn = 100;

    MainPanel panel;

    Ball(MainPanel panel)
    {
        this.panel = panel;
        this.x = xSpawn;
        this.y = ySpawn;
    }

    void move()
    {
        this.x += xTransform;
        this.y += yTransform;
    }

    boolean endOfX()
    {
        return x < 0 || x > panel.width-radius;
    }

    boolean endOfY()
    {
        return y < 0 || y > panel.height-1.5*radius;
    }

    void changeDirection()
    {
        if(endOfX()) xTransform *= -1;
        if(endOfY()) yTransform *= -1;
    }

    @Override
    public void run()
    {
        while(isWorking)
        {
            changeDirection();
            move();
            panel.repaint();
            try
            {
                Thread.sleep(2);
            }
            catch (InterruptedException e){ e.printStackTrace(); }
        }
    }
}
