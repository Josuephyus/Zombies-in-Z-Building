import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.util.HashMap;

public class Listener {
    public static HashMap<String,KeyBind> key;
    static String[] keyBindNames;
    public Listener(){
        key = new HashMap<String,KeyBind>();
        keyBindNames = new String[]{
            "MoveForward", "MoveLeft", "MoveBackward", "MoveRight",
            "SwapLeft", "SwapRight", "Sprint", "Fire",
            "SlowTime"
        };
        KeyBind[] defaultKeybinds = new KeyBind[]{
            new KeyBind(KeyEvent.VK_W), new KeyBind(KeyEvent.VK_A), new KeyBind(KeyEvent.VK_S), new KeyBind(KeyEvent.VK_D),
            new KeyBind(KeyEvent.VK_Q), new KeyBind(KeyEvent.VK_E), new KeyBind(KeyEvent.VK_SHIFT), new KeyBind(1, true),
            new KeyBind(KeyEvent.VK_SPACE)
        };

        for (int i = 0; i < keyBindNames.length; i++){
            key.put(keyBindNames[i], defaultKeybinds[i]);
        }
    }


    public static void swap(Boolean mouse, Integer e, Boolean a){
        for (int i = 0; i < keyBindNames.length; i++){
            KeyBind thisKey = key.get(keyBindNames[i]);
            if (thisKey.mouse[0] == mouse && e == thisKey.code[0]){
                key.get(keyBindNames[i]).active[0] = a;
            } else if (thisKey.mouse[1] == mouse && e == thisKey.code[1]){
                key.get(keyBindNames[i]).active[0] = a;
            }
        }
    }


    public static boolean check(String name){
        return (key.get(name).active[0] || key.get(name).active[1]);
    }


    public static class Key implements KeyListener{
        public Key(){}
        
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {swap(false,e.getKeyCode(),true);}
        public void keyReleased(KeyEvent e) {swap(false,e.getKeyCode(),false);}
    }
    public static class Mouse implements MouseListener{
        public static Integer x, y;

        public Mouse(){x = y = 0;}

        public static void updatePosition(){
            int x1 = (int)MouseInfo.getPointerInfo().getLocation().getX();
            int y1 = (int)MouseInfo.getPointerInfo().getLocation().getY();
            x = x1 - ((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 2;
            y = y1 - ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 2;
        }

        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {
            swap(true, e.getButton(), true);
            System.out.println(e.getButton());
        }
        public void mouseReleased(MouseEvent e) {swap(true, e.getButton(), false);}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }


    public static class KeyBind{
        public Boolean[] mouse, active;
        public Integer[] code;
        
        public KeyBind(Integer a){
            mouse = new Boolean[]{false, null}; active = new Boolean[]{false, false};
            code = new Integer[]{a, Integer.MAX_VALUE};
        }
        public KeyBind(Integer a, Boolean b){
            mouse = new Boolean[]{true, null}; active = new Boolean[]{false, false};
            code = new Integer[]{a, Integer.MAX_VALUE};
        }
    }
}
