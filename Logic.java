import java.util.ArrayList;

import behavior.Zombie;
import behavior.Entity;
import behavior.Player;
import behavior.Weapons;
import util.Point;
import util.Projectile;
import util.Laser;

public class Logic {

    public static Player player;

    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public static ArrayList<Laser> lasers = new ArrayList<Laser>();
    public static ArrayList<Entity> zombies = new ArrayList<Entity>();
    static Boolean FireToggle = true;
    static Boolean SwapToggle = true;

    static Integer ticksPerUpdate = 20;
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
        projectiles = new ArrayList<Projectile>();
        lasers = new ArrayList<Laser>();
        zombies = new ArrayList<Entity>();
        FireToggle = true;
        SwapToggle = true;
        Weapons.start();
        player = new Player();
        updateLoop.equals(""); // Start (Overwritten)
    }

    // Use the overwritten toString command to kill loop
    public static void killLoop(){updateLoop.toString();}

    public static void update(Integer ticks){

        Double time =  ticks / ((double)ticksPerUpdate * fps);
        Listener.gameMouse.updatePosition();

        // If not enough zombies, add more
        if (zombies.size() < 2){
            zombies.add(new Zombie());
        }

        updateProjectiles(time);
        updateLasers(time);
        updateAreas(time);

        updateEnemies(time);
        updatePlayer(time);
    }

    public static void updatePlayer(Double time){
        // ##### MOVEMENT #####
        Double speed = (player.speed) * time;

        if (Listener.check("Sprint") && player.canDash){
            speed = (player.speed) * time * 2.5;
            player.cEnergy -= 1.5 * time * fps;
            player.canDash = (player.cEnergy >= 0); 
            
            /*
             *If Sprint(True) and canDash(True)
             *Speed 2.5x walkSpeed
             *Reduce cEnergy by flat amount
             *Update canDash
             */

        } else if (player.cEnergy < player.mEnergy && !player.canDash){ 
            player.cEnergy += (player.mEnergy) * time / 5;
            player.canDash = (player.cEnergy >= player.mEnergy);
        
            /*
             *Else If cEnergy < mEnergy and canDash(False)
             *cEnergy increase based on mEnergy
             *Update canDash
             */

        } else player.cEnergy += player.rEnergy * time;
       
            /* (Increase cEnergy if canDash(True))
             * Else cEnergy increase based on rEnergy
             */ 


        if (player.cEnergy > player.mEnergy)
        player.cEnergy = player.mEnergy;

            /* (Fix Energy Overflow)
            * If cEnergy > mEnergy
            * cEnergy = mEnergy
            */


        Point playerToMove = new Point(player.position.x, player.position.y);

        if (Listener.check("Up"))playerToMove.y++;
        if (Listener.check("Left"))playerToMove.x--;
        if (Listener.check("Down"))playerToMove.y--;
        if (Listener.check("Right"))playerToMove.x++;

        if (player.position.distance(playerToMove) != 0){
            Double theta = player.position.directionTo(playerToMove);
            player.position.x += Math.cos(theta) * speed;
            player.position.y += Math.sin(theta) * speed;
        }
        
        /* (Move the player if they moved)
         * If playerToMove == player.position
         * player.position += ( x movement , y movement )
         */


        //                                              Swap Weapons
        if (Listener.check("SwapLeft") && SwapToggle){
            player.weaponIndex--; SwapToggle = false;
            if (player.weaponIndex == -1)player.weaponIndex = player.weapons.length - 1;
            if (player.weapons[player.weaponIndex] == null){
                player.weaponIndex++; if (player.weaponIndex == player.weapons.length)player.weaponIndex = 0;
            }
        } else if (Listener.check("SwapRight") && SwapToggle){
            player.weaponIndex++; SwapToggle = false;
            if (player.weaponIndex == player.weapons.length)player.weaponIndex = 0;
            if (player.weapons[player.weaponIndex] == null){
                player.weaponIndex--; if (player.weaponIndex == -1)player.weaponIndex = player.weapons.length - 1;
            }
        } else if (!Listener.check("SwapLeft") && !Listener.check("SwapRight")){
            SwapToggle = true;
        }


        /* (Swap Weapons)
         * If swapleft, swap to a leftward weapon (if possible)
         * If swapright, swap to a rightward weapon (if possible)
         * else enable the toggle
         * 
         * Overflow Protection
         */


        //                                              Shooting
        if (Listener.check("Fire") && FireToggle){
            Point mouse = new Point(Listener.gameMouse.x, -Listener.gameMouse.y);
            if (player.fireType().equals("Projectile")){
                for (util.Projectile i : player.fireProjectile(mouse)){
                    projectiles.add(i);
                }
            }
            else lasers.add(player.fireLaser(mouse));
            FireToggle = false;
        } else if (!Listener.check("Fire")){
            FireToggle = true;
        }
        /*
         * If can fire, fire
         * else, don't fire (duh)
         * 
         * adjust toggle
         */

    }

    public static void updateProjectiles(Double time){
        for (int i = 0; i < projectiles.size(); i++){
            if (projectiles.get(i).isAlive()){
                projectiles.get(i).update(time);
                for (int o = 0; o < zombies.size(); o++){
                    if (projectiles.get(i).position.distance(zombies.get(o).position) < zombies.get(o).size + projectiles.get(i).radius){
                        zombies.get(o).cHP -= projectiles.get(i).damage;
                        projectiles.get(i).curRange = projectiles.get(i).maxRange;
                        break;
                    }
                }
            } else {
                projectiles.remove(i);
            }
        }
    }

    public static void updateLasers(Double time){
        for (int i = 0; i < lasers.size(); i++){
            if (lasers.get(i).isAlive()){
                lasers.get(i).update(time);
                for (int o = 0; o < zombies.size(); o++){
                    if (lasers.get(i).hitbox.distance(zombies.get(o).position) < zombies.get(o).size - 3 && !lasers.get(i).alrHit(zombies.get(o).ID)){
                        zombies.get(o).cHP -= lasers.get(i).damage;
                        lasers.get(i).alreadyHit.add(zombies.get(o).ID);
                    }
                }
            } else {
                lasers.remove(i);            
            }
        }
    }

    public static void updateAreas(Double time){

    }

    public static void updateEnemies(Double time){
        for (int i = 0; i < zombies.size(); i++){
            player.cHP -= zombies.get(i).update(player.position, time);
            if (!zombies.get(i).isAlive()){
                for (int o = 0; o < zombies.size(); o++){
                    if (zombies.get(i).ID == zombies.get(o).ID){
                        zombies.remove(o); break;
                    }
                }
            }
        }
    }
}