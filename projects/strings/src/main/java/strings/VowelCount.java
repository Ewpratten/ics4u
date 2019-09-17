package strings;

import java.util.Arrays;

public class VowelCount implements Runnable {

    private char[] text_data;

    // Map of valid vowels. Defining both cases here is more efficient than
    // converting chars in the loop
    String vowels = "aeiouAEIOU";

    public static void main(String[] args) {
        System.out.println("---");
        (new VowelCount("Hello fellow humans, I am a cow. Moo.")).run();
    }

    public VowelCount(String data) {
        text_data = data.toCharArray();
    }

    @Override
    public void run() {

        // Iter text_data and count each vowel
        int vowel_count = 0;

        for (char x : text_data) {

            // Check if lowercase x exists in the vowel list
            if (vowels.indexOf(x) != -1) {

                // If it is, incr vowel_count
                vowel_count++;
            }
        }

        // Print the result to console
        System.out.println("VowelCount has found " + vowel_count + " vowels in the provided string");

    }

}