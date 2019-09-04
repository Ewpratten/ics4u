/**
 * ICS4U - Arrays review
 * By: Evan Pratten
 * 
 * Requirements:
 * - Make an array (size 30) of random integers from 1 to 99. 
 * - Go through the array and copy all of the numbers that are greater than 75 into a second array.
 * - Print out the first array and the second array
 */

package arrayreview;

import java.util.ArrayList;
import java.util.Random;

public class App {

    // RNG
    Random rand = new Random();

    public static void main(String[] args) {
        new App();
    }

    private App() {

        // Generate a random array
        ArrayList<Integer> rand_arr = buildArrayWithSize(30);

        // Get an array of rand_arr's values over 75
        ArrayList<Integer> rand_arr_striped = getValuesOver(rand_arr, 75);

        // Print each array
        rand_arr.forEach((x) -> System.out.print("" + x + " "));
        System.out.println(""); // print a newline
        rand_arr_striped.forEach((x) -> System.out.print("" + x + " "));

    }

    /**
     * Build an Integer array of specific size
     * 
     * @param size Length of the requested array
     * @return Generated array
     */
    private ArrayList<Integer> buildArrayWithSize(int size) {

        // Build empty array of size
        ArrayList<Integer> output = new ArrayList<Integer>();

        // foreach to init all values
        for (int i = 0; i < size; i++) {
            output.add(rand.nextInt(98) + 1);
        }

        return output;
    }

    /**
     * Get an arraylist of ints over a max point
     * 
     * @param input Input array
     * @param max   Max cutoff
     * @return Stripped array
     */
    private ArrayList<Integer> getValuesOver(ArrayList<Integer> input, int max) {
        // Memory efficency not required.. Use an ArrayList
        ArrayList<Integer> output = new ArrayList<Integer>();

        // Iter each value of the input
        for (Integer x : input) {
            if (x > max) {
                output.add(x);
            }
        }

        // Re-cast to a standard array
        return output;
    }
}
