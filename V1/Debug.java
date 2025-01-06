package srcOG;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Debug{

    static Dimension windowSize = new Dimension(300, Initialize.scrH);

    static JButton reset = new JButton("Reset");

    static JFrame win = new JFrame("Debug Menu");
    static JLabel xLab = new JLabel("x:");
        static JTextField xText = new JTextField();
    static JLabel yLab = new JLabel("y:");
        static JTextField yText = new JTextField();

    static JLabel w_opt1 = new JLabel();
        static JTextField w_txt1 = new JTextField();
    static JLabel w_opt2 = new JLabel();
        static JTextField w_txt2 = new JTextField();
    static JLabel w_opt3 = new JLabel();
        static JTextField w_txt3 = new JTextField();
    static JLabel w_opt4 = new JLabel();
        static JTextField w_txt4 = new JTextField();

    static JLabel lastShot = new JLabel("Last Shot Fired: ");
    static JLabel lastShotValues = new JLabel();

    public static void startDebug(){
        if (!win.isDisplayable())
            win.setUndecorated(true);
        win.setDefaultCloseOperation(3);
        win.setLayout(null);
        Integer windowWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        Integer windowHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        win.setLocation((windowWidth + Initialize.scrW) / 2, (windowHeight-Initialize.scrH) / 2);

        reset.setBounds(10,10,90,15);
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Logic.killLoop();;
                Initialize.win.remove(Initialize.gameDisplay);
                Initialize.start();
            }
        });
        win.add(reset);

        xLab.setBounds(10,25,20,15);
        win.add(xLab);
            xText.setBounds(30,25,260,15);
            win.add(xText);
        yLab.setBounds(10,40,20,15);
        win.add(yLab);
            yText.setBounds(30,40,260,15);
            win.add(yText);

        w_opt1.setBounds(10,55,60,15);
        win.add(w_opt1);
            w_txt1.setBounds(70,55,210,15);
            win.add(w_txt1);
        w_opt2.setBounds(10,70,60,15);
        win.add(w_opt2);
            w_txt2.setBounds(70,70,210,15);
            win.add(w_txt2);
        w_opt3.setBounds(10,85,60,15);
        win.add(w_opt3);
            w_txt3.setBounds(70,85,210,15);
            win.add(w_txt3);
        w_opt4.setBounds(10,100,60,15);
        win.add(w_opt4);
            w_txt4.setBounds(70,100,210,15);
            win.add(w_txt4);

        lastShot.setBounds(40, 115, 230, 15);
        win.add(lastShot);
        lastShotValues.setBounds(10, 135, 280, 15);
        win.add(lastShotValues);
    
        win.pack();
        win.setSize(windowSize);
        win.setVisible(true);

        Runnable update = new Runnable(){
            public void run(){
                try {
                    while (true){
                        Debug.update();
                        Thread.sleep(50);
                    }
                } catch (InterruptedException e){
                    System.out.println("Debug failed");
                }
            }
        };
        Thread t = new Thread(update);
        t.start();
    }

    public static void update(){
        xText.setText(((Double)Logic.player.position.x).toString());
        yText.setText(((Double)Logic.player.position.y).toString());

        w_opt1.setText("Image: ");
        w_txt1.setText(Logic.player.weapons[Logic.player.weaponIndex].create.image);
        w_opt2.setText("Speed: ");
        w_txt2.setText(((Integer)Logic.player.weapons[Logic.player.weaponIndex].create.speed).toString());
        w_opt3.setText("Range: ");
        w_txt3.setText(((Float)Logic.player.weapons[Logic.player.weaponIndex].create.range).toString());
        w_opt4.setText("Damage: ");
        w_txt4.setText(((Integer)Logic.player.weapons[Logic.player.weaponIndex].create.damage).toString());
    }
}