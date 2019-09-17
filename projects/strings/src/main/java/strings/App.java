/**
 * ICS4U - Strings
 * By: Evan Pratten
 * 
 *  - Write a program that reads in a sentence (use Scanner) and then prints out every third word
 *  - Count how many vowels there are in a word (or any string)
 *  - Read in a sentence and print out only words that have the same letter repeated 3 or more times in a row (e.g. happpy) 
 *  - Make random 6 letter words with exactly one or two vowels in them. The vowels should be located at random locations. (eg. "yupsqt", "ihlkpa")
 */

package strings;

/**
 * This class just launches the sub-programs
 */
public class App {

    public static void main(String[] args) {
        // Run ThirdWord
        System.out.println("--- Starting ThirdWord ---\n");
        (new ThirdWord()).run();


    }
}
