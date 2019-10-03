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

    /**
     * Pick a random point around the perimeter of a box (starting at 0,0)
     * 
     * @param width  Box width
     * @param height Box height
     * @return Generated point
     */
    public static Point randPerimeterPoint(int width, int height) {

        // Choose a random side of the box
        int side = rand.nextInt(4);
        Point output = null;

        // Get random point for side
        switch (side) {

        // Left wall
        case 0:
            output = new Point(0, rand.nextInt(height));
            break;

        // Bottom wall
        case 1:
            output = new Point(rand.nextInt(width), height);
            break;

        // Left wall
        case 2:
            output = new Point(width, rand.nextInt(height));
            break;

        // Top wall
        case 3:
            output = new Point(rand.nextInt(width), 0);
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