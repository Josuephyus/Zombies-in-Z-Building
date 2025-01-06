package srcOG.data.interactables;

import java.awt.Graphics;
import java.awt.Color;

import behavior.Entity;
import util.*;

public class Interactable {
    
    public Point position = new Point(150, 0);
    public float radius = 70;

    public void interactedWith(Entity e){
        e.position = new Point(0, 0);
    }
    public void draw(Graphics g){
        int x = (int)(position.x);
        int y = -(int)(position.y);
        int rad = (int)radius;
        g.setColor(Color.RED);
        g.drawOval(x-rad, y-rad, rad*2, rad*2);
    }
}
