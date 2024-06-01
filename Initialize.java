import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

import behavior.Player;

public class Initialize {
    public static void main(String[] args){Initialize i = new Initialize(); i.start();}

    public static Integer scrW, scrH;
    public static Listener.Key uKL;
    public static Listener.Mouse uML;
    
    public static JFrame win;
    public static RunnablePanel game;

    public void start(){
        
        // Universals Setup
        scrW = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        scrH = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        new Listener();
        uKL = new Listener.Key();
        uML = new Listener.Mouse();
        // Window Setup
        win = new JFrame();
        win.setDefaultCloseOperation(3);
        win.setResizable(false);
        win.setUndecorated(true);
        win.setTitle("Zombies in Z Building");
        //startMenu();
        setupGame();
        win.add(game); win.pack();
        win.setLocationRelativeTo(null);
        win.setVisible(true);

        game.start();
    }

    public Component[] startMenu(){
        Component[] returnthis = new Component[0];
        return returnthis;
    }

    public void setupGame(){
        game = new RunnablePanel();
        game.setPreferredSize(new Dimension(scrW, scrH));
        game.setDoubleBuffered(true);
        game.addKeyListener(uKL);
        game.addMouseListener(uML);
        game.setFocusable(true);
        game.setBackground(Color.BLACK);
    }

    public void startGame(){
        game.start();
    }

    public static class RunnablePanel extends JPanel implements Runnable{
        Thread thread;
        public Player p;

        public RunnablePanel(){
            Artist.loadImages();
            p = new Player();
            thread = new Thread(this);
        }
        public void start(){
            thread.start();
        }

        public void run(){
            while (thread != null){
                try {
                    Long millisecondsPerFrame = (long)(1000.0 / 60.0);
                    update(4);
                    if (!Listener.check("SlowTime")){
                        update(16);
                    }
                    repaint();
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

        public void update(Integer time){
            Logic.update(time);
        }
    }
}