import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;

public class Listener {
    public static class Key implements KeyListener{
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
    }
    public static class Mouse implements MouseListener{

        Integer x, y;

        public void updatePosition(){
            int x1 = (int)MouseInfo.getPointerInfo().getLocation().getX();
            int y1 = (int)MouseInfo.getPointerInfo().getLocation().getY();
            x = x1 - Initialize.scrW;
            y = y1 - Initialize.scrH;
        }

        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}
