package util;

import java.util.ArrayList;

import entities.*;
import maps._Map;

import util.math.Vec2;

public class Logic {
    
    static Listener LISTENER;
    public static void setListener(Listener L) { LISTENER = L; }

    public static Player PLAYER;
    public static _Map MAP;
    public static ArrayList<_Entity> ENEMIES;
    public static int round = 0;

    public static void Start() {
        MAP = new _Map();
        _Entity.MAP = MAP;
    
        PLAYER = new Player();
        ENEMIES = new ArrayList<>();
    }


    static float time;
    static boolean setSpeed() {
        if (LISTENER.check("Pause"))
            return false;
        
        time = 1f;
        if (LISTENER.check("SlowTime"))
            time = 0.25f;
        return true;
    }


    public static void update() {
        LISTENER.ML.mouseUpdate();
        boolean doUpdate = setSpeed();
        if (!doUpdate) return;

        updatePlayer();
        updateEnemies();
        updateRound();
    }

    static boolean waiting_for_new_round = false;
    static int wait = 0;
    static int zombie_count = 0;
    static int zombie_cap = 20;
    static int zombie_cooldown = 10;
    static int zombie_cooldown_cur = 0;
    static void updateRound() {

        // If round ended, wait to start the next one
        if (!waiting_for_new_round) {
            if (ENEMIES.size() == 0) {
                round++;
                waiting_for_new_round = true;
            }
        } else {
            wait--;
            waiting_for_new_round = wait > 0;

            if (!waiting_for_new_round) {
                zombie_count = round * 2 + 1;
            }
        }
    }

    static void updateEnemies() {
        for (int i = 0; i < ENEMIES.size(); i++) {
            if (ENEMIES.get(i).HP.cur <= 0) {
                ENEMIES.remove(i);
                continue;
            }

            ENEMIES.get(i).updateAI(time);
        }

        if (ENEMIES.size() < 1) {
            round++;
            for (int i = 0; i < round * 2 + 1; i++) {
                ENEMIES.add(new Zombie(0, 0));
            }
        }
    }


    static void updatePlayer() {
        
        PLAYER.upTick(time);

        // Movement
        boolean[] Move = new boolean[] {
            LISTENER.check("MoveU"),
            LISTENER.check("MoveD"),
            LISTENER.check("MoveR"),
            LISTENER.check("MoveL"),
            LISTENER.check("Sprint"),
            LISTENER.check("Dash"),
        };

        PLAYER.move(Move, time);

        
        // Facing
        PLAYER.face(LISTENER.ML.x, LISTENER.ML.y);

        if (LISTENER.check("Fire")) {
            PLAYER.attack();
        }
    }
}
