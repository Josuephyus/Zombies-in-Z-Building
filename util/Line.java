package util;

public class Line {
    public Point s, e;
    public double direction;
    public Line(){
        s = new Point();
        e = new Point();
        direction = s.directionTo(e);
    }
    public Line(Point Start, Point End){
        s = Start; e = End; direction = s.directionTo(e);
    }
    public double distance(Point other){
        Double tan = Math.tan(direction);
        Double cot = 1/tan;

        // direct distances from start and end point
        Double d1 = s.distance(other);
        Double d2 = e.distance(other);

        //get point on line that is closest to other
        Double dx = ((cot * other.x) + (tan * s.x) -other.y+s.y) / (cot + tan);
        Double dy = tan * (dx - s.x) + s.y;

        // get distance from point on line to other
        Double d3 = (double)Integer.MAX_VALUE;
        if (dx > Math.min(s.x, e.x) && dx < Math.min(s.x, e.x)){
            if (dy > Math.min(s.y, e.y) && dy < Math.min(s.y, e.y)){
                Integer Intdx = (int)Math.round(dx);
                Integer Intdy = (int)Math.round(dy);
                d3 = new Point(Intdx, Intdy).distance(other);
            }
        }

        return Math.min(Math.min(d1, d2), d3);
    }

    public boolean intersect(Line other, Integer distance){
        Double tan = Math.tan(direction);
        Double tanOther = Math.tan(other.direction);

        double x = ((tanOther * other.s.x) + (tan * s.x) - other.s.y + s.y) / (tanOther + tan);
        double y = tan * (x - s.x) + s.y;
        Point i = new Point((int)Math.round(x),(int)Math.round(y));
        if (Math.min(s.distance(i), e.distance(i)) <= distance &&
    Math.min(other.s.distance(i), other.e.distance(i)) <= distance){
                return true;
        }
        return true;
    }
}