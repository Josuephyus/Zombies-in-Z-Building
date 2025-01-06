package srcOG;
import java.util.ArrayList;

import srcOG.behavior.*;
import util.*;

public class Logic {

    public static Player player;
    public static Map map;

    public static Thread t;

    public static ArrayList<Damage> damages = new ArrayList<Damage>();
    public static ArrayList<Entity> zombies = new ArrayList<Entity>();

    static int ticksPerUpdate = 20;
    static Long fps = 60l;
    static Long millisecondsPerFrame = 1000 / fps;

    public static float zombiesPerRound = 10;
    public static int zombiesKilled = 0;
    private static int maxZombiesAllowed = 30;
    private static int roundCounter = 1;

    static Runnable updateLoop = new Runnable(){
        public void run(){
            try{
                while (true){
                    if (Listener.check("SlowTime"))
                        update(ticksPerUpdate / 4);
                    else if (Listener.check("Pause"))
                        update(0);
                    else 
                        update(ticksPerUpdate);
                    Initialize.win.repaint();


                    Thread.sleep(millisecondsPerFrame);
                }
            } catch (InterruptedException e){
                System.out.println("##### Thread Broke #####");
            }
        }
    };

    public static void start(){

        map = new Map();
        Entity.map = map;


        damages = new ArrayList<Damage>();
        player = new Player();
        t = new Thread(updateLoop);
        t.start();
    }

    // Use the overwritten toString command to kill loop
    public static void killLoop(){updateLoop.toString();}

    public static void update(int ticks){

        float time =  (float)ticks / ((float)ticksPerUpdate * fps);
        Listener.gameMouse.updatePosition();

        // If not enough zombies, add more
        if (zombiesKilled + zombies.size() < (int)zombiesPerRound && zombies.size() < maxZombiesAllowed){
            zombies.add(new Zombie(400, 0));
        } else if (zombiesKilled == (int)zombiesPerRound){
            zombiesPerRound *= 1.75;
            zombiesKilled = 0;
            roundCounter++;
            System.out.println("Round " + roundCounter);
        }

        updateProjectiles(time);
        updateEnemies(time);
        updatePlayer(time);
    }

    public static void updatePlayer(float time){

        boolean[] boolean_array = {
            Listener.check("Up"), // 0
            Listener.check("Down"),
            Listener.check("Left"),
            Listener.check("Right"),
            Listener.check("Weapon1"), // 4
            Listener.check("Weapon2"),
            Listener.check("Weapon3"),
            Listener.check("Weapon4"),
            Listener.check("Fire"), // 8
            Listener.check("Reload"),
            Listener.check("Interact"),
            Listener.check("Ability"),
            Listener.check("Dash"), // 12
            Listener.check("Sprint"),
            Listener.check("Interact")
        };

        int x = Listener.gameMouse.x + (int)player.position.x;
        int y = Listener.gameMouse.y + (int)player.position.y;

        
        player.update(new Player.Keys(boolean_array), new Player.Mouse(x, y), time);
        while (player.damages.size() > 0){
            Damage d = player.damages.removeLast();
            if (d != null){
                damages.add(d);
            }
        }
    }

    public static void updateProjectiles(float time){
        for (int i = 0; i < damages.size(); i++){
            if (damages.get(i).isAlive()){
                for (int o = 0; o < zombies.size(); o++){
                    if (damages.get(i).canHit(zombies.get(o))){
                        zombies.get(o).HP[0] -= damages.get(i).damage;
                        if (damages.get(i).tied.hasBuff("LIFESTEAL_5")){
                            damages.get(i).tied.HP[0] += damages.get(i).damage / 20;
                        }
                        damages.get(i).setRange(0);
                    }
                }
                damages.get(i).update(time);
            } else {
                damages.remove(i);
            }
        }
    }

    public static void updateEnemies(float time){
        for (int i = 0; i < zombies.size(); i++){
            player.HP[0] -= zombies.get(i).update(player.position, time);
            if (!zombies.get(i).isAlive()){
                zombiesKilled++;
                zombies.remove(i);
            }
        }
    }
}