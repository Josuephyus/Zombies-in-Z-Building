import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import textures.Texture;
import util.Point;

public class Artist{

    static Graphics2D g2;

    public static void loadImages(){
        Texture.loadImages();
    }

    public static void draw(Graphics g){

        // Move Center based on mouse position
        g.translate(Listener.Mouse.x / -3, Listener.Mouse.y / -3);

        // Move Center to halfscreen / player position
        g.translate(Initialize.scrW / 2, Initialize.scrH / 2);

        Point realPosition = new Point(Initialize.game.p.position.x, -Initialize.game.p.position.y);

        // Draw Circle at 0,0
        g.translate((int)Math.round(-realPosition.x), (int)Math.round(-realPosition.y));
        g.setColor(Color.WHITE);
        g.fillOval(-10, -10, 20, 20);
        g.setColor(Color.GRAY);
        g.translate((int)Math.round(realPosition.x),(int)Math.round(realPosition.y));

        // Draw Projectiles
        g.translate((int)Math.round(-realPosition.x), (int)Math.round(-realPosition.y));
        g2 = (Graphics2D) g;
        for (Logic.Projectile i : Logic.projectiles){
            g2.translate((int)Math.round(i.position.x), (int)Math.round(i.position.y));
            g2.rotate(i.rotation);
            g2.fillRect(-(i.width/2), -(i.height/2), i.width, i.height);
            g2.rotate(-i.rotation);
            g2.translate((int)Math.round(-i.position.x), (int)Math.round(-i.position.y));
        }
        g.translate((int)Math.round(realPosition.x),(int)Math.round(realPosition.y));

        // Draw Player
        Initialize.game.p.drawMethod(g);

        // Draw Gun (based on player position)
        g2 = (Graphics2D) g;
        Double theta = realPosition.directionTo(new Point(Listener.Mouse.x + realPosition.x, Listener.Mouse.y + realPosition.y));
        g2.rotate(theta);
        Boolean isLeft = theta > Math.PI / 2 || theta < Math.PI / -2;
        g2.drawImage(Texture.gun[0], 100, 20 * (isLeft?1:-1), -100, 40 * (isLeft?-1:1), null);
        g2.rotate(-theta);

        // Undo Halfscreen Movement
        g.translate(Listener.Mouse.x / 3, Listener.Mouse.y / 3);
        // Draw Cursor]
        g2.setColor(Color.RED);
        g.translate(Listener.Mouse.x, Listener.Mouse.y);
        g.drawOval(-5,-5,10,10);
        g.translate(-Listener.Mouse.x, -Listener.Mouse.y);
    }
}