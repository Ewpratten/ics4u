/**
 * ICS4U - BorderOfStars
 * By: Evan Pratten
 * 
 */
package borderofstars;

import java.util.Scanner;

public class App {

    Scanner stdin = new Scanner(System.in);

    public static void main(String[] args) {
        new App();
    }

    App() {
        // Read a sentence
        System.out.println("Enter a sentence:");
        System.out.print("> ");
        String sentence = stdin.nextLine();

        // Wrap the sentence
        String[] split = wrapBy(sentence, 20);

        // Print the box
        System.out.println("************************");
        for (String x : split) {
            System.out.printf("* %-20s *%n", x);
        }
        System.out.println("************************");

    }

    String[] wrapBy(String x, int len) {
        StringBuilder sb = new StringBuilder(x);

        int i = 0;
        while (i + len < sb.length() && (i = sb.lastIndexOf(" ", i + len)) != -1) {
            sb.replace(i, i + 1, "\n");
        }

        return sb.toString().split("\\r?\\n");
    }

}
