import javax.swing.*;

public class MainFrame extends JFrame
{
    public static final int width = 400;
    public static final int height = 600;

    MainPanel panel;

    public MainFrame()
    {
        setTitle("Cymbergaj - game");
        setSize(width,height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panel = new MainPanel();
        add(panel);
    }

    public static void main(String[] args)
    {
        new MainFrame().setVisible(true);
    }
}
