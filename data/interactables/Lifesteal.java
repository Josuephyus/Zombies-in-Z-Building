package data.interactables;

import java.awt.Graphics;
import java.awt.Color;

import behavior.*;
import util.*;

public class Lifesteal extends Interactable{
    
    public Lifesteal(){
        position = new Point(150, -100);
        radius = 60;
    }

    public void interactedWith(Entity e){
        e.addBuff("Lifesteal");
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        int x = (int)position.x;
        int y = -(int)position.y;
        int rad = (int)radius;
        g.drawOval(x - rad, y - rad, 2 * rad, 2 * rad);
    }
}
