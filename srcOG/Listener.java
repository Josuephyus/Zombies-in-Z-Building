package srcOG;

import javax.swing.JFrame;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;
import java.awt.Toolkit;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.HashMap;
import java.util.ArrayList;

public class Listener {

    public static HashMap<String,KeyBind> key;
    static String[] keyBindNames = new String[]{
        "Up", "Left", "Down", "Right",
        "Weapon1", "Weapon2", "Weapon3", "Weapon4",
        "Fire", "Reload", "Interact", "Ability",
        "Dash", "Sprint", "SlowTime", "Pause",
    };
    static KeyBind[] currentKeyBinds = new KeyBind[]{
        new KeyBind(KeyEvent.VK_W), new KeyBind(KeyEvent.VK_A), new KeyBind(KeyEvent.VK_S), new KeyBind(KeyEvent.VK_D),
        new KeyBind(KeyEvent.VK_1), new KeyBind(KeyEvent.VK_2), new KeyBind(KeyEvent.VK_3), new KeyBind(KeyEvent.VK_4),
        new KeyBind(KeyEvent.VK_F), new KeyBind(KeyEvent.VK_R), new KeyBind(KeyEvent.VK_E), new KeyBind(KeyEvent.VK_SPACE),
        new KeyBind(KeyEvent.VK_Q), new KeyBind(KeyEvent.VK_SHIFT), new KeyBind(KeyEvent.VK_P), new KeyBind(KeyEvent.VK_ESCAPE), 
    };

    public static void start(){
        File keybindsFile = new File("data\\cache\\keybinds.txt");
        try {
            if (keybindsFile.createNewFile()){
                System.out.println("Listener.java - Creating new Keybinds");
                FileWriter saveKeyBinds = new FileWriter(keybindsFile);

                for (int i = 0; i < keyBindNames.length; i++){
                    saveKeyBinds.write(keyBindNames[i] +":"
                    + currentKeyBinds[i].mouse[0] + ","
                    + currentKeyBinds[i].code[0] + ","
                    + currentKeyBinds[i].toggle[0]);
                    if (i != keyBindNames.length - 1){
                        saveKeyBinds.write("\n");
                    }
                } saveKeyBinds.close();
                //write a new one with default
            } else {
                System.out.println("Listener.java - Loading saved Keybinds");
                FileReader readKeyBinds = new FileReader(keybindsFile);

                Integer code = readKeyBinds.read();
                ArrayList<Integer> allcodes = new ArrayList<Integer>();
                while (code != -1){
                    allcodes.add(code);
                    code = readKeyBinds.read();
                } readKeyBinds.close();

                String txt = "";
                for (int i = 0; i < allcodes.size(); i++){
                    txt += ((char)(int)allcodes.get(i));
                }
                String[] txtSplit = txt.split("" + (char)10);
                for (int i = 0; i < txtSplit.length; i++){
                    String[] index = txtSplit[i].split(",");
                    keyBindNames[i] = index[0].split(":")[0];
                    if (index[0].split(":")[1].equals("true")){
                        currentKeyBinds[i] = new KeyBind(Integer.parseInt(index[1]), true);
                    } else {
                        currentKeyBinds[i] = new KeyBind(Integer.parseInt(index[1]));
                    }
                    if (index[2].equals("true")){
                        currentKeyBinds[i].toggle[0] = true;
                    }
                }
                //read
            }
        } catch (IOException e){

        }

        key = new HashMap<String,KeyBind>();
        for (int i = 0; i < keyBindNames.length; i++){
            key.put(keyBindNames[i], currentKeyBinds[i]);
            System.out.println("    "+keyBindNames[i] +" > " + currentKeyBinds[i]);
        }
    }

    // IF KeyPressed set Keybind to True
    // IF KeyReleased set Keybind to False
    public static void swap(Boolean mouse, Integer e, Boolean setTo){
        for (int i = 0; i < keyBindNames.length; i++){
            KeyBind thisKey = key.get(keyBindNames[i]);

            if (thisKey.mouse[0] == mouse && e == thisKey.code[0]){
                if (thisKey.toggle[0] && setTo == false)
                    key.get(keyBindNames[i]).active[0] = !key.get(keyBindNames[i]).active[0];
                else if (!thisKey.toggle[0])
                    key.get(keyBindNames[i]).active[0] = setTo;
            }

            else if (thisKey.mouse[1] == mouse && e == thisKey.code[1]){
                if (thisKey.toggle[1] && setTo == false)
                    key.get(keyBindNames[i]).active[1] = !key.get(keyBindNames[i]).active[1];
                else if (!thisKey.toggle[1])
                    key.get(keyBindNames[i]).active[1] = setTo;
            }
        }
    }


    public static boolean check(String name){
        if (key.get(name) == null)return false;
        return (key.get(name).active[0] || key.get(name).active[1]);
    }


    public static class gameKey implements KeyListener{
        public gameKey(){}
        
        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {swap(false,e.getKeyCode(),true);}
        public void keyReleased(KeyEvent e) {swap(false,e.getKeyCode(),false);}
    }
    public static class gameMouse implements MouseListener{
        public static Integer x = 0, y = 0;
        public gameMouse(){}

        public static void updatePosition(){
            int x1 = (int)MouseInfo.getPointerInfo().getLocation().getX();
            int y1 = (int)MouseInfo.getPointerInfo().getLocation().getY();
            x = x1 - ((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 2;
            y = y1 - ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 2;
        }

        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {swap(true, e.getButton(), true);}
        public void mouseReleased(MouseEvent e) {swap(true, e.getButton(), false);}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }


    public static class KeyBind{
        public boolean[] active = new boolean[]{false, false};
        public boolean[] toggle = new boolean[]{false, false};
        public boolean[] mouse;
        public int[] code;
        
        public KeyBind(int a){
            mouse = new boolean[]{false, false};
            code = new int[]{a, Integer.MAX_VALUE};
        }
        public KeyBind(int a, boolean b){
            mouse = new boolean[]{true, false};
            code = new int[]{a, Integer.MAX_VALUE};
        }

        public String toString(){
            if (mouse[0])
                return ("MB " + code[0] + ", Toggle: " + toggle[0]);
            else
                return ("Key " + (char)(int)code[0] + ", Toggle: " + toggle[0]);
        }
    }
}
