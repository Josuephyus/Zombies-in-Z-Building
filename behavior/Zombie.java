package behavior;

import java.awt.Color;
import java.awt.Graphics;

import util.Point;

public class Zombie extends Entity{

    static Integer nextID = 0;
    static Integer range = 10;
    static Double attackCD, attackTimer;

    public Zombie(){
        mHP = cHP = 100;
        ID = nextID; nextID++;
        size = 20; speed = 175.0;
        damage = 12.0;

        attackCD = 1.0;
        attackTimer = 0.0;

        position = new Point(0,0);
    }

    public void drawMethod(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(-size/2, -size/2, size, size);
    }

    public Integer update(Point player, Integer time, Integer tickPerSecond){
        Double theta = position.directionTo(player);
        position.x += Math.cos(theta) * speed * time / (100 * tickPerSecond);
        position.y += Math.sin(theta) * speed * time / (100 * tickPerSecond);

        if (!isAlive()){return 0;}

        if (player.distance(position) < range + size && attackTimer <= 0){
            attackTimer = attackCD - ((double)time / (100 * tickPerSecond));
            return (int)Math.round(damage);
        } else {
            attackTimer -= (double)time / (100 * tickPerSecond);
            if (attackTimer < 0) attackTimer = 0.0;
            return 0;
        }
    }

    public boolean isAlive(){
        return (cHP > 0);
    }
}
