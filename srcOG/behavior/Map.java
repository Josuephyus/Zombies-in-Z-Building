package srcOG.behavior;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

import srcOG.data.interactables.*;
import util.*;

public class Map {

    public BufferedImage image;
    public Point lowerBound = new Point(0,0);
    public Point upperBound = new Point(2000,1000);
    public Interactable[] interactables;

    public Wall[] walls = new Wall[]{
        new Wall(0,996,316, 4),
        new Wall(0, 724,316, 8),
        new Wall(0, 500-16,316, 8),
        new Wall(0, 500-256,316, 8),
        new Wall(0, 0,316, 8),
        new Wall(1000,500,100,100),
    };

    public Map(){
        interactables = new Interactable[]{
            new FireRate(),
            new Movespeed(),
            new Lifesteal(),
        };
    }

    public boolean isValid(Point e){
        if (e.x > upperBound.x) return false;
        if (e.y > upperBound.y) return false;
        if (e.x < lowerBound.x) return false;
        if (e.y < lowerBound.y) return false;

        for (Wall i : walls){
            if (i.isValid(e)){
                return false;
            }
        }
        return true;
    }

    public void draw(Graphics g){
        g.setColor(new Color(255,0,0,190));
        int[] xs = new int[]{(int)lowerBound.x, (int)lowerBound.x, (int)upperBound.x, (int)upperBound.x};
        int[] ys = new int[]{-(int)lowerBound.y, -(int)upperBound.y, -(int)upperBound.y, -(int)lowerBound.y};
        g.drawPolygon(xs, ys, 4);
        for (Wall i : walls){
            int[][] p = i .getPoints();
            g.fillPolygon(p[0], p[1], 4);
        }
    }

    public static class Wall{
        public float x, y, w, h;
        public Wall(int x, int y, int width, int height){
            this.x = x; this.y = y; w = width; h = height;
        }

        public boolean isValid(Point e){
            if (e.x > x && e.y > y){
                if (e.x < x+w && e.y < y+h){
                    return true;
                }
            } return false;
        }

        public int[][] getPoints(){
            int x1 = (int)(x);
            int x2 = (int)(x+w);
            int y1 = -(int)(y);
            int y2 = -(int)(y+h);
            return new int[][]{
                {x1, x2, x2, x1},
                {y1, y1, y2, y2}
            };
        }
        public float[][] getRawPoints(){
            float x1 = x;
            float x2 = x+w;
            float y1 = -(y);
            float y2 = -(y+h);
            return new float[][]{
                {x1, x2, x2, x1},
                {y1, y1, y2, y2}
            };
        }
    }
}
