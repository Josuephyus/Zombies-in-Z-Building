import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

public class Initialize {
    public static void main(String[] args){Initialize i = new Initialize(); i.start();}

    public static Integer scrW, scrH;
    public static Listener.Key uKL;
    public static Listener.Mouse uML;
    
    JFrame win; RunnablePanel game;

    public void start(){
        
        // Universals Setup
        scrW = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
        scrH = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
        uKL = new Listener.Key();
        uML = new Listener.Mouse();

        // Game Setup
        game = new RunnablePanel();
        game.setPreferredSize(new Dimension(scrW, scrH));
        game.setDoubleBuffered(true);
        game.addKeyListener(uKL);
        game.addMouseListener(uML);
        game.setFocusable(true);
        game.setBackground(Color.BLACK);

        // Window Setup
        win = new JFrame();
        win.setDefaultCloseOperation(3);
        win.setResizable(false);
        win.setTitle("Zombies in Z Building");
        win.add(game);
        win.pack();
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }

    public static class RunnablePanel extends JPanel implements Runnable{
        Thread thread;
        public RunnablePanel(){thread = new Thread(this);thread.start();}

        public void run(){
            while (thread != null){
                try {
                    Long millisecondsPerFrame = (long)(1000.0 / 30.0);
                    update(); repaint();
                    Thread.sleep(millisecondsPerFrame);
                } catch (InterruptedException e){
                    e.printStackTrace(); thread.interrupt();
                }
            }
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Artist.draw(g);
        }

        public void update(){
            Logic.update();
        }
    }
}