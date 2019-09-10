/**
 * ICS4U - Sorting (Quicksort implementation)
 * Evan Pratten
 * 
 * How a quicksort works:
 *  - Pick an array element (called the pivot)
 *  - Order all values so that values larger than the pivot are to the right, and smaller are to the left (partitioning)
 *  - Recursively apply previous steps
 */
package sortingprez;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        new App();
    }

    Random rand = new Random();


    private App() {
        // Create and print an array to sort
        int arrlen = 100000000;
        int[] test = new int[arrlen];

        for (int i = 0; i < arrlen; i++) {
            test[i] = rand.nextInt(1000000);
        }
        // dump(test);

        // Sort test
        quicksort(test);

        // dump(test);
    }

    /**
     * A wrapper around quicksort(int[], int, int) that handles the selection of
     * high and low indexes
     * 
     * @param arr Int array to be in-place sorted
     */
    public void quicksort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    /**
     * Method for determining the partition index and swapping surrounding values
     * based on pivot, based on the Lomuto partition scheme.
     * (https://en.wikipedia.org/wiki/Quicksort#Lomuto_partition_scheme)
     * 
     * @param arr        Int array to select partition from
     * @param low_index  Low index of section range
     * @param high_index High index of section range
     * @return Determined partition index
     */
    private int lomutoPartition(int[] arr, int low_index, int high_index) {

        // Find value of element at pivot index (the highest of the range, as defined by
        // the Lomuto partition scheme)
        int pivot_val = arr[high_index];

        // Set base iterator index to the lowest of the array
        int i = low_index;

        // Iterate through all elements in the defined range for the current segment
        for (int j = low_index; j < high_index; j++) {
            // Check if the current element of the array needs to be swapped with the value
            // at i.
            if (arr[j] < pivot_val) {
                // Use a bitwise XOR to swap arr[i] and arr[j] in-place
                arr[i] = arr[i] ^ arr[j]; // Set arr[i] to the xor of arr[i] and arr[j]
                arr[j] = arr[i] ^ arr[j]; // Extract the original arr[i] value
                arr[i] = arr[i] ^ arr[j]; // Extract the original arr[j] value

                // Increment the swap location
                i++;
            }

            // Use a bitwise XOR to swap arr[i] and arr[high_index] in-pace
            arr[i] = arr[i] ^ arr[high_index]; // Set arr[i] to the xor of arr[i] and arr[high_index]
            arr[high_index] = arr[i] ^ arr[high_index]; // Extract the original arr[i] value
            arr[i] = arr[i] ^ arr[high_index]; // Extract the original arr[high_index] value

        }

        return i;
    }

    /**
     * Method for determining the partition index and swapping surrounding values
     * based on pivot, based on the Hoare partition scheme.
     * (https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme)
     * 
     * @param arr        Int array to select partition from
     * @param low_index  Low index of section range
     * @param high_index High index of section range
     * @return Determined partition index
     */
    private int hoarePartition(int[] arr, int low_index, int high_index) {

        // Find value of element at pivot index (the highest of the range, as defined by
        // the Hoare partition scheme)
        int pivot_val = arr[low_index + (high_index - low_index) / 2];

        // Loop forever
        for (;;) {

            // Scan the low side of the section until a value is found that is >= the
            // pivot_val
            while (arr[low_index] < pivot_val) {
                low_index++;
            }

            // Scan the high side of the section until a value is found that is <= the
            // pivot_val
            while (arr[high_index] > pivot_val) {
                high_index--;
            }

            // Check if swap is not needed
            if (low_index >= high_index) {
                return high_index;
            }

            // Use a bitwise XOR to swap arr[low_index] and arr[high_index] in-pace
            arr[low_index] = arr[low_index] ^ arr[high_index]; // Set the xor of arr[low_index] and arr[high_index]
            arr[high_index] = arr[low_index] ^ arr[high_index]; // Extract the original arr[low_index] value
            arr[low_index] = arr[low_index] ^ arr[high_index]; // Extract the original arr[high_index] value

            // Move the locators
            low_index++;
            high_index--;

        }
    }

    /**
     * Quicksort an array recursively
     * 
     * @param arr        Int array to be in-place sorted
     * @param low_index  Lowest index of the segment to sort. <br>
     *                   This is usually 0 if called manually
     * @param high_index Highest index of segment to sort. <br>
     *                   This is usually (arr.length - 1) if called manually
     */
    private void quicksort(int[] arr, int low_index, int high_index) {
        // Determine if further sorting is needed
        if (low_index < high_index) {

            // Determine the partition index
            int part_index = hoarePartition(arr, low_index, high_index);

            // Recursively fork low and high
            quicksort(arr, low_index, part_index);
            quicksort(arr, part_index + 1, high_index);
        }
    }

    /**
     * PHP-style variable dump for an int array
     * 
     * @param arr int array to dump
     */
    private void dump(int[] arr) {
        for (int x : arr) {
            System.out.print("" + x + " ");
        }
        System.out.println();
    }
}
