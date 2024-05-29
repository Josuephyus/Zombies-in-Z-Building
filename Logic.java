import java.util.ArrayList;

import util.Point;

public class Logic {

    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    static Boolean LCToggle = true;

    public static void update(){
        Listener.Mouse.updatePosition();
        updatePlayer();
    }

    public static void updatePlayer(){
        Point p = Initialize.game.p.position;
        Double speed = Initialize.game.p.speed / 100;
        Point pm = new Point(p.x, p.y);

        //  Movement Commands
        if (Listener.Key.keyActive[0])pm.y++;
        if (Listener.Key.keyActive[1])pm.x--;
        if (Listener.Key.keyActive[2])pm.y--;
        if (Listener.Key.keyActive[3])pm.x++;

        // Update Projectiles
        for (Projectile i : projectiles){
            i.update();
        }
        
        // Toggle for Left Click (Also shoot)
        if (Listener.Mouse.LeftClick && LCToggle){
            LCToggle = false;
            projectiles.add(
                new Projectile(
                    (int)Math.round(p.x),
                    (int)Math.round(-p.y),
                    p.directionTo(new Point(Listener.Mouse.x+p.x, Listener.Mouse.y+p.y)),
                    0
                    )
            );
        } else if (!Listener.Mouse.LeftClick){
            LCToggle = true;
        }

        if (p.distance(pm) != 0){
            Double theta = p.directionTo(pm);
            Initialize.game.p.position.x += Math.cos(theta) * speed;
            Initialize.game.p.position.y += Math.sin(theta) * speed;
        }
    }

    public static class Projectile{
        public Integer ownerID;
        public Integer width, height, speed;
        public Double rotation;
        public Point position;

        public Projectile(Integer x, Integer y, Double direction, Integer type){
            this.position = new Point(x, y);
            this.rotation = direction;
            if (type == 0){
                this.width = 10;
                this.height = 4;
                this.speed = 1500;
            }
        }

        public void update(){
            position.x += Math.cos(rotation) * speed / 100;
            position.y += Math.sin(rotation) * speed / 100;
        }
    }
}