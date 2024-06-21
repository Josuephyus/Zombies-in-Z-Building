package util;

public class Projectile{
    static Integer nextID = 0;
    public Integer ownerID, ID;
    public Integer width, height, speed, damage, radius;
    public Double rotation, lifespan;
    public Point position;
    public java.awt.image.BufferedImage image;

    public Projectile(Integer x, Integer y, Double direction){
        this.position = new Point(x, y);
        this.ID = nextID; nextID++;
        this.rotation = direction; lifespan = 1.0;
        this.width = 10; this.height = 4;
        this.speed = 750; this.damage = 100;
        this.radius = 2;
    }

    public void update(Double totalTime){
        position.x += Math.cos(rotation) * speed * totalTime;
        position.y += Math.sin(rotation) * speed * totalTime;
        lifespan -= totalTime;
    }

    public boolean isAlive(){
        return (lifespan > 0);
    }
}
