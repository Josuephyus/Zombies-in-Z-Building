package behavior;

import java.awt.Graphics;
import java.awt.Graphics2D;

import data.weapons.Weapon;

import java.awt.Color;

import util.*;

public class Player extends Entity{

    public Weapon[] weapons = new Weapon[]{WeaponManager.newWeapon(0, this), null, null};
    public Integer weaponIndex = 0;

    //Current, Max, Regeneration
    public float[] Energy = new float[]{200f, 200f, 40f};
    public Boolean canDash = true;
    private float _sprint_speed = 180f;

    public int size = 30;

    private boolean _SwapToggle = true;
    public Map m = new Map();

    String lastShot = "";

    public Player(){
        System.out.println("java - Creating Player");

        _speed = 100f;
        HP = new float[]{100f, 100f, 0f};

        position = new Point(0, 0);
        rotation = 0f;
        damage = 1f;
    }

    public void update(Keys a, Mouse b, float time){

        float speed = _speed * time;

        // SPRINT LOGIC

        if (a.k[4] && canDash){

            speed = _sprint_speed * time;
            Energy[0] -= 75f * time;
            canDash = (Energy[0] >= 0);

        } else if (Energy[0] < Energy[1] && !canDash){ 
            
            Energy[0] += (Energy[1]) * time / 5;
            canDash = (Energy[0] >= Energy[1]);

        } else{

            Energy[0] += Energy[2] * time;

        }

        if (Energy[0] > Energy[1])
        Energy[0] = Energy[1];


        
        Point playerToMove = new Point(position.x, position.y);

        if (a.k[0])playerToMove.y++;
        if (a.k[2])playerToMove.x--;
        if (a.k[1])playerToMove.y--;
        if (a.k[3])playerToMove.x++;

        if (hasBuff("movespeed")){
            speed *= 2;
        }

        if (position.distance(playerToMove) != 0){
            float theta = position.directionTo(playerToMove);

            int microsteps = 4;
            
            float xM = (float)(Math.cos(theta) * speed) / microsteps;
            float yM = (float)(Math.sin(theta) * speed) / microsteps;
            for (int i = 0; i < microsteps; i++){
                if (m.isValid(new Point(position.x + xM, position.y))){
                    position.x += xM;
                }
                if (m.isValid(new Point(position.x, position.y + yM))){
                    position.y+= yM;
                }
            }
        }
        
        /* (Move the player if they moved)
         * If playerToMove == position
         * position += ( x movement , y movement )
         */


        //                                              Swap Weapons
        if (a.k[5] && _SwapToggle){
            weaponIndex--; _SwapToggle = false;
            if (weaponIndex == -1)weaponIndex = weapons.length - 1;
            if (weapons[weaponIndex] == null){
                weaponIndex++; if (weaponIndex == weapons.length)weaponIndex = 0;
            }
        } else if (a.k[6] && _SwapToggle){
            weaponIndex++; _SwapToggle = false;
            if (weaponIndex == weapons.length)weaponIndex = 0;
            if (weapons[weaponIndex] == null){
                weaponIndex--; if (weaponIndex == -1)weaponIndex = weapons.length - 1;
            }
        } else if (!a.k[5] && !a.k[6]){
            _SwapToggle = true;
        }


        /* (Swap Weapons)
         * If swapleft, swap to a leftward weapon (if possible)
         * If swapright, swap to a rightward weapon (if possible)
         * else enable the toggle
         * 
         * Overflow Protection
         */


        //                                              Shooting
        rotation = position.directionTo(new Point(b.x, b.y));
        if (a.k[7]){
            damages.add(weapons[weaponIndex].Fire(this));
        } else if (a.k[8]){
            weapons[weaponIndex].Reload();
        }
        weapons[weaponIndex].update(time);
        /*
         * If can fire, fire
         * else, don't fire (duh)
         * 
         * adjust toggle
         */
    }

    public void drawMethod(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.fillOval(-size/2,-size/2,size,size);
    }

    public String getLastShot(){
        return lastShot;
    }

    public static class Keys{
        public boolean[] k;
        public Keys(boolean[] a){k = a;}
    }
    public static class Mouse{
        public int x, y;
        public Mouse(int x, int y){this.x = x; this.y = y;}
    }
}
