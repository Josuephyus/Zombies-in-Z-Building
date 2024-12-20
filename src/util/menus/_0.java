package util.menus;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;

import util.Menus;

public class _0 extends JPanel {
    
    JFrame mainWindow;
    JButton BUT_Start;

    JPanel self;

    public _0(JFrame window) {
        super();
        System.out.println("Splash Screen");

        mainWindow = window;
        self = this;

        setLayout(null);
        setBackground(new Color(0.1f, 0.1f, 0.2f));
        setBounds(0, 0, mainWindow.getWidth(), mainWindow.getHeight());

        int cW = mainWindow.getWidth() / 2;
        int cH = mainWindow.getHeight() / 2;

        BUT_Start = new JButton("Start Game");
        BUT_Start.setBounds(cW - 50, cH - 15, 100, 30);
        BUT_Start.addActionListener(START_GAME);
        this.add(BUT_Start);
    }

    public void paintComponent(Graphics g) {
        BUT_Start.setBounds(
            (mainWindow.getWidth() / 2) - 50,
            (mainWindow.getHeight() / 2) - 15,
            100,
            30
        );

        super.paintComponent(g);
    }

    ActionListener START_GAME = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            mainWindow.remove(self);
            mainWindow.add(Menus.getMenu("Loading"));
        }
    };
}
