import java.util.ArrayList;

import behavior.Zombie;
import textures.Texture;
import util.Point;

public class Logic {

    public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    static Boolean LCToggle = true;
    static Boolean SwapToggle = true;

    static Integer tickPerSecond = 20;

    public static void update(Integer time){
        if (zombies.size() < 2){
            zombies.add(new Zombie());
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
        if (Initialize.game.p.cEnergy > Initialize.game.p.mEnergy)Initialize.game.p.cEnergy = Initialize.game.p.mEnergy;

        //pm is a temporary point to find the direction the player is moving
        Point pm = new Point(Initialize.game.p.position.x, Initialize.game.p.position.y);
        // Player Movement
        if (Listener.check("MoveForward"))pm.y++;
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


        // Update Projectiles
        for (int i = 0; i < projectiles.size(); i++){
            projectiles.get(i).update(time);
        }
        // Shoot if Conditions
        if (Listener.check("Fire") && LCToggle){
            LCToggle = false;
            projectiles.add(
                new Projectile(
                    (int)Math.round(Initialize.game.p.position.x),
                    (int)Math.round(-Initialize.game.p.position.y),
                    Initialize.game.p.position.directionTo(
                        new Point(
                            Listener.Mouse.x+Initialize.game.p.position.x, 
                            Listener.Mouse.y+Initialize.game.p.position.y
                        )
                    ),
                    0
                    )
            );
        } else if (!Listener.check("Fire")){
            LCToggle = true;
        }

        // Update zombies
        for (int i = 0; i < zombies.size(); i++){
            Initialize.game.p.cHP -= zombies.get(i).update(new Point(Initialize.game.p.position.x, -Initialize.game.p.position.y), time, tickPerSecond);
            if (!zombies.get(i).isAlive()){
                for (int o = 0; o < zombies.size(); o++){
                    if (zombies.get(i).ID == zombies.get(o).ID){
                        zombies.remove(o); break;
                    }
                }
            }
        }
    }

    public static class Projectile{
        static Integer nextID = 0;
        public Integer ownerID, ID;
        public Integer width, height, speed, damage;
        public Double rotation, lifespan;
        public Point position;

        public Projectile(Integer x, Integer y, Double direction, Integer type){
            this.position = new Point(x, y);
            this.ID = nextID; nextID++;
            this.rotation = direction; lifespan = 2.0;
            if (type == 0){
                this.width = 10;
                this.height = 4;
                this.speed = 1500;
                this.damage = 100;
            }
        }

        public void update(Integer time){
            position.x += Math.cos(rotation) * speed * time / (tickPerSecond * 100);
            position.y += Math.sin(rotation) * speed * time / (tickPerSecond * 100);
            lifespan -= (double)time / (tickPerSecond * 100);
            if (lifespan <= 0){
                for (int i = 0; i < projectiles.size(); i++){
                    if (this.ID == projectiles.get(i).ID){
                        projectiles.remove(i);
                    }
                }
            } else {
                for (Zombie i : zombies){
                    if (this.position.distance(i.position) < i.size + 2){
                        i.cHP -= damage; lifespan = 0.0; return;
                    }
                }
            }
        }
    }
}