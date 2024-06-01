package util;

import java.util.ArrayList;

public class Laser {
    public Integer ownerID, ID, width, damage;
    public Double curLifespan, maxLifespan;
    public ArrayList<Integer> alreadyHit;
    public Line hitbox;

    public Laser(Point start, Double direction, Integer width){
        this.hitbox = new Line(
            new Point(
                start.x,
                -start.y
            ),
            new Point(
                start.x + (Math.cos(direction) * 1000),
                -start.y + (Math.sin(direction) * 1000)
            )
        );
        this.width = width;
        this.curLifespan = this.maxLifespan = 1.0;
        this.damage = 40;
        alreadyHit = new ArrayList<Integer>();
    }

    public void update(Integer time, Integer tickPerSecond){
        this.curLifespan -= (double)time / (tickPerSecond * 50);
    }

    public Integer getDisplayWidth(){
        Double timer = ((curLifespan/maxLifespan) * -6) + 6;
        Integer returnthis = width;
        if (timer < 1){
            returnthis = (int)Math.round((0.3 + (timer * 0.7)) * width);
        } else if (timer > 3){
            returnthis = (int)Math.round((1 - (0.28 * (timer - 3))) * width);
        }
        return returnthis;
    }

    public boolean alrHit(Integer ID){
        for (Integer i : alreadyHit){
            if (i == ID){
                return true;
            }
        } return false;
    }

    public boolean isAlive(){
        return (curLifespan > 0);
    }
}