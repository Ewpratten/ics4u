/**
 * ICS4U - Array Quiz
 * By: Evan Pratten
 * 
 * 1. Create 5x6 array
 * 2. Fill it with random numbers
 * 3. Print the array
 * 4. Find the location of the first 13
 * 5. Print the location
 * 6. Print the following number
 */
package arrayquiz;

import java.util.Random;
import java.awt.Point;

public class App {

    // RNG
    Random rand = new Random();

    public static void main(String[] args) {
        new App(6, 5);
    }

    private App(int width, int height) {
        // Get a random 2D array
        int[][] arr = genRand2DArr(width, height);

        // Print the array
        dump2D(arr);

        // Find the first 13
        Point location = find(arr, 13);

        // Handle the possibility of no point found
        if (location == null) {

            // Print and skip the final step
            System.out.println("13 was not found");
            return;
        }

        // Print the location
        System.out.printf("%1d, %1d%n", location.x, location.y);

        // Ensure location is in bounds
        if (location.y + 1 >= height) {

            // Print and skip the next array access
            System.out.println("Next number is out of bounds");
            return;
        }

        // Print the next number
        System.out.println(arr[location.x][location.y + 1]);

    }

    /**
     * Generate a 2D array with random contents of a specific size
     * 
     * @param width  Array width
     * @param height Array height
     * @return Generated array
     */
    private int[][] genRand2DArr(int width, int height) {

        // Init an array to hold other arrays and be returned
        int[][] output = new int[width][height];

        // Iterate each element of dimension 1
        for (int[] row : output) {
            for (int i = 0; i < row.length; i++) {

                // Fill each element of row with a random number
                row[i] = rand.nextInt(19) + 1;

            }
        }

        return output;

    }

    /**
     * Print a 2D array to the console
     * 
     * @param arr 2D array
     */
    private void dump2D(int[][] arr) {
        for (int[] row : arr) {

            // Print each row
            for (int col : row) {
                System.out.printf("%2d ", col);
            }

            // Add a newline
            System.out.println();
        }
    }

    /**
     * Find the location of an item in a 2D array
     * 
     * @param arr  2D array of items
     * @param item Item to match
     * @return Determined location (or null)
     */
    private Point find(int[][] arr, int item) {

        // Iterate each row of arr
        for (int i = 0; i < arr.length; i++) {

            // Iterate each col of row
            for (int j = 0; j < arr[i].length; j++) {

                // Compare with item
                if (arr[i][j] == item) {

                    // Return determined point
                    return new Point(i, j);
                }

            }

        }

        // If no match is found, return null
        return null;
    }
}
