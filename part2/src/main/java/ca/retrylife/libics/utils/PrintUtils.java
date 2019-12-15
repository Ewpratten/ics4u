package ca.retrylife.libics.utils;

public class PrintUtils {
    
    public static void printArray(int[][] arr) {
        for (int[] is : arr) {
            for (int is2 : is) {
                System.out.print(is2 + ", ");
            }
            System.out.println();
        }
    }
}