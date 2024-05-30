package behavior;

import java.awt.Color;
import java.awt.Graphics;

import util.Point;

public class Zombie extends Entity{

    static Integer nextID = 0;


    public Zombie(){
        mHP = cHP = 100;
        ID = nextID; nextID++;
        size = 20; speed = 175.0;
        damage = 12.0;

        position = new Point(0,0);
    }

    public void drawMethod(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(-size/2, -size/2, size, size);
    }

    public void update(Point player, Integer time, Integer tickPerSecond){
        Double theta = position.directionTo(player);
        position.x += Math.cos(theta) * speed * time / (100 * tickPerSecond);
        position.y += Math.sin(theta) * speed * time / (100 * tickPerSecond);
    }
}
