package data.interactables;

import java.awt.Graphics;
import java.awt.Color;

import behavior.*;
import util.*;

public class FireRate extends Interactable{
    
    public FireRate(){
        position = new Point(-150, 0);
        radius = 60;
    }

    public void interactedWith(Entity e){
        e.addBuff("Firerate");
    }

    public void draw(Graphics g){
        g.setColor(Color.BLUE);
        int x = (int)position.x;
        int y = -(int)position.y;
        int rad = (int)radius;
        g.drawOval(x - rad, y - rad, 2 * rad, 2 * rad);
    }
}
