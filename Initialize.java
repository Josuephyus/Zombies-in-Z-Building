import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

public class Initialize {
    public static void main(String[] args){

        //Window setup
        win.setUndecorated(true);
        win.setDefaultCloseOperation(3);
        win.setResizable(false);
        win.setTitle("Zombies in Z Building");
        
        Initialize.start();
    }

    public static Integer scrW = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    public static Integer scrH = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;;
    public static Listener.gameKey gKL = new Listener.gameKey();
    public static Listener.gameMouse gML = new Listener.gameMouse();
    
    public static JFrame win = new JFrame();
    public static JPanel gameDisplay;

    public static void start(){

        // Start Game
        setupGameDisplay();

        Logic.start();

        win.add(gameDisplay);
        win.pack();

        win.setSize(scrW, scrH);
        win.setLocation(scrW/2, scrH/2);
        win.setVisible(true);

        Debug.startDebug();
    }

    public Component[] startMenu(){
        Component[] returnthis = new Component[0];
        return returnthis;
    }

    public static void setupGameDisplay(){
        System.out.println("test 1");
        gameDisplay = new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Artist.draw(g);
            }
        };
        System.out.println("test 2");
        gameDisplay.setPreferredSize(new Dimension(scrW, scrH));
        gameDisplay.setDoubleBuffered(true);
        gameDisplay.addKeyListener(gKL);
        gameDisplay.addMouseListener(gML);
        gameDisplay.setFocusable(true);
        gameDisplay.setBackground(Color.BLACK);
    }
}