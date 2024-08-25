package util;

public class Line {
    public Point s, e;
    public double direction, slope;
    public Line(){
        s = new Point();
        e = new Point();
        direction = s.directionTo(e);
        slope = (e.y - s.y)/(e.x - s.x);
    }
    public Line(Point Start, Point End){
        s = Start;
        e = End;
        direction = s.directionTo(e);
        slope = (e.y - s.y)/(e.x - s.x);
    }
    public double distance(Point other){
        Double t = (slope < 0.001 && slope > -0.001) ? 0.0001:slope;
        Double r = -1/t;

        // direct distances from start and end point
        Double d1 = Math.min(s.distance(other), e.distance(other));

        //get point on line that is closest to other
        Double dx = ((-r * other.x) + (t * s.x) + other.y - s.y)/(t - r);
        Double dy = r * (dx - other.x) + other.y;

        // get distance from point on line to other
        Double d2 = (double)Integer.MAX_VALUE;
        if (dx > Math.min(s.x, e.x) && dx < Math.max(s.x, e.x)){
            Integer Intdx = (int)Math.round(dx);
            Integer Intdy = (int)Math.round(dy);
            d2 = new Point(Intdx, Intdy).distance(other);
        }

        return Math.min(d1, d2);
    }

    public boolean intersect(Line other){
        float a1, a2, b1, b2, c1, c2, d1, d2;

        float x11 = (float)s.x; float y11 = (float)s.y;
        float x12 = (float)e.x; float y12 = (float)e.y;
        float x21 = (float)other.s.x; float y21 = (float)other.s.y;
        float x22 = (float)other.e.x; float y22 = (float)other.e.y;
        

        a1 = y12 - y11;
        b1 = x11 - x12;
        c1 = x12 * y11 + x11 * y12;

        d1 = (a1 * x21) + (b1 * y21) + c1;
        d2 = (a1 * x22) + (b1 * y22) + c1;

        if (d1 > 0 && d2 > 0) return false;
        if (d1 < 0 && d2 < 0) return false;

        a2 = y22 - y21;
        b2 = x21 - x22;
        c2 = x22 * y21 + x21 * y22;

        d1 = (a2 * x11) + (b2 * y11) + c2;
        d1 = (a2 * x12) + (b2 * y12) + c2;

        if (d1 > 0 && d2 > 0) return false;
        if (d1 < 0 && d2 < 0) return false;

        return true;
    }
}