import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import textures.Texture;
import util.Point;

public class Artist{

    static BufferedImage[] gun;

    public static void loadImages(){
        gun = quickRead("weapons/",new String[]{"BigYellow"});
    }

    public static BufferedImage[] quickRead(String prefix, String[] a){
        BufferedImage b[] = new BufferedImage[a.length];
        for (int i = 0; i < a.length; i++){
            try {
                b[i] = ImageIO.read(Texture.class.getResourceAsStream(prefix + "" + a[i] + ".png"));
            } catch (IOException e){}
        }
        return b;
    }

    public static void draw(Graphics g){
        // Move To Center
        g.translate(Initialize.scrW/2, Initialize.scrH/2);
        Integer x = (int)Math.round(Initialize.game.p.position.x);
        Integer y = (int)Math.round(Initialize.game.p.position.y);
        g.translate(x,-y);

        // Draw Player
        Initialize.game.p.drawMethod(g);

        // Draw Cursor
        g.translate(Listener.Mouse.x, Listener.Mouse.y);
        g.drawOval(-5,-5,10,10);
        g.translate(-Listener.Mouse.x, -Listener.Mouse.y);
        Graphics2D g2 = (Graphics2D) g;

        // Draw Gun
        Double theta = Initialize.game.p.position.directionTo(new Point(Listener.Mouse.x, Listener.Mouse.y));
        g2.rotate(theta);
        Boolean isLeft = theta > Math.PI / 2 || theta < Math.PI / -2;
        g2.drawImage(gun[0], 100, 20 * (isLeft?1:-1), -100, 40 * (isLeft?-1:1), null);
    }
}
