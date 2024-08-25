package util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import behavior.Entity;

public class Laser extends Damage{
    public float currentDuration;
    public Line hitbox;

    public float updateCount = 0;

    public Laser(){this(new Point(), 0f, 10, 1000);}
    public Laser(int a, int b, float c){this(new Point(a, b), c, 4, 1000);}
    public Laser(Damage l, Point pos, float direction){
        this(pos, direction, l.width, l.range);
        duration = l.duration;
        damage = l.damage;
    }
    public Laser(Point start, float direction, int width, float rang){
        range = rang;
        this.hitbox = new Line(
            new Point(
                start.x,
                start.y
            ),
            new Point(
                start.x + (Math.cos(-direction) * range),
                start.y + (Math.sin(-direction) * range)
            )
        );
        rotation = direction;
        this.width = width;
        this.damage = 40;
        this.duration = 1;
    }

    public void update(float totalTime){
        currentDuration += totalTime; updateCount++;
        hitbox = new Line(
            new Point(
                tied.position.x,
                tied.position.y
            ),
            new Point(
                tied.position.x + (Math.cos(-rotation) * range),
                tied.position.y + (Math.sin(-rotation) * range)
            )
        );}
    public boolean isAlive(){return (currentDuration < duration);}

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(getDisplayWidth()));
        g2.drawLine((int)hitbox.s.x, -(int)hitbox.s.y, (int)hitbox.e.x, -(int)hitbox.e.y);
    }

    public boolean canHit(Entity e){
        System.out.println(hitbox.distance(e.position) < width + e.size);
        return hitbox.distance(e.position) < (width * 2) + e.size && updateCount < 2;
    }

    private int getDisplayWidth(){
        float timer = ((currentDuration / duration) * -6) + 6;
        int returnthis = width;
        if (timer < 1){
            returnthis = (int)((0.3 + (timer * 0.7)) * width);
        } else if (timer > 3){
            returnthis = (int)((1 - (0.28 * (timer - 3))) * width);
        }
        return returnthis;
    }
}