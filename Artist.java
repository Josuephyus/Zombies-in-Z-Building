import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;

import textures.Texture;
import util.Projectile;
import util.Laser;
import util.Point;

public class Artist{

    static Graphics g;
    static Graphics2D g2;
    static Point playerPos;
    static Double zoom, heiRatio, widRatio;

    public static void loadImages(){
        Texture.loadImages();
        zoom = 1.0;
        heiRatio = Initialize.scrH / 640.0;
        widRatio = Initialize.scrW / 360.0;
    }

    public static void draw(Graphics gr){

        g = gr;
        g2 = (Graphics2D) g;
        playerPos = new Point(Initialize.game.p.position.x, Initialize.game.p.position.y);

        // Move Center based on mouse position &
        g.translate(
            (Listener.Mouse.x / -3) + (Initialize.scrW / 2),
            (Listener.Mouse.y / -3) + (Initialize.scrH / 2)
        );
        

        // Draw Circle at 0,0
        g.setColor(Color.WHITE);
        drawRelativeOval(0.0,-10,-10,20,20);


        // Draw Projectiles
        g.setColor(Color.WHITE);
        for (int i = 0; i < Logic.projectiles.size(); i++){
            Projectile cur = Logic.projectiles.get(i);
            g.translate((int)Math.round(cur.position.x), (int)Math.round(-cur.position.y));
            fillRelativeRect(-cur.rotation,-(cur.width/2), -(cur.height/2), cur.width, cur.height);
            g.translate((int)Math.round(-cur.position.x), (int)Math.round(cur.position.y));
        }


        // Draw Lasers
        g.setColor(Color.CYAN);
        for (int i = 0; i < Logic.lasers.size(); i++){
            Laser cur = Logic.lasers.get(i);
            g2.setStroke(new BasicStroke((int)Math.round(cur.getDisplayWidth() * ((widRatio + heiRatio) / 2))));
            drawRelativeLine((int)Math.round(cur.hitbox.s.x), (int)Math.round(-cur.hitbox.s.y), (int)Math.round(cur.hitbox.e.x), (int)Math.round(-cur.hitbox.e.y));
        }

        // Draw Player
        Initialize.game.p.drawMethod(g);

        // Draw Gun (based on player position)
        Double theta = playerPos.directionTo(new Point(Listener.Mouse.x + playerPos.x, Listener.Mouse.y + playerPos.y));
        Boolean isLeft = theta > Math.PI / 2 || theta < Math.PI / -2;
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
        drawRelativeImage(
            Texture.gun[Texture.selectedWeapon],
            theta,
            100,
            20 * (isLeft?1:-1),
            -100,
            40 * (isLeft?-1:1)
        );
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));


        // Draw Zombies
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        for (int i = 0; i < Logic.zombies.size(); i++){
            g.translate((int)Math.round(Logic.zombies.get(i).position.x), (int)Math.round(-Logic.zombies.get(i).position.y));
            Logic.zombies.get(i).drawMethod(g);
            g.translate((int)Math.round(-Logic.zombies.get(i).position.x), (int)Math.round(Logic.zombies.get(i).position.y));
        }
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));


        // Draw Health 
        g.setColor(Color.GRAY);
        fillAbsoluteRect(
            0.0,
            (int)10,
            (int)(-10 - (50 * heiRatio) + Initialize.scrH),
            (int)(Initialize.scrW / 3),
            (int)(50 * heiRatio)
        );
        g.setColor(Color.RED);
        fillAbsoluteRect(
            0.0,
            (int)10,
            (int)(-10 - (50 * heiRatio) + Initialize.scrH),
            (int)((Initialize.scrW / 3) * ((double)Initialize.game.p.cHP / (double)Initialize.game.p.mHP)),
            (int)(50 * heiRatio)
        );

        // Stamina
        g.setColor(Color.GRAY);
        fillAbsoluteRect(
            0.0,
            (int)10,
            (int)(-20 - (50 * heiRatio) - (40 * heiRatio) + Initialize.scrH),
            (int)(Initialize.scrW / 4),
            (int)(40 * heiRatio)
        );
        g.setColor(((Initialize.game.p.canDash) ? Color.YELLOW:Color.ORANGE));
        fillAbsoluteRect(
            0.0,
            (int)10,
            (int)(-20 - (50 * heiRatio) - (40 * heiRatio) + Initialize.scrH),
            (int)((Initialize.scrW / 4) * (Initialize.game.p.cEnergy / Initialize.game.p.mEnergy)),
            (int)(40 * heiRatio)
        );


        // Draw Cursor
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke((int)Math.round(2 * (widRatio + heiRatio))));
        drawAbsoluteOval(
            0.0,
            Listener.Mouse.x - 5 ,
            Listener.Mouse.y - 5,
            10,
            10
        );
    }

    public static void drawRelativeLine(int x1, int y1, int x2, int y2){
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        g2.drawLine(x1, y1, x2, y2);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
    }
    public static void drawRelativeImage(BufferedImage a, Double direction, int x, int y, int w, int h){
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        g2.rotate(direction);
        g2.drawImage(a, x, y, w, h, null);
        g2.rotate(-direction);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
    }
    public static void drawRelativeOval(Double rot, Integer x, Integer y, Integer w, Integer h){
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        Graphics2D tempGraphics2D = (Graphics2D) g;
        tempGraphics2D.rotate(rot);
        g2.drawOval(x,y,w,h);
        tempGraphics2D.rotate(-rot);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
    }
    public static void drawRelativeRect(Double rot, Integer x, Integer y, Integer w, Integer h){
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        Graphics2D tempGraphics2D = (Graphics2D) g;
        tempGraphics2D.rotate(rot);
        g2.drawRect(x,y,w,h);
        tempGraphics2D.rotate(-rot);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
    }
    public static void fillRelativeRect(Double rot, Integer x, Integer y, Integer w, Integer h){
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        Graphics2D tempGraphics2D = (Graphics2D) g;
        tempGraphics2D.rotate(rot);
        g2.fillRect(x,y,w,h);
        tempGraphics2D.rotate(-rot);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
    }
    public static void drawAbsoluteOval(Double rot, Integer x, Integer y, Integer w, Integer h){

    }
    public static void drawAbsoluteRect(Double rot, Integer x, Integer y, Integer w, Integer h){
        g.translate(
            (Listener.Mouse.x / 3) + (Initialize.scrW / -2),
            (Listener.Mouse.y / 3) + (Initialize.scrH / -2)
        );
        g2.rotate(rot);
        g2.drawRect(x,y,w,h);
        g2.rotate(-rot);
        g.translate(
            (Listener.Mouse.x / -3) + (Initialize.scrW / 2),
            (Listener.Mouse.y / -3) + (Initialize.scrH / 2)
        );
    }
    public static void fillAbsoluteRect(Double rot, Integer x, Integer y, Integer w, Integer h){
        g.translate(
            (Listener.Mouse.x / 3) + (Initialize.scrW / -2),
            (Listener.Mouse.y / 3) + (Initialize.scrH / -2)
        );
        g2.rotate(rot);
        g2.fillRect(x,y,w,h);
        g2.rotate(-rot);
        g.translate(
            (Listener.Mouse.x / -3) + (Initialize.scrW / 2),
            (Listener.Mouse.y / -3) + (Initialize.scrH / 2)
        );
    }
    
}