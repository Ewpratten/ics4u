package starfield;

import java.awt.Point;
import java.util.Random;
import java.awt.geom.Point2D;

public class Utils {

    // Random number gen
    static Random rand = new Random();

    /**
     * Pick a random point around the perimeter of a box (starting at 0,0)
     * 
     * @param width Box width
     * @param height Box height
     * @return Generated point
     */
    public static Point randPerimeterPoint(int width, int height) {

        // Determine the perimeter of the box
        int perimeter = 2 * width + 2 * height;

        // Pick a random point along the perimeter
        int rand_sel = rand.nextInt(perimeter);

        // Check if the point needs to be flipped
        boolean ovf = rand_sel > (perimeter / 2);

        // Re-fold the perimeter to a box
        Point output = new Point();

        // Check if point is along the side
        if (rand_sel % (perimeter / 2) <= height) {
            output.x = 0;
            output.y = rand_sel;
        } else {
            output.x = rand_sel - height;
            output.y = height;
        }

        // Handle flipping
        // if (ovf) {
        //     output.x = width - output.x;
        //     output.y = height - output.y;
        // }

        return output;
    }

    /**
     * Check if two points are near eachother
     * 
     * @param a First point 
     * @param b Second point
     * @return Are they near?
     */
    public static boolean near(Point a, Point b) {
        return Point2D.distance(a.x, a.y, b.x, b.y) < 10;
        // return Math.abs(a.x - b.x) < 5 || Math.abs(a.y - b.y) < 5;
    }
}