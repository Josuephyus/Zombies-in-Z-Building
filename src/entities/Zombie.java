package entities;

import util.Logic;
import util.math.*;

public class Zombie extends _Entity {
    
    byte attack_total = 60;
    byte attack_windup = 30;
    public byte attacking = 0;
    int damage = 10;
    int range = 22;

    public Zombie(int x, int y) {
        this.position.x = x;
        this.position.y = y;

        movement_speed = 0.4f;
    }

    public void updateAI(float time) {
        Vec2 playerPos = Logic.PLAYER.position;
        
        if (attacking != 0) {
            attacking--;
            if (attacking == attack_total - attack_windup) {
                if (position.distance(playerPos) <= range)
                Logic.PLAYER.HP.cur -= damage;
            }
            return;
        }

        if (position.distance(playerPos) > range) {
            float dir = position.directionTo(playerPos);

            position.x += MathF.cos(dir) * movement_speed * time;
            position.y += MathF.sin(dir) * movement_speed * time;
            return;
        } else {
            attacking = attack_total;
        }
    }
}
