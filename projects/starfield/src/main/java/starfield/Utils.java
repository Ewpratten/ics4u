/**
 * ISC4U - Starfield
 * By: Evan Pratten
 */
package starfield;

import java.awt.Point;
import java.util.Random;
import java.awt.geom.Point2D;

/**
 * A collection of standalone utilities
 */
public class Utils {

    // Random number generator
    static Random rand = new Random();

    private static double curve(int x, int max, int curve) {
        return (curve * Math.sin((x / (double) max) * Math.PI));
    }

    /**
     * Pick a random point around the perimeter of a box (starting at 0,0)
     * 
     * @param width  Box width
     * @param height Box height
     * @return Generated point
     */
    public static Point randPerimeterPoint(int width, int height, int curviness) {

        // Choose a random side of the box
        int side = rand.nextInt(4);
        Point output = null;

        // Get random point for side
        switch (side) {

        // Left wall
        case 0:
            int selection = rand.nextInt(height);

            output = new Point((int) (0 - curve(selection, height, curviness)), selection);
            break;

        // Bottom wall
        case 1:
            selection = rand.nextInt(width);
            output = new Point(selection, (int) (height + curve(selection, width, curviness)));
            break;

        // Left wall
        case 2:
            selection = rand.nextInt(height);
            output = new Point((int) (width + curve(selection, height, curviness)), selection);
            break;

        // Top wall
        case 3:
            selection = rand.nextInt(width);
            output = new Point(selection, (int) (0 - curve(selection, width, curviness)));
            break;
        }

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
        return Point2D.distance(a.x, a.y, b.x, b.y) < 5;
    }
}