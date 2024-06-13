package behavior;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import util.Point;
import util.Projectile;
import util.Laser;

public class Player extends Entity{

    public Double mEnergy, cEnergy, rEnergy;
    public Boolean canDash;

    public Player(){ //Assign Default Values
        ID = 0; mHP = cHP = 200;
        damage = 1.0; speed = 200.0;
        size = 30;

        mEnergy = cEnergy = 200.0;
        rEnergy = 40.0;

        canDash = true;

        position = new Point(0,0);
    }

    public void drawMethod(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.fillOval(-size/2,-size/2,size,size);
    }


    public String fireType(){
        return "Laser";
    }

    public Projectile fireProjectile(Point to){
        return new Projectile(
            (int)Math.round(position.x),
            (int)Math.round(position.y),
            position.directionTo(
                new Point(
                    position.x - to.x, 
                    position.y - to.y
                )
            ),
            0
        );
    }

    public Laser fireLaser(Point to){
        return new Laser(
            position,
            position.directionTo(
                new Point(
                    to.x + position.x,
                    to.y + position.y
                )
            ),
            12
        );
    }
}
