import java.util.ArrayList;

import behavior.Zombie;
import behavior.Entity;
import textures.Texture;
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

        // If not enough zombies, add more
        if (zombies.size() < 2){
            zombies.add(new Zombie());
        }

        // Update Projectiles
        for (int i = 0; i < projectiles.size(); i++){
            if (projectiles.get(i).isAlive()){
                projectiles.get(i).update(time, tickPerSecond);
                for (int o = 0; o < zombies.size(); o++){
                    if (projectiles.get(i).position.distance(zombies.get(o).position) < zombies.get(o).size + projectiles.get(i).radius){
                        zombies.get(o).cHP -= projectiles.get(i).damage;
                        projectiles.get(i).lifespan = 0.0;
                        break;
                    }
                }
            } else {
                projectiles.remove(i);
            }
        }


        // Update Lasers
        for (int i = 0; i < lasers.size(); i++){
            if (lasers.get(i).isAlive()){
                lasers.get(i).update(time, tickPerSecond);
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


        // Update zombies
        for (int i = 0; i < zombies.size(); i++){
            Initialize.game.p.cHP -= zombies.get(i).update(Initialize.game.p.position, time, tickPerSecond);
            if (!zombies.get(i).isAlive()){
                for (int o = 0; o < zombies.size(); o++){
                    if (zombies.get(i).ID == zombies.get(o).ID){
                        zombies.remove(o); break;
                    }
                }
            }
        }


        Listener.Mouse.updatePosition();
        updatePlayer(time);
    }

    public static void updatePlayer(Integer time){

        // Checking what should the player's speed be assda well as assigning a speed value
        Double speed = Initialize.game.p.speed * time / (100 * tickPerSecond);
        if (Listener.check("Sprint") && Initialize.game.p.canDash){
            speed = Initialize.game.p.speed * time / (40 * tickPerSecond);
            Initialize.game.p.cEnergy -= 1.2 * time / tickPerSecond;
            Initialize.game.p.canDash = (Initialize.game.p.cEnergy >= 0);
        } else if (Initialize.game.p.cEnergy < Initialize.game.p.mEnergy && !Initialize.game.p.canDash){
            Initialize.game.p.cEnergy += Initialize.game.p.mEnergy * time / (300 * tickPerSecond);
            Initialize.game.p.canDash = (Initialize.game.p.cEnergy >= Initialize.game.p.mEnergy);
        } else Initialize.game.p.cEnergy += Initialize.game.p.rEnergy * time / (100 * tickPerSecond);
        
        //If cur is higher than max, set cur to max
        if (Initialize.game.p.cEnergy > Initialize.game.p.mEnergy)Initialize.game.p.cEnergy = Initialize.game.p.mEnergy;

        //pm is a temporary point to find the direction the player is moving
        Point pm = new Point(Initialize.game.p.position.x, Initialize.game.p.position.y);
        // Player Movement
        if (Listener.check("MoveUp"))pm.y++;
        if (Listener.check("MoveLeft"))pm.x--;
        if (Listener.check("MoveBackward"))pm.y--;
        if (Listener.check("MoveRight"))pm.x++;
        // Move the player if a move command was input
        if (Initialize.game.p.position.distance(pm) != 0){
            Double theta = Initialize.game.p.position.directionTo(pm);
            Initialize.game.p.position.x += Math.cos(theta) * speed;
            Initialize.game.p.position.y += Math.sin(theta) * speed;
        }


        // Swap Weapons
        if (Listener.check("SwapLeft") && SwapToggle){
            Texture.selectedWeapon--; SwapToggle = false;
        } else if (Listener.check("SwapRight") && SwapToggle){
            Texture.selectedWeapon++; SwapToggle = false;
        } else if (!Listener.check("SwapLeft") && !Listener.check("SwapRight")){
            SwapToggle = true;
        }
        if (Texture.selectedWeapon == -1)Texture.selectedWeapon = Texture.gun.length - 1;
        if (Texture.selectedWeapon == Texture.gun.length)Texture.selectedWeapon = 0;

        // Shoot if Conditions
        if (Listener.check("Fire") && FireToggle){
            String i = Initialize.game.p.fireType();
            Point mouse = new Point(Listener.Mouse.x, -Listener.Mouse.y);
            if (i.equals("Projectile"))projectiles.add(Initialize.game.p.fireProjectile(mouse));
            else lasers.add(Initialize.game.p.fireLaser(mouse));
            FireToggle = false;
        } else if (!Listener.check("Fire")){
            FireToggle = true;
        }
    }
}