package util;

public class Shape {
    public Line[] lines;

    public Shape(){
        lines = null;
    }
    public Shape(Line[] a){
        lines = a;
    }

    public boolean contains(Point a){
        // Create a line from point to infinite right
        Line b = new Line(a, new Point(a.y));

        // Create a counter for how many of the shape's lines the line b passes through
        Integer howManyIntersections = 0;

        // For every line in shape
        for (Line c : lines){
            // if b intersects with the line, add to the counter
            if (c.intersect(b, 1)){
                howManyIntersections++;
            }
        }

        // If line b only passes through one line, it is inside of the shape
        if (howManyIntersections == 1){
            return true;
        }
        // otherwise, it is not
        return false;
    }
    // Entry 2: This shit works really well... Like insanely. We can make rotated squares by rotating the lines around the center.
    // Holy shit im a genius...


    //OKAY, shapes are complicated, but because we are only going to use Squares and Triangles, what we can do is the line method
    // Basically, we get the point. With the point, we make a line. The line goes all right. We check if that line intersects with one
    // of the shapes lines. If it intersects with only one, it is inside, otherwise it is outside. Capish?
}
