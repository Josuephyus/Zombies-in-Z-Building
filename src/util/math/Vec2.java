package util.math;

import java.awt.Point;

public class Vec2 {
    public float x, y;

    public Vec2(float x, float y) {
        this.x = x; this.y = y;
    }
    public Vec2() {
        this(0, 0);
    }


    public void add(Vec2 o) {
        this.x += o.x; this.y += o.y;
    }
    public void multiply(Vec2 o) {
        this.x *= o.x; this.y *= o.y;
    }
    public void multiply(float o) {
        this.x *= o; this.y *= o;
    }


    public float mag() {
        return MathF.sqrt(x*x + y*y);
    }
    public void normalize() {
        float denominator = mag();
        this.x /= denominator;
        this.y /= denominator;
    }
    public Point toPoint() {
        return new Point((int)x, (int)y);
    }
}
