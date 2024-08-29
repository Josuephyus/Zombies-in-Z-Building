package behavior;

import java.awt.Graphics;
import java.awt.Graphics2D;

import data.interactables.Interactable;
import data.weapons.Weapon;

import java.awt.Color;

import util.*;

public class Player extends Entity{

    public Weapon[] weapons = new Weapon[]{
        WeaponManager.newWeapon(0, this),
        WeaponManager.newWeapon(1, this),
        WeaponManager.newWeapon(2, this),
        WeaponManager.newWeapon(0, this),
    };
    public Integer weaponIndex = 0;

    //Current, Max, Regeneration
    public float[] Energy = new float[]{200f, 200f, 40f};
    public Boolean canDash = true;
    private float _sprint_speed = 180f;
    private boolean _interactionToggle = true;

    public int size = 30;

    private boolean _SwapToggle = true;

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

        boolean[] boolean_array = new boolean[]{a.k[0],a.k[1],a.k[2],a.k[3], a.k[13]};
        Move(boolean_array, time);

        boolean_array = new boolean[]{a.k[4], a.k[5], a.k[6], a.k[7]};
        SwapWeapons(boolean_array);

        boolean_array = new boolean[]{a.k[8], a.k[9]};
        Shoot(boolean_array, b, time);

        boolean_array = new boolean[]{a.k[10]};
        Interact(boolean_array, time);

    }

    private void Interact(boolean[] a, float time){
        if (a[0] && _interactionToggle){
            for (Interactable i : map.interactables){
                if (i.position.distance(position) < i.radius){
                    i.interactedWith(this);
                }
            } _interactionToggle = false;
        } else if (!a[0]){
            _interactionToggle = true;
        }
    }

    private void Shoot(boolean[] a, Mouse b, float time){
        rotation = position.directionTo(new Point(b.x, b.y));
        if (a[0]){
            damages.add(weapons[weaponIndex].Fire(this));
        } else if (a[1]){
            weapons[weaponIndex].Reload();
        }
        weapons[weaponIndex].update(time);
    }

    private void SwapWeapons(boolean[] a){
        for (int i = 0; i < weapons.length; i++){
            if (a[i] && _SwapToggle && weapons[i] != null){
                weaponIndex = i; _SwapToggle = false;
            }
        }
        if (!a[0] && !a[1] && !a[2] && !a[3]){
            _SwapToggle = true;
        }
    }

    private void Move(boolean[] a, float time){
        
        float speed = _speed * time;

        if (a[4] && canDash){
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

        if (a[0])playerToMove.y++; if (a[2])playerToMove.x--;
        if (a[1])playerToMove.y--; if (a[3])playerToMove.x++;

        if (hasBuff("MOVESPEED_UP")){speed *= 2;}

        if (position.distance(playerToMove) != 0){
            float theta = position.directionTo(playerToMove);

            int microsteps = 4;
            
            float xM = (float)(Math.cos(theta) * speed) / microsteps;
            float yM = (float)(Math.sin(theta) * speed) / microsteps;
            for (int i = 0; i < microsteps; i++){
                if (map.isValid(new Point(position.x + xM, position.y))){
                    position.x += xM;
                }
                if (map.isValid(new Point(position.x, position.y + yM))){
                    position.y+= yM;
                }
            }
        }
    }


    public String getLastShot(){ return lastShot; }

    public void drawMethod(Graphics g){
        Graphics2D g2 = (Graphics2D) g; g2.setColor(Color.YELLOW); g2.fillOval(-size/2,-size/2,size,size);
    }

    public static class Keys{
        public boolean[] k; public Keys(boolean[] a){k = a;}
    }
    public static class Mouse{
        public int x, y; public Mouse(int x, int y){this.x = x; this.y = y;}
    }
}
