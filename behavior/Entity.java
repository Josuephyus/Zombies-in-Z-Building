package behavior;

import java.awt.Graphics;

import java.util.ArrayList;
import util.*;

public class Entity {

    private static int NEXT_ID;
    public int ID;

    public ArrayList<Damage> damages = new ArrayList<Damage>();

    // Current, Max, Regeneration
    public float[] HP;

    //direction is the direction of movement
    public float rotation;


    //size is the radius of check for hitbox intersection
    //ID is the Unique Identifier to avoid multiple things happening to one unit
    public int size;


    //Speed is pixels / 100 per frame
    //Damage (for player) is a multiplicative modifier for projectiles
    //Damage (for NPCS) is a flat value of health reduction on damage instance;
    public float damage;
    public float _speed;


    //Position... is position (with decimals)
    public Point position;
    
    public static void AdvanceID(Entity e){
        e.ID = NEXT_ID; NEXT_ID++;
    }

    public void drawMethod(Graphics g){}
    public boolean isAlive(){return (HP[0] > 0);}
    public int update(Point a, double b){
        return (int)Math.round(damage);
    }
}
