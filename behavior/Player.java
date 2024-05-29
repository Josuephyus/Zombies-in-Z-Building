package behavior;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import util.Point;

public class Player extends Entity{

    public Player(){ //Assign Default Values
        mHP = cHP = 100;
        damage = 1.0; speed = 280.0;
        size = 30;

        position = new Point(0,0);
    }

    public void drawMethod(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.translate(-size/2, -size/2);
        g2.fillOval(0,0,size,size);
        g2.translate(size/2, size/2);
    }
}
