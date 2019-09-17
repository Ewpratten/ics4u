package strings;

import java.util.Scanner;

public class ThirdWord implements Runnable {

    Scanner stdin = new Scanner(System.in);

    public static void main(String[] args) {
        (new ThirdWord()).run();
    }

    @Override
    public void run() {

        // Ask user for a sentence
        System.out.println("Type a sentence");
        System.out.print("> ");

        // Read the user's input
        String user_data = stdin.nextLine();

        // Check for no data
        if (user_data.equals("")) {
            System.out.println("You did not enter any words.. ThirdWord will exit now.");
            return;
        }

        // Display every third word
        System.out.println("Every third word:");

        int i = 0;
        for (String word : user_data.strip().split("\\s+")) {
            // Only display word if i%3==0
            if (i % 3 == 0) {
                System.out.println("-> " + word);
            }

            // Incr I
            i++;
        }

    }

}