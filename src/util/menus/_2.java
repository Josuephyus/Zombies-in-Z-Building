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
                byte frame_count = 0;
                long frame_end = System.currentTimeMillis() + 1000;
                long start, end;
                while ( true ) {
                    start = System.currentTimeMillis();
                    frame_count++;

                    Logic.update();
                    self.repaint();

                    if (frame_end <= System.currentTimeMillis()) {
                        System.out.println("FPS: " + frame_count);
                        frame_count = 0;
                        frame_end = System.currentTimeMillis() + 1000;
                    }
                    end = System.currentTimeMillis();


                   if (end - start < 15)
                        Thread.sleep(15 - (end - start));
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
