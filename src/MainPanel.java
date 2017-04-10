import javax.swing.*;
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
//        paddle.start();

        addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                super.mouseMoved(e);
                paddle.updatePosition(e.getX()+Paddle.width/2,e.getY()+Paddle.height/2);
                if(ball.isTouchingPaddle(e.getX(),e.getY()))
                {
                    ball.changeDirection();
                }
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

        g.fillRect(paddle.x- Paddle.width,paddle.y- Paddle.height, Paddle.width, Paddle.height);
    }
}
