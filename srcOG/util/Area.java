package util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import srcOG.behavior.*;

import java.awt.Color;

public class Area extends Damage {

    public float currentDuration;
    public int xa = 0, ya = 0;
    
    public Area(){}
    public Area(Entity e, Damage d, Point p, float direction){
        rotation = -direction;
        position = new Point(p.x, p.y);

        range = d.range; // Square 0, Triangle 1
        width = d.width; height = d.height;
        damage = d.damage;
        duration = d.duration;
        image = d.image;

        tied = e;
        currentDuration = 0;

        Damage.AdvanceID(this);
    }

    public void update(float totalTime){currentDuration += totalTime;}
    public boolean canHit(Entity e){
        if (currentDuration < 0.017f){
            Line a = new Line(new Point(e.position.x, e.position.y), new Point(e.position.y));
            xa = (int)a.s.x; ya = (int)a.s.y;
            Point[] b = new Point[4];
            float[] xs = {width, -10, -10, width};
            float[] ys = {height / 2, height / 2, height / -2, height / -2};
            for (int i = 0; i < b.length; i++){
                b[i] = rotate(xs[i], ys[i], -rotation);
                b[i] = new Point(b[i].x, b[i].y);
                xs[i] = (float)(b[i].x + position.x);
                ys[i] = -(float)(b[i].y - position.y);;
            }
    
            boolean inside = false;
            int j = 3;
            for (int i = 0; i < 4; j = i++){
                if ( ((ys[i] > e.position.y) != (ys[j] > e.position.y))
                && (e.position.x < (xs[j] - xs[i]) * (e.position.y - ys[i]) / (ys[j] - ys[i]) + xs[i])){
                    inside = !inside;
                }
            }

            return inside;
        } 
        
        return false;
    }

    public boolean isAlive(){return currentDuration < duration;}

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.GRAY);
        g2.translate(position.x, -position.y);
        g2.rotate(-rotation);
        g2.setStroke(new BasicStroke(1));
        int x1 = width;
        int x2 = -10;
        int y1 = (int)(height / 2);
        int y2 = -(int)(height / 2);
        g2.fillPolygon(new int[]{x1, x2, x2, x1}, new int[]{y1, y1, y2, y2}, 4);
        g2.rotate(rotation);

        g2.translate(-position.x, position.y);
    }    
    

    private Point rotate(float x, float y, float direction){
        float asdf = (float)(x * Math.cos(direction) - y * Math.sin(direction));
        float fdsa = (float)(x * Math.sin(direction) + y * Math.cos(direction));

        return new Point(asdf, fdsa);
    }
}
