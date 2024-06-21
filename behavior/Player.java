package behavior;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import util.Point;
import util.Projectile;
import util.Laser;

public class Player extends Entity{

    public behavior.Weapons[] weapons;

    public Integer weaponIndex;

    public Double mEnergy, cEnergy, rEnergy;
    public Boolean canDash;

    public Player(){ // Assign Default Values
        ID = weaponIndex = 0; mHP = cHP = speed = 100f;
        
        // Damage Modifier rather than actual damage
        damage = 1f;

        size = 30;

        mEnergy = cEnergy = 200.0;
        rEnergy = 40.0; canDash = true;

        position = new Point(0,0);

        weapons = new behavior.Weapons[3];
        Integer index = -1;
        for (int i = 0; i < behavior.Weapons.names.size(); i++){
            if (behavior.Weapons.names.get(i).equals("pistol.weapon")){
                index = i;
            }
        }
        if (index == -1){
            throw new Error();
        } else {
            weapons[0] = behavior.Weapons.weapons.get(index);
            weapons[1] = null;
            weapons[2] = null;
        }
    }

    public void drawMethod(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.fillOval(-size/2,-size/2,size,size);
    }


    public String fireType(){
        return weapons[weaponIndex].type;
    }

    public Projectile fireProjectile(Point to){
        return new Projectile(
            (int)Math.round(position.x),
            (int)Math.round(position.y),
            position.directionTo(
                new Point(
                    position.x + to.x, 
                    position.y + to.y
                )
            )
        );
    }

    public Laser fireLaser(Point to){
        return new Laser(
            position,
            position.directionTo(
                new Point(
                    position.x + to.x,
                    position.y + to.y
                )
            ),
            12
        );
    }
}
