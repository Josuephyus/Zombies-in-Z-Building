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


    public float directionTo(Vec2 o) {

        // Check Simple ones first
        if (this.x == o.x) {
            if (this.y == o.y)
                return 0;
            if (this.y > o.y)
                return MathF.PI * 3 / 2;
            if (this.y < o.y)
                return MathF.PI / 2;
        }
        if (this.y == o.y) {
            if (this.x > o.x)
                return MathF.PI;
            if (this.y < o.y)
                return 0;
        }

        float xD = o.x - this.x;
        float yD = o.y - this.y;
        float dir = MathF.atan(yD / xD);
        if (xD < 0) dir += MathF.PI;

        return dir;
    }
    public float distance(Vec2 o) {
        float x1 = o.x - this.x;
        float y1 = o.y - this.y;
        return MathF.sqrt(x1*x1 + y1*y1);
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
