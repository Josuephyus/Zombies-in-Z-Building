import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import textures.Texture;
import util.Point;

public class Artist{

    public static void loadImages(){
        Texture.loadImages();
    }

    public static void draw(Graphics g){
        // Move To Center
        Integer x = (int)Math.round(Initialize.game.p.position.x);
        Integer y = (int)Math.round(Initialize.game.p.position.y);
        g.translate((Initialize.scrW/2) + x, (Initialize.scrH/2) - y);

        // Draw Player
        Initialize.game.p.drawMethod(g);
        Point realPosition = new Point(Initialize.game.p.position.x, -Initialize.game.p.position.y);

        // Draw Gun
        Graphics2D g2 = (Graphics2D) g;
        Double theta = realPosition.directionTo(new Point(Listener.Mouse.x, Listener.Mouse.y));
        g2.rotate(theta);
        Boolean isLeft = theta > Math.PI / 2 || theta < Math.PI / -2;
        g2.drawImage(Texture.gun[0], 100, 20 * (isLeft?1:-1), -100, 40 * (isLeft?-1:1), null);
        g2.rotate(-theta);

        // Draw Cursor
        g2.setColor(Color.RED);
        g.translate(-x, y);
        g.translate(Listener.Mouse.x, Listener.Mouse.y);
        g.drawOval(-5,-5,10,10);
        g.translate(-Listener.Mouse.x, -Listener.Mouse.y);
    }
}
