package behavior;

import java.awt.Color;
import java.awt.Graphics;

import util.Point;

public class Zombie extends Entity{

    static Integer nextID = 0;
    static Integer range = 10;
    static Double attackCD, attackTimer;

    public Zombie(){
        mHP = cHP = 100f;
        ID = nextID; nextID++;
        size = 20; speed = 105f;
        damage = 12f;

        attackCD = 2.0;
        attackTimer = 0.0;

        position = new Point(300,0);
    }

    public void drawMethod(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(-size/2, -size/2, size, size);
    }

    public Integer update(Point player, Double totalTime){
        Double theta = position.directionTo(player);
        position.x += Math.cos(theta) * speed * totalTime;
        position.y += Math.sin(theta) * speed * totalTime;

        if (!isAlive()){return 0;}

        if (player.distance(position) < range + size && attackTimer <= 0){
            attackTimer = attackCD;
            return (int)Math.round(damage);
        } else {
            attackTimer -= totalTime;
            if (attackTimer < 0) attackTimer = 0.0;
            return 0;
        }
    }

    public boolean isAlive(){
        return (cHP > 0);
    }
}
