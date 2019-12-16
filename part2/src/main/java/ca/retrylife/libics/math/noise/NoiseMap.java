package ca.retrylife.libics.math.noise;

import java.util.Random;
import java.awt.Point;

/**
 * A 2D perlin-ish noise map
 */
public class NoiseMap {

    // RNG
    Random rand;

    // Map
    int[][] map;
    int width, height;

    /**
     * Create a NoiseMap of a specific size
     * 
     * @param width  Map width
     * @param height Map height
     */
    public NoiseMap(int width, int height) {

        // Set locals
        this.width = width;
        this.height = height;

        // Create map
        clearMap();

        // Create RNG
        rand = new Random();

    }

    /**
     * Compute a map with n starting points
     * 
     * @param n Number of starting points
     */
    public void compute(int n) {

        // Run first pass
        firstPass(n);

        // Run second pass
        secondPass();

    }

    /**
     * The first pass will just plot n random points
     * 
     * @param n Number of points
     */
    public void firstPass(int n) {
        int used_points = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                // Determine chance of a plot
                int chance = n - used_points;

                // If the RNG decides, plot the point
                if (rand.nextInt(chance) == 1) {
                    map[i][j] = 1;
                    used_points++;
                }

            }
        }

    }

    /**
     * The second pass will fill in all other values with their distance from a spot
     * selected in the first pass. Effectively creating a noise map
     */
    public void secondPass() {

        // Set every empty slot with it's distance to a "1"
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int distance = getNearestDistanceTo(new Point(i, j), 1);

                map[i][j] = distance + 1;
            }
        }
    }

    /**
     * Find the distance to the nearest occurence of a value in the map
     * 
     * @param loc Current location
     * @param val Desired value
     * @return Distance
     */
    private int getNearestDistanceTo(Point loc, int val) {

        // Set a big number as the shortest
        double distance = map.length * map[0].length;

        // Find all vals
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                // Check if current val is wanted
                if (map[i][j] == val) {

                    // Find shortest distance from current to val
                    double dist = loc.distance(new Point(i, j));

                    // If it is the smallest, set it
                    if (dist < distance) {
                        distance = dist;
                    }
                }
            }
        }

        return (int) distance;

    }

    /**
     * Get the map if ints
     * 
     * @return Map
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * Clear the internal map
     */
    public void clearMap() {
        map = new int[height][width];
    }

}