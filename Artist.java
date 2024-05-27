import java.awt.Graphics;

import util.Point;
import behavior.Entity;

public class Artist{
    public static void draw(Graphics g){
        Point zero = Initialize.game.p.position;
        g.translate(Initialize.scrW/2, Initialize.scrH/2);
        Integer x = (int)Math.round(Initialize.game.p.position.x);
        Integer y = (int)Math.round(Initialize.game.p.position.y);
        g.translate(x,-y);
        Initialize.game.p.drawMethod(g);
    }
}
