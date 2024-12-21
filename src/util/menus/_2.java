package util.menus;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Artist;
import util.Logic;

public class _2 extends JPanel {
    
    JFrame mainWindow;
    JPanel self;

    public _2(JFrame window) {
        super();
        System.out.println("In Game");
        
        mainWindow = window;
        self = this;
        
        setBounds(0, 0, mainWindow.getWidth(), mainWindow.getHeight());

        gameLoop = new Thread(gameRun);
        gameLoop.start();
    }

    Thread gameLoop;
    Runnable gameRun = new Runnable() {
        public void run() {
            try {
                long start, end;
                while ( true ) {
                    start = System.currentTimeMillis();

                    self.repaint();
                    Logic.update();

                    end = System.currentTimeMillis();

                   if (end - start < 16)
                        Thread.sleep(16 - (end - start));
                }    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void paintComponent(Graphics g) {
        setBounds(0, 0, mainWindow.getWidth(), mainWindow.getHeight());
        super.paintComponent(g);

        Artist.draw(g);
    }
}
