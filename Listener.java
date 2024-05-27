import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;

public class Listener {
    public static class Key implements KeyListener{

        public Integer[] keyBinds;
        public static Boolean[] keyActive;
        
        public Key(){
            keyBinds = new Integer[]{
                KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D
            };
            keyActive = new Boolean[keyBinds.length];
            for (int i = 0; i < keyBinds.length; i++){
                keyActive[i] = false;
            }
        }
        
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {
            for (int i = 0; i < keyBinds.length; i++){
                if (e.getKeyCode() == keyBinds[i]){
                    keyActive[i] = true;
                }
            }
        }
        public void keyReleased(KeyEvent e) {
            for (int i = 0; i < keyBinds.length; i++){
                if (e.getKeyCode() == keyBinds[i]){
                    keyActive[i] = false;
                }
            }
        }
    }
    public static class Mouse implements MouseListener{

        public static Integer x, y;
        static Integer px = 22;
        static Integer py = 10;

        public static void updatePosition(){
            int x1 = (int)MouseInfo.getPointerInfo().getLocation().getX();
            int y1 = (int)MouseInfo.getPointerInfo().getLocation().getY();
            x = x1 - Initialize.scrW - (px / 2);
            y = y1 - Initialize.scrH - (py / 2);
        }

        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}
