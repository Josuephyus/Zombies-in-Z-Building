package entities;

import util.Logic;
import util.math.*;

public class Zombie extends _Entity {
    


    public Zombie(int x, int y) {
        this.position.x = x;
        this.position.y = y;

        movement_speed = 0.4f;
    }

    public void updateAI(float time) {
        Vec2 playerPos = Logic.PLAYER.position;
        
        if (position.distance(playerPos) > 5) {
            float dir = position.directionTo(playerPos);

            position.x += MathF.cos(dir) * movement_speed * time;
            position.y += MathF.sin(dir) * movement_speed * time;
        }
    }
}
