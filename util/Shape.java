package util;

public class Shape {
    public Line[] lines;

    public Shape(){
        lines = null;
    }
    public Shape(Line[] a){
        lines = a;
    }

    public boolean inside(Point a){
        Line b = new Line(a, new Point(a.y));
        Integer howManyIntersections = 0;
        for (Line c : lines){
            if (c.intersect(b, 1)){
                howManyIntersections++;
            }
        }
        if (howManyIntersections == 1){
            return true;
        }
        return false;
    }
    // Entry 2: This shit works really well... Like insanely. We can make rotated squares by rotating the lines around the center.
    // Holy shit im a genius...


    //OKAY, shapes are complicated, but because we are only going to use Squares and Triangles, what we can do is the line method
    // Basically, we get the point. With the point, we make a line. The line goes all right. We check if that line intersects with one
    // of the shapes lines. If it intersects with only one, it is inside, otherwise it is outside. Capish?
}
