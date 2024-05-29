package behavior;

import java.awt.Graphics;

import util.Point;

public class Entity {
    //mHP is Max Health Points, cHP is Current Health Points
    public Integer mHP, cHP;


    //size is the radius of check for hitbox intersection
    //ID is the Unique Identifier to avoid multiple things happening to one unit
    public Integer size, ID;


    //Speed is pixels / 100 per frame
    //Damage (for player) is a multiplicative modifier for projectiles
    //Damage (for NPCS) is a flat value of health reduction on damage instance;
    public Double speed, damage;


    //Position... is position (with decimals)
    public Point position;
    

    public void drawMethod(Graphics g){
        
    }
}
