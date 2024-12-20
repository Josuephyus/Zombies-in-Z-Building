package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import srcOG.behavior.Entity;

public class Projectile extends Damage{
    public int speed, radius;
    public int width, height;
    public String image;

    private float curRange, maxRange;
    public void setRange(int a){maxRange = a * 1f;}

    public Projectile(){this(0, 0, 0f);}

    public Projectile(Entity e, Damage d, Point p, float dir){
        rotation = -dir;
        position = new Point(p.x, p.y);

        maxRange = d.range; curRange = 0f;
        width = d.width; height = d.height;
        speed = d.speed; damage = d.damage;
        radius = d.radius + 1;

        tied = e;
        image = d.image;

        Damage.AdvanceID(this);
    }

    public Projectile(int x, int y, float direction){
        position = new Point(x, y);
        rotation = direction;
        this.maxRange = 1000f; this.curRange = 0f;
        this.width = 10; this.height = 4;
        this.speed = 750; this.damage = 100;
        this.radius = 2;

        Damage.AdvanceID(this);
    }

    public void update(float totalTime){
        curRange += (Math.abs(Math.cos(rotation)) + Math.abs(Math.sin(rotation))) * speed * totalTime;
        position.x += Math.cos(rotation) * speed * totalTime;
        position.y += Math.sin(rotation) * speed * totalTime;
    }

    public boolean canHit(Entity e){
        return e.position.distance(position) < radius + e.size;
    }

    public void draw(Graphics g){
        Graphics2D graphics2D = (Graphics2D)g;


        graphics2D.setColor(Color.GRAY);
        graphics2D.translate((int)Math.round(position.x), (int)Math.round(-position.y));
        graphics2D.rotate(-rotation);
        graphics2D.fillRect(-width/2, -height/2, width, height);
        graphics2D.rotate(rotation);
        graphics2D.translate((int)Math.round(-position.x), (int)Math.round(position.y));
    }

    public boolean isAlive(){
        return (curRange < maxRange);
    }

    public String toString(){
        return (
            ("id: " + ID) +
            (" | " + Math.round(Math.toDegrees(rotation)) + "°") +
            (" | r: " + radius) +
            (" | speed: " + speed) +
            (" | ad: " + damage)
        );
    }
}
