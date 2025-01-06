package util;

import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.awt.event.KeyListener;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Listener {
    public Listener(){ KL = new KeyL(this); ML = new MouseL(this); }

    public void setWindow(JFrame window) { ML.mainWindow = window; }

    public KeyL KL;
    public MouseL ML;
    HashMap<String, KeyBind> key;
    String[] KeybindNames;
    KeyBind[] KeybindBinds;
    public void Start() {
        KeybindNames = new String[]{
            "MoveU", "MoveL", "MoveD", "MoveR",
            "Weapon1", "Weapon2", "Weapon3", "Weapon4",
            "Fire", "Reload", "Interact", "Ability",
            "Dash", "Sprint", "SlowTime", "Pause"
        };
        KeybindBinds = new KeyBind[]{
            new KeyBind(KeyEvent.VK_W), new KeyBind(KeyEvent.VK_A), new KeyBind(KeyEvent.VK_S), new KeyBind(KeyEvent.VK_D),
            new KeyBind(KeyEvent.VK_1), new KeyBind(KeyEvent.VK_2), new KeyBind(KeyEvent.VK_3), new KeyBind(KeyEvent.VK_4),
            new KeyBind(KeyEvent.VK_F), new KeyBind(KeyEvent.VK_R), new KeyBind(KeyEvent.VK_E), new KeyBind(KeyEvent.VK_SPACE),
            new KeyBind(KeyEvent.VK_Q), new KeyBind(KeyEvent.VK_SHIFT), new KeyBind(KeyEvent.VK_P), new KeyBind(KeyEvent.VK_ESCAPE), 
        };

        File keybindsFile = new File("data\\cache\\keybinds.txt");
        try {
            if (keybindsFile.createNewFile()){
                System.out.println("Listener.java - Creating new Keybinds");
                FileWriter saveKeyBinds = new FileWriter(keybindsFile);

                for (int i = 0; i < KeybindNames.length; i++){
                    saveKeyBinds.write(KeybindNames[i] +":"
                    + KeybindBinds[i].mouse[0] + ","
                    + KeybindBinds[i].code[0] + ","
                    + KeybindBinds[i].toggle[0]);
                    if (i != KeybindNames.length - 1){
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
                    KeybindNames[i] = index[0].split(":")[0];
                    if (index[0].split(":")[1].equals("true")){
                        KeybindBinds[i] = new KeyBind(Integer.parseInt(index[1]), true);
                    } else {
                        KeybindBinds[i] = new KeyBind(Integer.parseInt(index[1]));
                    }
                    if (index[2].equals("true")){
                        KeybindBinds[i].toggle[0] = true;
                    }
                }
                //read
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        key = new HashMap<String,KeyBind>();
        for (int i = 0; i < KeybindNames.length; i++){
            key.put(KeybindNames[i], KeybindBinds[i]);
            System.out.println("    " + KeybindNames[i] +" > " + KeybindBinds[i]);
        }
    }

    void swap(Boolean mouse, Integer e, Boolean setTo){
        for (int i = 0; i < KeybindNames.length; i++){
            KeyBind thisKey = key.get(KeybindNames[i]);

            if (thisKey.mouse[0] == mouse && e == thisKey.code[0]){
                if (thisKey.toggle[0] && setTo == false)
                    key.get(KeybindNames[i]).active[0] = !key.get(KeybindNames[i]).active[0];
                else if (!thisKey.toggle[0])
                    key.get(KeybindNames[i]).active[0] = setTo;
            }

            else if (thisKey.mouse[1] == mouse && e == thisKey.code[1]){
                if (thisKey.toggle[1] && setTo == false)
                    key.get(KeybindNames[i]).active[1] = !key.get(KeybindNames[i]).active[1];
                else if (!thisKey.toggle[1])
                    key.get(KeybindNames[i]).active[1] = setTo;
            }
        }
    }

    public boolean check(String name){
        if (key.get(name) == null)return false;
        return (key.get(name).active[0] || key.get(name).active[1]);
    }

    static class KeyBind {
        public boolean[] active = new boolean[]{false, false};
        public boolean[] toggle = new boolean[]{false, false};
        public boolean[] mouse = new boolean[]{false, false};
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

    static class KeyL implements KeyListener {
        Listener L;
        public KeyL(Listener L) { this.L = L; }

        public void keyTyped(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {
            L.swap(false, e.getKeyCode(), true);
        }
        public void keyReleased(KeyEvent e) {
            L.swap(false, e.getKeyCode(), false);
        }
    }

    static class MouseL implements MouseListener {
        Listener L;
        int x, y;
        int scr_w, scr_h;
        public JFrame mainWindow;
        public MouseL(Listener L) {
            this.L = L;
            scr_w = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            scr_h = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        }

        public void mouseUpdate() {
            int x1 = (int)MouseInfo.getPointerInfo().getLocation().getX();
            int y1 = (int)MouseInfo.getPointerInfo().getLocation().getY();
            scr_w = mainWindow.getWidth();
            scr_h = mainWindow.getHeight();
            int scr_x = mainWindow.getX();
            int scr_y = mainWindow.getY();
            x = x1 - (scr_w / 2) - scr_x;
            y = -y1 + (scr_h / 2) + scr_y;
        }

        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {
            L.swap(true, e.getButton(), true);
        }
        public void mouseReleased(MouseEvent e) {
            L.swap(true, e.getButton(), false);
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }



}
