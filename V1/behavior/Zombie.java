package srcOG.behavior;

import java.awt.Color;
import java.awt.Graphics;

import util.Point;

public class Zombie extends Entity{

    static Integer range = 10;
 
    private float _speed = 105f;
    public int size = 20;
    public float damage = 12f;

    private static float attackCD = 2.0f;
    private static float attackTimer = 0.0f;


    public Zombie(int x, int y){
        position = new Point(x, y);
        HP = new float[]{100f, 100f, 0f};
        AdvanceID(this);
    }

    public void drawMethod(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(-size/2, -size/2, size, size);
    }

    public float update(Point player, float totalTime){
        float theta = position.directionTo(player);

        int microsteps = 4;
        float xM = (float)Math.cos(theta) * _speed * totalTime / (float)microsteps;
        float yM = (float)Math.sin(theta) * _speed * totalTime / (float)microsteps;

        for (int i = 0; i < microsteps; i++){
            if (map.isValid(new Point(position.x + xM, position.y))){
                position.x += xM;
            }
            if (map.isValid(new Point(position.x, position.y + yM))){
                position.y += yM;
            }
        }

        if (!isAlive()){return 0;}

        if (player.distance(position) < range + size && attackTimer <= 0){
            attackTimer = attackCD;
            return (int)Math.round(damage);
        } else {
            attackTimer -= totalTime;
            if (attackTimer < 0) attackTimer = 0.0f;
            return 0;
        }
    }

    public boolean isAlive(){
        return (HP[0] > 0);
    }
}
