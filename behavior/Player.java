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

    String lastShot = "";

    public Player(){ // Assign Default Values
        System.out.println("Player.java - Creating Player");

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

    public Projectile[] fireProjectile(Point to){
        Projectile[] returnThis = new Projectile[weapons[weaponIndex].projectiles.length];
        for (int i = 0; i < returnThis.length; i++){
            returnThis[i] = new Projectile(
                (int)Math.round(position.x),
                (int)Math.round(position.y),
                position.directionTo(
                    new Point(
                        position.x + to.x, 
                        position.y + to.y
                    )
                )
            );
            returnThis[i].damage = weapons[weaponIndex].projectiles[i].damage;
            returnThis[i].width = weapons[weaponIndex].projectiles[i].width;
            returnThis[i].height = weapons[weaponIndex].projectiles[i].height;
            returnThis[i].radius = weapons[weaponIndex].projectiles[i].radius;
            returnThis[i].speed = weapons[weaponIndex].projectiles[i].speed;
            returnThis[i].rotation = Math.toRadians(weapons[weaponIndex].projectiles[i].direction) + returnThis[i].rotation;
        }

        lastShot = returnThis[0].toString();

        return returnThis;
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

    public String getLastShot(){
        return lastShot;
    }
}
