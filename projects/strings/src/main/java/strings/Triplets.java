package strings;

import java.util.Scanner;

public class Triplets implements Runnable {

    String data;

    public static void main(String[] args) {
        System.out.println("---");
        (new Triplets("Hello felloww humkdsafsssans, I am a cow. Mooo.")).run();
    }

    public Triplets(String data) {
        this.data = data;
    }

    private void handleWord(String word) {
        // Temp vals for parsing
        char current_char = '`';
        int char_count = 1;

        for (char x : word.toCharArray()) {
            // Incr counter if x is the same as the last char
            if (x == current_char) {
                char_count++;

            } else {
                // reset counter if different
                char_count = 1;
                current_char = x;
            }

            // Print word and return if char_count >= 3
            if (char_count >= 3) {
                System.out.println("-> " + word);
                return;
            }
        }

    }

    @Override
    public void run() {

        // Iter each word in input data
        System.out.println("Words with the same letter repeated 3 or more times in a row:");
        for (String word : data.strip().split("\\s+")) {

            // Handle the word
            handleWord(word);
        }

    }

}