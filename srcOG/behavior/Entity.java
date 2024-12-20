package srcOG.behavior;

import java.awt.Graphics;

import java.util.ArrayList;
import util.*;

public class Entity {

    public static Map map;
    private static int NEXT_ID;
    public int ID;
    public String[] buffs = new String[3];
    public String[] potentialBuffs = new String[]{
        "FIRERATE_UP", "MOVEMENT_UP", "LIFESTEAL_5"
    };
    public int buffIndex = 0;

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

    public void addBuff(String a){
        buffs[buffIndex] = a;
        buffIndex++;
        buffIndex = buffIndex % buffs.length;
    }

    public boolean hasBuff(String a){
        for (int i = 0; i < buffs.length; i++){
            if (buffs[i] != null){
                if (buffs[i].equals(a.toUpperCase())){
                    return true;
                }
            }
        }
        return false;
    }

    public void drawMethod(Graphics g){}
    public boolean isAlive(){return (HP[0] > 0);}
    public float update(Point a, float b){
        return Math.round(damage);
    }
}
