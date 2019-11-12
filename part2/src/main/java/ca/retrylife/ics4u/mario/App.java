package ca.retrylife.ics4u.mario;

import ca.retrylife.libics.frameworks.Assignment;

import java.awt.Point;
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
        player = new Player(new Point(1, 1), new Dimension(64, 32));

        // Read the computer screen size
        // screenSize = ScreenTools.getScreenSize();

    }

    @Override
    public void run() {

        // Start the player
        player.start();

    }

}