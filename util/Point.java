package util;

public class Point {
    public Double x, y;
    public Point(){
        x = Double.MAX_VALUE;
        y = Double.MAX_VALUE;
    }
    public Point(int x, int y){
        this.x = (double) x;
        this.y = (double) y;
    }
    public Point (int y){
        this.y = (double) y;
        x = Double.MAX_VALUE;
    }
    public Point(Double y){
        this.y = y;
        x = Double.MAX_VALUE;
    }
    public Point(Double x, Double y){
        this.x = x; this.y = y;
    }
    public double distance(Point other){
        Double xD = this.x - other.x;
        Double yD = this.y - other.y;
        return Math.sqrt((xD * xD) + (yD * yD));
    }
    public double directionTo(Point other){
        double theta;
        Double xD = other.x - this.x;
        Double yD = other.y - this.y;

        if (xD != 0)theta = Math.atan(yD / xD) + ((xD < 0) ? Math.PI:0);
        else theta = Math.PI - (((yD < 0) ? -Math.PI:Math.PI ) / 2);


        return theta;
    }
}