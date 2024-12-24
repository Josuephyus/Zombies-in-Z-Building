package util;

import javax.swing.JFrame;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;

import util.math.Vec2;
import entities.*;
import entities._Entity.Resource;;

public class Artist {
    
    static Listener LISTENER;
    public static void setListener(Listener L) { LISTENER = L; }

    static JFrame mainWindow;
    public static void setWindow(JFrame window) {
        mainWindow = window;
    }

    public static void LoadImages() {

    }

    static float scale_w, scale_h;
    static float disp_w, disp_h;
    static int base_w = 640, base_h = 360;
    // 640 x 360 as the DEFAULT, otherwise it stretches
    public static void draw(Graphics g) {
        
        g.setColor(Color.black);
        fillRect(g, 0, 0, base_w, base_h);
        g.setColor(Color.white);

        ArrayList<_Entity> E = Logic.ENEMIES;

        disp_w = mainWindow.getWidth() - 14;
        disp_h = mainWindow.getHeight() - 37;
        scale_w = ((float)disp_w / base_w);
        scale_h = ((float)disp_h / base_h);

        float x = 0, y = 0;
        int w = 1, h = 1;
        int center_x = base_w / 2 - LISTENER.ML.x / 3, center_y = base_h / 2 - LISTENER.ML.y / 3;

        Vec2 player_pos = Logic.PLAYER.position;
        x = center_x; y = center_y; w = 40; h = 40;
        g.setColor(Color.cyan);
        fillCenteredCircle(g, x, y, w, h);


        x = 10; y = base_h - 50; w = base_w / 3; h = 40;
        g.setColor(Color.gray);
        fillRect(g, x, y, w, h);

        w *= Logic.PLAYER.HP.cur / Logic.PLAYER.HP.max;
        g.setColor(Color.red);
        fillRect(g, x, y, w, h);

        y -= 40; h = 30;
        w = base_w / 4;
        g.setColor(Color.gray);
        fillRect(g, x, y, w, h);

        w *= Logic.PLAYER._stamina.cur / Logic.PLAYER._stamina.max;
        g.setColor(Color.yellow);
        fillRect(g, x, y, w, h);

        for (int i = 0; i < E.size(); i++) {
            Vec2 pos = E.get(i).position;
            Resource hp = E.get(i).HP;

            if (E.get(i) instanceof Zombie) {
                x = pos.x - player_pos.x + center_x;
                y = -pos.y + player_pos.y + center_y;
                w = 30; h = 30;
                Zombie zombie = (Zombie)E.get(i);
                
                if (zombie.attacking == 0) {
                    g.setColor(new Color(0f, 1f, 0f));
                } else {
                    g.setColor(new Color(0f, 0.8f, 0.4f));
                }

                fillCenteredCircle(g, x, y, w, h);

                w += 8;
                h += 24;
                x -= w / 2;
                y -= h / 2;
                h = 8;
                g.setColor(Color.gray);
                fillRect(g, x, y, w, h);

                w *= hp.cur / hp.max;
                g.setColor(Color.red);
                fillRect(g, x, y, w, h);
            }
        }
    }


    
    static void drawRect(Graphics g, float x, float y, int w, int h) {
        g.drawRect(
            (int)(x * scale_w),
            (int)(y * scale_h),
            (int)((float)w * scale_w),
            (int)((float)h * scale_h)
        );
    }

    static void drawCenteredRect(Graphics g, float x, float y, int w, int h) {
        float new_x = (float)x - ((float)w / 2);
        float new_y = (float)y - ((float)h / 2);
        drawRect(g, new_x, new_y, w, h);
    }

    static void fillRect(Graphics g, float x, float y, int w, int h) {
        g.fillRect(
            (int)(x * scale_w),
            (int)(y * scale_h),
            (int)((float)w * scale_w),
            (int)((float)h * scale_h)
        );
    }

    static void fillCenteredRect(Graphics g, float x, float y, int w, int h) {
        float new_x = (float)x - ((float)w / 2);
        float new_y = (float)y - ((float)h / 2);
        fillRect(g, new_x, new_y, w, h);
    }

    static void fillCircle(Graphics g, float x, float y, float w, float h) {
        g.fillOval(
            (int)(x * scale_w),
            (int)(y * scale_h),
            (int)(w * scale_w),
            (int)(h * scale_h)
        );
    }

    static void fillCenteredCircle(Graphics g, float x, float y, float w, float h) {
        float new_x = x - (w / 2);
        float new_y = y - (h / 2);
        fillCircle(g, new_x, new_y, w, h);
    }
}
