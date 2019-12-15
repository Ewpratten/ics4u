package ca.retrylife.ics4u.map;

import java.awt.Dimension;
import java.awt.Point;

import java.awt.event.MouseEvent;

import ca.retrylife.libics.frameworks.Assignment;
import ca.retrylife.libics.graphics.Window;

/**
 * Map continents assignment
 */
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
    MouseClickHandler mouse = new MouseClickHandler(this::handleInput);

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

    }

    /**
     * Method for handling mouse input
     * 
     * @param pos        Mouse position
     * @param buttonType Button click type
     */
    private void handleInput(Point pos, int buttonType) {

        // Handle each type of button press
        switch (buttonType) {

        // Left click
        case MouseEvent.BUTTON1:
            world.handleSplash(world.pixelToSquare(pos));
            break;

        case MouseEvent.BUTTON3:
            world.handleCreativeInput(world.pixelToSquare(pos));
            break;
        }
    }
}