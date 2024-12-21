package util;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Artist {
    
    static JFrame mainWindow;
    public static void setWindow(JFrame window) {
        mainWindow = window;
    }

    public static void LoadImages() {

    }

    static int disp_w, disp_h;
    static float scale_w, scale_h;

    // 640 x 360 as the DEFAULT, otherwise it stretches
    public static void draw(Graphics g) {
        disp_w = mainWindow.getWidth() - 14;
        disp_h = mainWindow.getHeight() - 37;
        scale_w = ((float)disp_w / 640);
        scale_h = ((float)disp_h / 360);

        int x = 0, y = 0, w = 1, h = 1;

        Point pos = Logic.PLAYER.position.toPoint();
        x = pos.x; y = -pos.y; w = 40; h = 40;
        drawCenteredRect(g, x, y, w, h);

        x = 10; y = disp_h - 50; w = disp_w / 3; h = 40;
        w *= Logic.PLAYER._stamina_cur / Logic.PLAYER._stamina_max;
        g.drawRect(x, y, w, h);
    }

    static void drawCenteredRect(Graphics g, int x, int y, int w, int h) {
        g.fillRect(
            (int)(((float)x - ((float)w / 2)) * scale_w),
            (int)(((float)y - ((float)h / 2)) * scale_h),
            (int)((float)w * scale_w),
            (int)((float)h * scale_h)
        );
    }
}
