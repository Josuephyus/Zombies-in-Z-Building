package entities;

import util.math.Vec2;

public class Player extends _Entity {

    public Resource _stamina = new Resource(new float[]{ 100f, 100f, 0.8f, 1f, 0.6f});
    public boolean _stamina_empty = false;

    public float facing = 0;

    public float _sprinting_modifier = 2f;
    public Player() {
        HP = new Resource(100, 1);
    }


    public void move(boolean[] Move, float time) {
        if (Move.length != 6) return;
        boolean MoveU = Move[0];
        boolean MoveD = Move[1];
        boolean MoveR = Move[2];
        boolean MoveL = Move[3];
        boolean Sprint = Move[4];

        float speed = movement_speed * time;


        // Manipulate Stamina
        if (_stamina_empty) {
            _stamina.cur += _stamina.regen_empty * time;
            if (_stamina.cur >= _stamina.max) {
                _stamina.cur = _stamina.max;
                _stamina_empty = false;
            }
        } else if (Sprint) {
            speed *= _sprinting_modifier;
            _stamina.cur -= _stamina.consume * time;
            if (_stamina.cur < 0) {
                _stamina.cur = 0;
                _stamina_empty = true;
            }
        } else if (_stamina.cur < _stamina.max) {
            _stamina.cur += _stamina.regen * time;
            if (_stamina.cur == _stamina.max) {
                _stamina.cur = _stamina.max;
            }
        }


        // Actually Move
        Vec2 movement = new Vec2();
        if (MoveU) movement.y += 1;
        if (MoveD) movement.y -= 1;
        if (MoveR) movement.x += 1;
        if (MoveL) movement.x -= 1;

        if (movement.mag() != 0) {
            movement.normalize();
            movement.multiply(speed);
            position.add(movement);
        }
    }

    public void face(float x, float y) {
        // WindowSpace
        Vec2 WS_mouse = new Vec2(x, y);
        Vec2 WS_self = new Vec2(0, 0);

        facing = WS_self.directionTo(WS_mouse);
    }
}
