package util;

public class Projectile{
    static Integer nextID = 0;
    public Integer ownerID, ID;
    public Integer width, height, speed, damage, radius;
    public Double rotation, lifespan;
    public Point position;

    public Projectile(Integer x, Integer y, Double direction, Integer type){
        this.position = new Point(x, y);
        this.ID = nextID; nextID++;
        this.rotation = direction; lifespan = 2.0;
        if (type == 0){
            this.width = 10;
            this.height = 4;
            this.speed = 1500;
            this.damage = 100;
            this.radius = 2;
        }
    }

    public void update(Integer time, Integer tickPerSecond){
        position.x += Math.cos(rotation) * speed * time / (tickPerSecond * 100);
        position.y += Math.sin(rotation) * speed * time / (tickPerSecond * 100);
        lifespan -= (double)time / (tickPerSecond * 100);
    }

    public boolean isAlive(){
        return (lifespan > 0);
    }
}
