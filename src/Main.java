import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;

import util.Listener;
import util.Artist;
import util.Logic;
import util.Menus;

public class Main {

    final int SCREEN_WIDTH =
        (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    final int SCREEN_HEIGHT =
        (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    public int win_w = SCREEN_WIDTH / 2;
    public int win_h = SCREEN_HEIGHT / 2;

    public JFrame window;
    public JPanel display;
    public Listener listeners;
    
    public static void main(String[] args) { new Main(); }


    public Main() {
        SetupWindow();
        SetupListener();
        StartGame();
    }


    void SetupWindow() {
        window = new JFrame("Zombies in Z Building!");

        window.setLayout(null);
        window.setDefaultCloseOperation(3);
        window.setSize(win_w + 14, win_h + 37);
        window.setLocation(SCREEN_WIDTH / 4, SCREEN_HEIGHT / 4);
        window.setResizable(true);
        window.setVisible(true);

        Menus.setWindow(window);
    }

    void SetupListener() {
        listeners = new Listener();
        listeners.setWindow(window);

        listeners.Start();
        window.addKeyListener(listeners.KL);
        window.addMouseListener(listeners.ML);
    }

    void StartGame() {
        Logic.setListener(listeners);
        Artist.setListener(listeners);
        Artist.setWindow(window);
        display = Menus.getMenu("Splash");
        window.add(display);
        window.repaint();
    }
}