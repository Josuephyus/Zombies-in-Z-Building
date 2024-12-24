package util;

import java.awt.Graphics;
import util.math.Vec2;

import javax.swing.JFrame;

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
        disp_w = mainWindow.getWidth() - 14;
        disp_h = mainWindow.getHeight() - 37;
        scale_w = ((float)disp_w / base_w);
        scale_h = ((float)disp_h / base_h);

        float x = 0, y = 0;
        int w = 1, h = 1;
        int center_x = base_w / 2 - LISTENER.ML.x / 3, center_y = base_h / 2 - LISTENER.ML.y / 3;

        Vec2 player_pos = Logic.PLAYER.position;
        x = center_x; y = center_y; w = 40; h = 40;
        drawCenteredRect(g, x, y, w, h);

        x = 10; y = base_h - 50; w = base_w / 3; h = 40;
        w *= Logic.PLAYER._stamina_cur / Logic.PLAYER._stamina_max;
        drawRect(g, x, y, w, h);

        for (int i = 0; i < Logic.ENEMIES.size(); i++) {
            Vec2 pos = Logic.ENEMIES.get(i).position;
            x = pos.x - player_pos.x + center_x;
            y = -pos.y + player_pos.y + center_y;
            w = 30; h = 30;

            drawCenteredRect(g, x, y, w, h);
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
        g.drawRect(
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
}
