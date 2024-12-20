package srcOG;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;

import textures.Texture;
import util.Point;

public class Artist{

    static Graphics g;
    static Graphics2D g2;
    static Point playerPos;
    static double zoom, heiRatio, widRatio;

    public static void loadImages(){
        Texture.loadImages();
        zoom = 1.0;
        heiRatio = Initialize.scrH / 640.0;
        widRatio = Initialize.scrW / 360.0;
    }

    public static void draw(Graphics gr){

        g = gr;
        g2 = (Graphics2D) g;
        playerPos = new Point(Logic.player.position.x, Logic.player.position.y);

        // Move Center based on mouse position &
        g.translate(
            (Listener.gameMouse.x / -3) + (Initialize.scrW / 2),
            (Listener.gameMouse.y / -3) + (Initialize.scrH / 2)
        );

        // Draw Map
        g.setColor(Color.WHITE);
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        g.drawImage(Texture.allMaps[1], 0, -1000, 2000, 1000, null);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));

        // Draw Debug Map
        g.setColor(Color.WHITE);
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        Logic.map.draw(g);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));

        // Draw Circle at 0,0
        g.setColor(Color.WHITE);
        drawRelativeOval(0f,-10,-10,20,20);


        // Draw Vending Machines
        g.setColor(Color.WHITE);
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        for (int i = 0; i < Logic.map.interactables.length; i++){
            Logic.map.interactables[i].draw(g);
        }
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));

        // Draw Projectiles
        g.setColor(Color.WHITE);
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        for (int i = 0; i < Logic.damages.size(); i++){
            Logic.damages.get(i).draw(g);
        }
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));

        // Draw Player
        Logic.player.drawMethod(g);

        // Draw Gun (based on player position)
        float theta = playerPos.directionTo(new Point(Listener.gameMouse.x + playerPos.x, Listener.gameMouse.y + playerPos.y));
        Boolean isLeft = theta > Math.PI / 2 || theta < Math.PI / -2;
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));

        String toFind = Logic.player.weapons[Logic.player.weaponIndex].image + ".png";
        Integer weaponIndex = -1;
        for (int i = 0; i < Texture.weaponNames.length; i++){
            if (toFind.equals(Texture.weaponNames[i])){
                weaponIndex = i;
            }
        }
        if (weaponIndex == -1){throw new Error();}
        else {
        drawRelativeImage(
            Texture.allWeapons[weaponIndex],
            theta,
            Logic.player.weapons[Logic.player.weaponIndex].width,
            Logic.player.weapons[Logic.player.weaponIndex].height * (isLeft?1:-1) / 2,
            -Logic.player.weapons[Logic.player.weaponIndex].width,
            Logic.player.weapons[Logic.player.weaponIndex].height * (isLeft?-1:1)
        );}
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
            0f,
            (int)10,
            (int)(-10 - (50 * heiRatio) + Initialize.scrH),
            (int)(Initialize.scrW / 3),
            (int)(50 * heiRatio)
        );
        g.setColor(Color.RED);
        fillAbsoluteRect(
            0f,
            (int)10,
            (int)(-10 - (50 * heiRatio) + Initialize.scrH),
            (int)((Initialize.scrW / 3) * (Logic.player.HP[0] / Logic.player.HP[1])),
            (int)(50 * heiRatio)
        );

        // Stamina
        g.setColor(Color.GRAY);
        fillAbsoluteRect(
            0f,
            (int)10,
            (int)(-20 - (50 * heiRatio) - (40 * heiRatio) + Initialize.scrH),
            (int)(Initialize.scrW / 4),
            (int)(40 * heiRatio)
        );
        g.setColor(((Logic.player.canDash) ? Color.YELLOW:Color.ORANGE));
        fillAbsoluteRect(
            0f,
            (int)10,
            (int)(-20 - (50 * heiRatio) - (40 * heiRatio) + Initialize.scrH),
            (int)((Initialize.scrW / 4) * (Logic.player.Energy[0] / Logic.player.Energy[1])),
            (int)(40 * heiRatio)
        );


        // Draw Cursor
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke((int)Math.round(2 * (widRatio + heiRatio))));
        drawAbsoluteOval(
            0f,
            Listener.gameMouse.x - 5 ,
            Listener.gameMouse.y - 5,
            10,
            10
        );

        if (Listener.check("Pause")){
            g.setColor(new Color(0,0,0,100));
            fillAbsoluteRect(0f,0,0,Initialize.scrW, Initialize.scrH);
        }
    }

    public static void drawRelativeLine(int x1, int y1, int x2, int y2){
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        g2.drawLine(x1, y1, x2, y2);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
    }
    public static void drawRelativeImage(BufferedImage a, float direction, int x, int y, int w, int h){
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        g2.rotate(direction);
        g2.drawImage(a, x, y, w, h, null);
        g2.rotate(-direction);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
    }
    public static void drawRelativeOval(float rot, Integer x, Integer y, Integer w, Integer h){
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        Graphics2D tempGraphics2D = (Graphics2D) g;
        tempGraphics2D.rotate(rot);
        g2.drawOval(x,y,w,h);
        tempGraphics2D.rotate(-rot);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
    }
    public static void drawRelativeRect(float rot, Integer x, Integer y, Integer w, Integer h){
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        Graphics2D tempGraphics2D = (Graphics2D) g;
        tempGraphics2D.rotate(rot);
        g2.drawRect(x,y,w,h);
        tempGraphics2D.rotate(-rot);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
    }
    public static void fillRelativeRect(float rot, Integer x, Integer y, Integer w, Integer h){
        g.translate((int)Math.round(-playerPos.x), (int)Math.round(playerPos.y));
        Graphics2D tempGraphics2D = (Graphics2D) g;
        tempGraphics2D.rotate(rot);
        g2.fillRect(x,y,w,h);
        tempGraphics2D.rotate(-rot);
        g.translate((int)Math.round(playerPos.x), (int)Math.round(-playerPos.y));
    }
    public static void drawAbsoluteOval(float rot, Integer x, Integer y, Integer w, Integer h){

    }
    public static void drawAbsoluteRect(float rot, Integer x, Integer y, Integer w, Integer h){
        g.translate(
            (Listener.gameMouse.x / 3) + (Initialize.scrW / -2),
            (Listener.gameMouse.y / 3) + (Initialize.scrH / -2)
        );
        g2.rotate(rot);
        g2.drawRect(x,y,w,h);
        g2.rotate(-rot);
        g.translate(
            (Listener.gameMouse.x / -3) + (Initialize.scrW / 2),
            (Listener.gameMouse.y / -3) + (Initialize.scrH / 2)
        );
    }
    public static void fillAbsoluteRect(float rot, Integer x, Integer y, Integer w, Integer h){
        g.translate(
            (Listener.gameMouse.x / 3) + (Initialize.scrW / -2),
            (Listener.gameMouse.y / 3) + (Initialize.scrH / -2)
        );
        g2.rotate(rot);
        g2.fillRect(x,y,w,h);
        g2.rotate(-rot);
        g.translate(
            (Listener.gameMouse.x / -3) + (Initialize.scrW / 2),
            (Listener.gameMouse.y / -3) + (Initialize.scrH / 2)
        );
    }
    
}