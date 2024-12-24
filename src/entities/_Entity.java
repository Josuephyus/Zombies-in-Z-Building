package entities;

import maps._Map;
import util.math.Vec2;

public class _Entity {
    public static _Map MAP;

    public Resource HP = new Resource(10, 0);
    public Vec2 position = new Vec2(0, 0);
    public float movement_speed = 1f;

    public static class Resource {
        public float max, cur;
        public float consume, regen, regen_empty;
        public Resource(float m, float regen) {
            this.max = m; this.cur = m;
            this.regen = regen;
        }
        public Resource(float[] v) {
            this.max = v[0]; this.cur = v[1]; this.consume = v[2];
            this.regen = v[3]; this.regen_empty = v[4];
        }
    }

    

    public _Entity() {  }
    public void updateAI(float time) {  }
}
