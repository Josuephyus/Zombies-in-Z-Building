import java.awt.Graphics;

public class Artist{
    public static void draw(Graphics g){
        g.translate(Initialize.scrW/2, Initialize.scrH/2);
        Integer x = (int)Math.round(Initialize.game.p.position.x);
        Integer y = (int)Math.round(Initialize.game.p.position.y);
        g.translate(x,-y);
        Initialize.game.p.drawMethod(g);
        g.translate(Listener.Mouse.x, Listener.Mouse.y);
        g.drawOval(-5,-5,10,10);
        g.translate(-Listener.Mouse.x, -Listener.Mouse.y);
    }
}
