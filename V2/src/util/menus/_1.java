package util.menus;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Artist;
import util.Logic;
import util.Menus;

public class _1 extends JPanel{
    
    JFrame mainWindow;
    JPanel self;

    public _1(JFrame window) {
        super();
        System.out.println("Loading Screen");
        
        mainWindow = window;
        self = this;

        mainWindow.requestFocus();

        thread = new Thread(runnable);
        thread.start();
    }
    
    Thread thread;
    Runnable runnable = new Runnable() {
        public void run() {
            Artist.LoadImages();
            Logic.Start();

            mainWindow.remove(self);
            mainWindow.add(Menus.getMenu("InGame"));
        }
    };
}
