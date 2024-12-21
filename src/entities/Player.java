package entities;

import util.math.Vec2;

public class Player extends _Entity {
    
    public Vec2 position = new Vec2();

    public float _movement_speed = 1f;

    public float _stamina_max = 100f;
    public float _stamina_cur = _stamina_max;
    public float _stamina_consume = 0.8f;
    public float _stamina_regen = 1f;
    public float _stamina_regen_empty = (100f / 60f) / 3f;
    public boolean _stamina_empty = false;

    public float _sprinting_modifier = 2f;


    public void move(boolean[] Move, float time) {
        if (Move.length != 6) return;
        boolean MoveU = Move[0];
        boolean MoveD = Move[1];
        boolean MoveR = Move[2];
        boolean MoveL = Move[3];
        boolean Sprint = Move[4];

        float speed = _movement_speed * time;


        // Manipulate Stamina
        if (_stamina_empty) {
            _stamina_cur += _stamina_regen_empty * time;
            if (_stamina_cur >= _stamina_max) {
                _stamina_cur = _stamina_max;
                _stamina_empty = false;
            }
        } else if (Sprint) {
            speed *= _sprinting_modifier;
            _stamina_cur -= _stamina_consume * time;
            if (_stamina_cur < 0) {
                _stamina_cur = 0;
                _stamina_empty = true;
            }
        } else if (_stamina_cur < _stamina_max) {
            _stamina_cur += _stamina_regen * time;
            if (_stamina_cur == _stamina_max) {
                _stamina_cur = _stamina_max;
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
}
