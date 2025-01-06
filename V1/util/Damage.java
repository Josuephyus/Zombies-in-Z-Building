package util;

import java.awt.Graphics;

import srcOG.behavior.Entity;

public class Damage {
    public static int NEXT_ID = 0;
    public int ID;

    public String image;
    public int damage;
    public int speed;
    public float range;
    public float duration;

    public int width, height, radius;
    public Entity tied;

    public Point position;
    public float rotation;

    public static void AdvanceID(Damage a){
        a.ID = NEXT_ID;
        NEXT_ID++;
    }

    public boolean isAlive(){return false;}
    public void update(float time){}
    public void setRange(int a){}
    public boolean canHit(Entity e){return false;}

    public void draw(Graphics g){}

}
