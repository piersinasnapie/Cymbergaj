package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Test extends JFrame
{
    int x;
    int y;

    JPanel panel;

    Test()
    {
        this.x = 0;
        this.y = 0;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(400,300));

        panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g)
            {
                g.setColor(Color.black);
                g.fillOval(Test.this.x,Test.this.y,20,20);
            }
        };
        panel.setBackground(Color.cyan);
        panel.setSize(new Dimension(400,300));
        panel.addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                super.mouseMoved(e);
                Test.this.x = e.getX()-10;
                Test.this.y = e.getY()-10;
                Test.this.panel.repaint();
            }
        });

        add(panel);
    }

    public static void main(String[] args)
    {
        new Test().setVisible(true);
    }
}
