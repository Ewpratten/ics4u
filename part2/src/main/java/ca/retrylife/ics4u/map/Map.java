package ca.retrylife.ics4u.map;

import java.awt.Dimension;

import ca.retrylife.libics.frameworks.Assignment;
import ca.retrylife.libics.graphics.Window;

public class Map extends Assignment {

    private static class Constants {
        static final Dimension WINSIZE = new Dimension(800, 600);
        static final Dimension GRIDSIZE = new Dimension(32, 32);
    }

    Window window;

    World world;

    public static void main(String[] args) {
        (new Map()).run();
    }

    public Map() {
        register("Map assignment");

        // Create a window
        window = (new Window(_name)).autoConfig();

        // Create the world
        world = new World(Constants.WINSIZE, Constants.GRIDSIZE);
        window.add(world.getCanvas());
        window.pack();

    }

    @Override
    public void run() {
        // Show the window
        window.setVisible(true);

        // Disallow window resize
        window.setResizable(false);

        // Run the window
        window.start();

    }
}