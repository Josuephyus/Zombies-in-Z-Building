

import util.Point;

public class Logic {
    public static void update(){
        Listener.Mouse.updatePosition();
        updatePlayer();
    }

    public static void updatePlayer(){
        Point p = Initialize.game.p.position;
        Double speed = Initialize.game.p.speed / 100;
        Point pm = new Point(p.x, p.y);

        if (Listener.Key.keyActive[0])pm.y++;
        if (Listener.Key.keyActive[1])pm.x--;
        if (Listener.Key.keyActive[2])pm.y--;
        if (Listener.Key.keyActive[3])pm.x++;
        if (p.distance(pm) != 0){
            Double theta = p.directionTo(pm);
            System.out.println(theta);
            Initialize.game.p.position.x += Math.cos(theta) * speed;
            Initialize.game.p.position.y += Math.sin(theta) * speed;
        }
    }
}