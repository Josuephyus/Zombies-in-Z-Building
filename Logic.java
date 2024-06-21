import java.util.ArrayList;

import behavior.Zombie;
import behavior.Entity;
import util.Point;
import util.Projectile;
import util.Laser;

public class Logic {

    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public static ArrayList<Laser> lasers = new ArrayList<Laser>();
    public static ArrayList<Entity> zombies = new ArrayList<Entity>();
    static Boolean FireToggle = true;
    static Boolean SwapToggle = true;

    static Integer tickPerSecond = 20;

    public static void update(Integer time){

        Double totalTime = (time / (tickPerSecond * Initialize.RunnablePanel.fps * 1.0));

        Listener.Mouse.updatePosition();

        // If not enough zombies, add more
        if (zombies.size() < 2){
            zombies.add(new Zombie());
        }

        updateProjectiles(totalTime);
        updateLasers(totalTime);
        updateAreas(totalTime);

        updateEnemies(totalTime);
        updatePlayer(totalTime);
    }

    public static void updatePlayer(Double totalTime){

        //                                          MOVEMENT
        Double speed = (Initialize.game.p.speed) * totalTime;
        behavior.Player player = Initialize.game.p;

        if (Listener.check("Sprint") && player.canDash){
            speed = (player.speed) * totalTime * 2.5;
            player.cEnergy -= 1.5 * totalTime * Initialize.RunnablePanel.fps;
            player.canDash = (player.cEnergy >= 0); 
            
            /*
             *If Sprint(True) and canDash(True)
             *Speed 2.5x walkSpeed
             *Reduce cEnergy by flat amount
             *Update canDash
             */

        } else if (player.cEnergy < player.mEnergy && !player.canDash){ 
            player.cEnergy += (player.mEnergy) * totalTime / 5;
            player.canDash = (player.cEnergy >= player.mEnergy);
        
            /*
             *Else If cEnergy < mEnergy and canDash(False)
             *cEnergy increase based on mEnergy
             *Update canDash
             */

        } else player.cEnergy += player.rEnergy * totalTime;
       
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

        if (Listener.check("MoveUp"))playerToMove.y++;
        if (Listener.check("MoveLeft"))playerToMove.x--;
        if (Listener.check("MoveBackward"))playerToMove.y--;
        if (Listener.check("MoveRight"))playerToMove.x++;

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
            Point mouse = new Point(Listener.Mouse.x, -Listener.Mouse.y);
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

        Initialize.game.p = player;
    }

    public static void updateProjectiles(Double totalTime){
        for (int i = 0; i < projectiles.size(); i++){
            if (projectiles.get(i).isAlive()){
                projectiles.get(i).update(totalTime);
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

    public static void updateLasers(Double totalTime){
        for (int i = 0; i < lasers.size(); i++){
            if (lasers.get(i).isAlive()){
                lasers.get(i).update(totalTime);
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

    public static void updateAreas(Double totalTime){

    }

    public static void updateEnemies(Double totalTime){
        for (int i = 0; i < zombies.size(); i++){
            Initialize.game.p.cHP -= zombies.get(i).update(Initialize.game.p.position, totalTime);
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