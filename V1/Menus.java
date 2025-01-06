package srcOG;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.*;

public class Menus{

    public static JPanel game(){
        return new JPanel(){
            {
                setBounds(0,0,Initialize.scrW,Initialize.scrH);
                setBackground(Color.BLACK);
            }
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Artist.draw(g);
            }
        };
    }
    public static JPanel loading(){
        return Menus.game();
    }


    /*
     * This is the start screen, officially called landing. The landing screen
     * will support settings, credits, play, and exit, but for testing purposes,
     * it is only gameplay
     */
    public static JPanel landing(){
        return new JPanel(){{
            setLayout(null);
            setBackground(java.awt.Color.BLACK);
            setBounds(0,0,Initialize.scrW, Initialize.scrH);

            JPanel t = this;

            Integer cW = Initialize.scrW / 2; Integer cH = Initialize.scrH / 2;

            add(new JButton("Start Game"){
                {
                    setBounds(cW - 50, cH - 15, 100,30);
                    addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            Initialize.win.remove(t);
                            Initialize.win.add(Menus.loading());
                            Artist.loadImages();
                            Logic.start();
                            Debug.startDebug();
                            Initialize.win.requestFocus();
                        }
                    });
                }
            });
        }};
    }
}
