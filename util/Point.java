package util;

public class Point {
    public Integer x, y;
    public Point(){
        x = Integer.MAX_VALUE;
        y = Integer.MAX_VALUE;
    }
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Point (int y){
        this.y = y;
        x = Integer.MAX_VALUE;
    }
    public double distance(Point other){
        Integer xD = this.x - other.x;
        Integer yD = this.y - other.y;
        return Math.sqrt((xD * xD) + (yD * yD));
    }
    public double directionTo(Point other){
        double theta;
        Integer xD = other.x - this.x;
        Integer yD = other.x - this.x;

        if (xD != 0)theta = Math.atan(yD / xD) * ((xD > 0) ? 1:-1);
        else theta = Math.PI - (((yD > 0) ? Math.PI:Math.PI ) / 2);
        return theta;
    }
}