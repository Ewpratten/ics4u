/**
 * ICS3U - RandomWord
 * 
 * Task:
 * - Make random 6 letter words with exactly one or two vowels in them. The vowels should be located at random locations. (eg. "yupsqt", "ihlkpa")
 * 
 * Execution:
 *  - Randomly select if the word should have 1 or 2 vowels
 *  - Fill a len(6) char array with chars following there rules:
 *     - Have a random chance of setting the current index to a vowel (this gets closer to 100% as the remaining chars get closer to 0)
 *     - If the current char is not decided to be a vowel (or the max number of vowels was reached) choose a random non-vowel
 */
package strings;

import java.util.Random;

public class RandomWord implements Runnable {

    Random rand = new Random();

    String vowels = "aeiouAEIOU";

    public static void main(String[] args) {
        System.out.println("---");
        for (int i = 0; i < 10; i++) {
            (new RandomWord()).run();
        }
        
    }

    /**
     * Generate a random, valid, english char
     * @return random char
     */
    private char rndChar() {
        // Get a random ascii index
        int rnd = (int) rand.nextInt(52);
        
        // choose correct ascii base
        char base = (rnd < 26) ? 'A' : 'a';

        // Convert to actual char and return
        return (char) (base + rnd % 26);
    }

    /**
     * Generate a random word
     */
    @Override
    public void run() {

        // Determine the max number of vowels
        int remainimg_vowels = rand.nextInt(2) + 1;

        // Define a word
        char[] word = new char[6];

        for (int i = 0; i < word.length; i++) {
            // System.out.println("" + remainimg_vowels + " " + word.toString());
            // Determine if the current position should contain a vowel, based on:
            // rand(word.len) - 1 == 0
            if (rand.nextInt(word.length - i ) == 0 && remainimg_vowels > 0) {

                // Select a random vowel
                word[i] = vowels.charAt(rand.nextInt(vowels.length() - 1));
                remainimg_vowels--;

            } else {
                // Fill with a non-vowel
                char desired_char = rndChar();

                // this MUST not be a vowel. Check this
                if (vowels.indexOf(desired_char) != -1) {
                    // If this is a vowel, decr the index, so that the loop will try gain
                    i--;
                } else {
                    // Set the current word index to the char
                    word[i] = desired_char;
                }

            }
        }

        // Print the result
        System.out.println("Generated word: "+ String.valueOf(word));

    }

}