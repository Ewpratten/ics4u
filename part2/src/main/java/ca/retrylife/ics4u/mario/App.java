package ca.retrylife.ics4u.mario;

import ca.retrylife.libics.frameworks.Assignment;

import java.awt.Point;
import java.io.IOException;
import java.awt.Dimension;

/**
 * A little widget to make mario run around on your screen
 */
public class App extends Assignment {

    // Dimension screenSize;

    Player player;

    public static void main(String[] args) {
        (new App()).run();
    }

    public App() {
        register("Mario");

        // Create a player
        try{
            player = new Player(new Point(1, 1), new Dimension(16,64));
        } catch (IOException e) {
            System.out.printf("Could not create player: %n%s%n", e);
            System.exit(1);
        }


    }

    @Override
    public void run() {

        // Start the player
        player.start();

    }

}
