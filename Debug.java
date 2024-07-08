import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import behavior.Player;

public class Debug{
    
    static Player player = Logic.player;

    static Dimension windowSize = new Dimension(300, 500);
    static JFrame win = new JFrame("Debug Menu");;
    static JLabel pX = new JLabel("x: " + player.position.x);
    static JLabel pY = new JLabel("y: " + player.position.y);
    static JButton reset = new JButton("Reset");

    static JLabel w_opt1 = new JLabel("Image:");
    static JLabel w_opt2 = new JLabel("Speed:");
    static JLabel w_opt3 = new JLabel("Range:");
    static JLabel w_opt4 = new JLabel("Damage:");

    public static void startDebug(){
        win.setDefaultCloseOperation(3);
        win.setLayout(null);


        pX.setBounds(0,0,150,15);
        win.add(pX);
        pY.setBounds(150,0,150,15);
        win.add(pY);

        w_opt1.setBounds(0,15,300,15);
        win.add(w_opt1);
        w_opt2.setBounds(0,30,300,15);
        win.add(w_opt2);
        w_opt3.setBounds(0,45,300,15);
        win.add(w_opt3);
        w_opt4.setBounds(0,60,300,15);
        win.add(w_opt4);

        reset.setBounds(10,75,90,15);
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Logic.killLoop();;
                Initialize.win.remove(Initialize.gameDisplay);
                Initialize.start();
            }
        });
        win.add(reset);

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
        if (player.weapons[player.weaponIndex].type.equals("Projectile")){
            w_opt1.setText("Image: "+player.weapons[player.weaponIndex].projectiles[0].image);
            w_opt2.setText("Speed: "+player.weapons[player.weaponIndex].projectiles[0].speed);
            w_opt3.setText("Range: "+player.weapons[player.weaponIndex].projectiles[0].range);
            w_opt4.setText("Damage: "+player.weapons[player.weaponIndex].projectiles[0].damage);
        } else if (player.weapons[player.weaponIndex].type.equals("Lasers")){
            //weaponImage.setText(player.weapons[player.weaponIndex].lasers[0].image);
        } else {
            //weaponImage.setText(player.weapons[player.weaponIndex].areas[0].image);
        }
    }
}