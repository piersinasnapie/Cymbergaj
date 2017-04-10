import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPanel extends JPanel
{
    public final int width = MainFrame.width;
    public final int height = MainFrame.height;

    Ball ball;
    Paddle paddle;

    MainPanel()
    {
        setSize(new Dimension(MainFrame.width,MainFrame.height));
        setBackground(new Color(61, 24, 78));

        paddle = new Paddle(MainPanel.this);
        paddle.start();

        addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                super.mouseMoved(e);
                paddle.updatePosition(e.getX()+paddle.width/2,e.getY()+paddle.height/2);
            }
        });

        ball = new Ball(this);
        ball.start();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(ball.x,ball.y,ball.radius,ball.radius);

        g.fillRect(paddle.x-paddle.width,paddle.y-paddle.height,paddle.width,paddle.height);
    }
}
