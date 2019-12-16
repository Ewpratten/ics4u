package ca.retrylife.ics4u.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import ca.retrylife.libics.steganography.SteganoImgProcess;

import java.awt.Graphics2D;

/**
 * A tool for saving and loading worlds with the help of steganography
 */
public class WorldLoader {

    // World
    private World world;

    // Steganographic processor
    SteganoImgProcess processor;

    public WorldLoader(World world) {

        // Set locals
        this.world = world;

        // Create a processor
        processor = new SteganoImgProcess();

    }

    public void save() {

        // Create an image buffer
        BufferedImage buffer = new BufferedImage(world.getCanvas().getWidth(), world.getCanvas().getHeight(),
                BufferedImage.TYPE_INT_RGB);

        // Grab the canvas graphics controller
        Graphics2D g2 = buffer.createGraphics();

        // Paint the canvas to the buffer
        world.getCanvas().paint(g2);

        // Convert world map into drawing instructions
        StringBuilder instructions = new StringBuilder();

        // Iterate through world map and build it to a string
        int[][] map = world.getAsIntArray();
        DecimalFormat formatter = new DecimalFormat("00");

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                // Read current value
                int val = map[i][j];

                // Write as 2 char int to allow for expansion of tileset up to 99 tiles
                instructions.append(formatter.format(val));

            }

            // Add "N" to specify newline
            // instructions.append("N");
        }

        // Encode and write to file
        processor.encode(buffer, buffer, world.getCanvas().getWidth(), world.getCanvas().getHeight(),
                instructions.toString(), "mapworld_save.png");

    }

    public void load() {

        // Load image
        BufferedImage img;
        try {
            img = ImageIO.read(new File("mapworld_save.png"));
        } catch (IOException e) {

            // If failure, dont bother trying to load
            System.out.println("Failed to load save file");
            e.printStackTrace();
            return;
        }

        // Load image to string
        String data = processor.decode(img, world.getCanvas().getWidth(), world.getCanvas().getHeight());

        // Read the current world, just to get the size of the array
        int[][] map = world.getAsIntArray();

        // Rebuild string back to an int array
        int charCount = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {

                // Read the current chars
                char b10 = data.charAt(charCount);
                char b1 = data.charAt(charCount + 1);

                // Construct chars back to an int
                int val = (Character.getNumericValue(b10) * 10) + Character.getNumericValue(b1);

                // Write the value to the map
                map[i][j] = val;

                // Increment the number of chars read
                charCount += 2;

            }
        }

        // Set the world map back to the loaded map
        world.setFromIntArray(map);

    }
}