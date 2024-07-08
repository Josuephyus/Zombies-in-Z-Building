import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;

import behavior.Entity;
import behavior.Player;

public class Debug{

    static Dimension windowSize = new Dimension(300, 500);
    static JLabel pX, pY;
    static JLabel pWeapon;
    static JButton reset;

    static Player player;

    public static void startDebug(){
        JFrame win = new JFrame("Debug Menu");
        win.setDefaultCloseOperation(3);
        win.setLayout(null);

        player = Initialize.game.p;
        pX = new JLabel("x: " + player.position.x);
        pX.setBounds(0,0,150,30);
        win.add(pX);
        pY = new JLabel("y: " + player.position.y);
        pY.setBounds(150,0,150,30);
        win.add(pY);


        pWeapon = new JLabel(player.weapons[player.weaponIndex].toString());
        pWeapon.setBounds(0,30,300,30);
        win.add(pWeapon);

        win.pack();
        win.setSize(windowSize);
        win.setVisible(true);

        Runnable update = new Runnable(){
            Thread t = new Thread(this);
            {t.start();}
            public void run(){
                while (t != null){
                    try {
                        Debug.update();
                        Thread.sleep(50);
                    } catch (InterruptedException e){
                        System.out.println("Debug failed");
                    }
                }
            }
        };
    }

    public static void update(){
        pX.setText("x: " + player.position.x);
        pY.setText("y: " + player.position.y);
        pWeapon.setText(player.weapons[player.weaponIndex].toString());
    }
}