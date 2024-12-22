package util;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

public class Artist {
    
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

        int x = 0, y = 0, w = 1, h = 1;

        Point player_pos = Logic.PLAYER.position.toPoint();
        x = base_w / 2; y = base_h / 2; w = 40; h = 40;
        drawCenteredRect(g, x, y, w, h);

        x = 10; y = base_h - 50; w = base_w / 3; h = 40;
        w *= Logic.PLAYER._stamina_cur / Logic.PLAYER._stamina_max;
        drawRect(g, x, y, w, h);

        for (int i = 0; i < Logic.ENEMIES.size(); i++) {
            Point pos = Logic.ENEMIES.get(i).position.toPoint();
            x = pos.x - player_pos.x + (base_w / 2);
            y = -pos.y + player_pos.y + (base_h / 2);
            w = 30; h = 30;

            drawCenteredRect(g, x, y, w, h);
        }
    }


    
    static void drawRect(Graphics g, int x, int y, int w, int h) {
        g.drawRect(
            (int)((float)x * scale_w),
            (int)((float)y * scale_h),
            (int)((float)w * scale_w),
            (int)((float)h * scale_h)
        );
    }

    static void drawCenteredRect(Graphics g, int x, int y, int w, int h) {
        float new_x = (float)x - ((float)w / 2);
        float new_y = (float)y - ((float)h / 2);
        drawRect(g, (int)new_x, (int)new_y, w, h);
    }

    static void fillRect(Graphics g, int x, int y, int w, int h) {
        g.drawRect(
            (int)((float)x * scale_w),
            (int)((float)y * scale_h),
            (int)((float)w * scale_w),
            (int)((float)h * scale_h)
        );
    }

    static void fillCenteredRect(Graphics g, int x, int y, int w, int h) {
        float new_x = (float)x - ((float)w / 2);
        float new_y = (float)y - ((float)h / 2);
        fillRect(g, (int)new_x, (int)new_y, w, h);
    }
}
