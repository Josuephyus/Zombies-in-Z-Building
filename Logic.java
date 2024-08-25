import java.util.ArrayList;

import behavior.Zombie;
import behavior.Entity;
import behavior.Player;

import util.*;

public class Logic {

    public static Player player;

    public static ArrayList<Damage> damages = new ArrayList<Damage>();
    public static ArrayList<Entity> zombies = new ArrayList<Entity>();
    static Boolean FireToggle = true;
    static Boolean SwapToggle = true;

    static int ticksPerUpdate = 20;
    static Long fps = 60l;
    static Long millisecondsPerFrame = 1000 / fps;

    static Runnable updateLoop = new Runnable(){
        Thread t;
        public boolean equals(Object a){
            t = new Thread(this); t.start();
            System.out.println("Logic.java - Starting Loop");
            return true;
        }
        public void run(){
            while (t != null){
                if (Listener.check("SlowTime"))
                    update(ticksPerUpdate / 4);
                else if (Listener.check("Pause"))
                    update(0);
                else 
                    update(ticksPerUpdate);
                Initialize.win.repaint();
                try{
                    Thread.sleep(millisecondsPerFrame);
                } catch (InterruptedException e){
                    System.out.println("##### Thread Broke #####");
                }
            }
        }
        public String toString(){
            t = null;
            return "";
        }
    };

    public static void start(){
        damages = new ArrayList<Damage>();
        FireToggle = true;
        SwapToggle = true;
        player = new Player();
        updateLoop.equals(""); // Start (Overwritten)
    }

    // Use the overwritten toString command to kill loop
    public static void killLoop(){updateLoop.toString();}

    public static void update(int ticks){

        float time =  (float)ticks / ((float)ticksPerUpdate * fps);
        Listener.gameMouse.updatePosition();

        // If not enough zombies, add more
        if (zombies.size() < 1){
            zombies.add(new Zombie(300, 300));
        }

        updateProjectiles(time);
        updateEnemies(time);
        updatePlayer(time);
    }

    public static void updatePlayer(float time){

        boolean[] boolean_array = {
            Listener.check("Up"),
            Listener.check("Down"),
            Listener.check("Left"),
            Listener.check("Right"),
            Listener.check("Sprint"),
            Listener.check("SwapLeft"),
            Listener.check("SwapRight"),
            Listener.check("Fire"),
        };

        int x = Listener.gameMouse.x + (int)player.position.x;
        int y = Listener.gameMouse.y + (int)player.position.y;

        
        player.update(new Player.Keys(boolean_array), new Player.Mouse(x, y), time);
        while (player.damages.size() > 0){
            damages.add(player.damages.removeLast());
        }
    }

    public static void updateProjectiles(float time){
        for (int i = 0; i < damages.size(); i++){
            if (damages.get(i).isAlive()){
                damages.get(i).update(time);
                for (int o = 0; o < zombies.size(); o++){
                    if (damages.get(i).canHit(zombies.get(o))){
                        zombies.get(o).HP[0] -= damages.get(i).damage;
                        damages.get(i).setRange(0);
                    }
                }
            } else {
                damages.remove(i);
            }
        }
    }

    public static void updateEnemies(float time){
        for (int i = 0; i < zombies.size(); i++){
            player.HP[0] -= zombies.get(i).update(player.position, time);
            if (!zombies.get(i).isAlive()){
                zombies.remove(i);
            }
        }
    }
}