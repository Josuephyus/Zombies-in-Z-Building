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
        s = Start; e = End; direction = s.directionTo(e);
    }
    public double distance(Point other){
        Double t = slope;
        Double r = -1/t;

        // direct distances from start and end point
        Double d1 = Math.min(s.distance(other), e.distance(other));

        //get point on line that is closest to other
        Double dx = ((-r * other.x) + (t * s.x) + other.y - s.y)/(t - r);
        Double dy = r * (dx - other.x) + other.y;

        // get distance from point on line to other
        Double d2 = (double)Integer.MAX_VALUE;
        if (dx > Math.min(s.x, e.x) && dx < Math.max(s.x, e.x)){
            if (dy > Math.min(s.y, e.y) && dy < Math.max(s.y, e.y)){
                Integer Intdx = (int)Math.round(dx);
                Integer Intdy = (int)Math.round(dy);
                d2 = new Point(Intdx, Intdy).distance(other);
            }
        }

        System.out.println(d1 + " " + d2);
        return Math.min(d1, d2);
    }

    public boolean intersect(Line other, Integer distance){
        Double tan = Math.tan(direction);
        Double tanOther = Math.tan(other.direction);

        double x = ((tanOther * other.s.x) + (tan * s.x) - other.s.y + s.y) / (tanOther + tan);
        double y = tan * (x - s.x) + s.y;
        Point i = new Point((int)Math.round(x),(int)Math.round(y));
        if (this.distance(i) <= distance && other.distance(i) <= distance){
            return true;
        }
        return true;
    }
}