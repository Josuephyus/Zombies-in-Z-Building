package srcOG;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Initialize {
    public static Integer scrW = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    public static Integer scrH = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;;
    
    public static JFrame win = new JFrame();
    public static JPanel gameDisplay;
    public static JPanel startScreen = Menus.landing();
    
    public static void main(String[] args){
        //Window setup
        win.setUndecorated(true);
        win.setLayout(null);
        win.setDefaultCloseOperation(3);
        win.setResizable(false);
        win.setTitle("Zombies in Z Building");
        win.addKeyListener(new Listener.gameKey());
        win.addMouseListener(new Listener.gameMouse());
        
        // Load Keybinds
        Listener.start();

        Initialize.start();
    }

    public static void start(){

        // Adds the start menu
        win.add(startScreen);

        
        win.setSize(scrW, scrH);
        win.setLocation(scrW / 2, scrH / 2);
        win.setVisible(true);
    }
}