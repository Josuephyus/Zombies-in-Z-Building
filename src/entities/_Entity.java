package entities;

import maps._Map;
import util.math.Vec2;

public class _Entity {

    public Vec2 position = new Vec2(0, 0);
    public float movement_speed = 1f;
    
    public static _Map MAP;

    public _Entity() {  }

    public void updateAI(float time) {

    }
}
