package util;

public class Projectile{
    static Integer nextID = 0;
    public Integer ownerID, ID;
    public Integer width, height, speed, damage, radius;
    public Double rotation, curRange, maxRange;
    public Point position;
    public String image;

    public Projectile(Integer x, Integer y, Double direction){
        this.position = new Point(x, y);
        this.ID = nextID; nextID++;
        this.rotation = direction;
        this.maxRange = 1000.0; this.curRange = 0.0;
        this.width = 10; this.height = 4;
        this.speed = 750; this.damage = 100;
        this.radius = 2;
    }

    public void update(Double totalTime){
        curRange += (Math.abs(Math.cos(rotation)) + Math.abs(Math.sin(rotation))) * speed * totalTime;
        position.x += Math.cos(rotation) * speed * totalTime;
        position.y += Math.sin(rotation) * speed * totalTime;
    }

    public boolean isAlive(){
        return (curRange < maxRange);
    }

    public String toString(){
        return (
            ("ID:" + ID) +
            (", Direction:" + Math.toDegrees(rotation)) +
            (", Range:" + curRange + " / " + maxRange) +
            (", Width:" + width) +
            (", Height:" + height) +
            (", Radius:" + radius) +
            (", Speed:" + speed) +
            (", Damage:" + damage)
        );
    }
}
