package ca.retrylife.ics4u.map;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.Point;
import java.awt.event.MouseEvent;

import ca.retrylife.ics4u.map.World.SquareType;
import ca.retrylife.libics.frameworks.Assignment;
import ca.retrylife.libics.graphics.Window;

public class Map extends Assignment {

    /* Program constants */
    private static class Constants {
        static final Dimension WINSIZE = new Dimension(800, 600);
        static final Dimension GRIDSIZE = new Dimension(32, 32);
    }

    /* Program window */
    Window window;

    /* World */
    World world;

    /* Mouse handling */
    // Point mousePos =
    MouseHandler mouse = new MouseHandler();

    public static void main(String[] args) {
        (new Map()).run();
    }

    public Map() {
        register("Map assignment");

        // Create a window
        window = (new Window(_name)).autoConfig();

        // Create the world
        world = new World(Constants.WINSIZE, Constants.GRIDSIZE);

        // Set mouse listening
        // window.addMouseListener(mouse);
        world.getCanvas().enableInputMethods(true);
        world.getCanvas().addMouseListener(mouse);

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

        // Interaction loop
        while (true) {

            // Set the mouse position
            world.handleSplash(world.pixelToSquare(mouse.getMousePos()));
        }

    }
}