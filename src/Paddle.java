public class Paddle extends Thread
{
    boolean isWorking = true;

    public static final int width = 100;
    public static final int height = 10;

    int leftUpper = 100;
    int rightDown = 400;

    int x;
    int y;

    private MainPanel panel;

    Paddle(MainPanel panel)
    {
        this.panel = panel;
        x = leftUpper;
        y = rightDown;
    }

    void updatePosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public void run()
    {
        while(isWorking)
        {
            panel.repaint();
        }
    }
}
