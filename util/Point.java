package util;

public class Point {
    public double x, y;
    public Point(){
        x = 0;
        y = 0;
    }
    public Point(int x, int y){
        this.x = (double) x;
        this.y = (double) y;
    }
    public Point (int y){
        this.y = (double) y;
        x = 99999d;
    }
    public Point(double y){
        this.y = y;
        x = 99999d;
    }
    public Point(double x, double y){
        this.x = x; this.y = y;
    }
    public double distance(Point other){
        double xD = this.x - other.x;
        double yD = this.y - other.y;
        return Math.sqrt((xD * xD) + (yD * yD));
    }
    public float directionTo(Point other){
        double theta;
        double xD = other.x - this.x;
        double yD = other.y - this.y;

        if (xD != 0)theta = Math.atan(yD / xD) + ((xD < 0) ? Math.PI:0);
        else theta = Math.PI - (((yD < 0) ? -Math.PI:Math.PI ) / 2);


        return (float)theta;
    }

    public String toString(){
        return "x: " + x + ", y: " + y;
    }
}