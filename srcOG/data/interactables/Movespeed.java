package srcOG.data.interactables;

import java.awt.Graphics;
import java.awt.Color;

import behavior.*;
import util.*;

public class Movespeed extends Interactable{
    
    public Movespeed(){
        position = new Point(1150, 600);
        radius = 60;
    }

    public void interactedWith(Entity e){
        e.addBuff("MOVESPEED_UP");
    }

    public void draw(Graphics g){
        g.setColor(Color.CYAN);
        int x = (int)position.x;
        int y = -(int)position.y;
        int rad = (int)radius;
        g.drawOval(x - rad, y - rad, 2 * rad, 2 * rad);
    }
}
