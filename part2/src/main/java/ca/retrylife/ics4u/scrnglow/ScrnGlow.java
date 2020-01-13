package ca.retrylife.ics4u.scrnglow;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;

import ca.retrylife.ics4u.map.MouseClickHandler;
import ca.retrylife.libics.frameworks.Assignment;
import ca.retrylife.libics.graphics.Canvas2D;
import ca.retrylife.libics.graphics.Window;
import ca.retrylife.libics.math.MathUtils;

public class ScrnGlow extends Assignment {

    /* Rendering */
    Window window;
    Canvas2D canvas;
    double scrnDiag;

    /* Glow sources */
    ArrayList<Point> sources = new ArrayList<>();

    /* Interaction */
    MouseClickHandler mouse = new MouseClickHandler((p,v)->{
        sources.add(p);
        System.out.println(p);
    });

    public static void main(String[] args) {
        new ScrnGlow().run();
    }

    public ScrnGlow() {
        register("ScrnGlow");

        // Build window
        window = new Window(_name).autoConfig();
        canvas = new Canvas2D(this::draw);
        window.add(canvas);
        window.addMouseListener(mouse);

        // Calculate the screen width
        scrnDiag = new Point(0, 0).distance(canvas.getWidth(), canvas.getHeight());

        // Add a source
        sources.add(new Point(0, 0));
        sources.add(new Point(0, 200));
        sources.add(new Point(400, 500));
        // sources.add(new Point(400, 200));

    }

    @Override
    public void run() {

        // Show the window
        window.setVisible(true);
        window.start();

        // while (true) {

        //     for (Point source : sources) {
                 
        //     }
            
        // }

    }

    private void draw(Graphics2D g) {

        // Do a pixel-by-pixel render of the entire screen
        for (int i = 0; i < canvas.getHeight(); i++) {
            for (int j = 0; j < canvas.getWidth(); j++) {

                // Create a base distance
                int distance = 0;

                // Add each distance from each source
                for (Point source : sources) {

                    distance += source.distance(j, i);
                }

                // Avg the distances
                distance = (distance / sources.size());

                // Clamp the distance
                distance = (int) MathUtils.clamp(distance, 0, 255);

                // Set the pixel colour
                Color c = Color.getHSBColor((float) distance / 10, .8f, 1.f);
                g.setColor(c);

                // Render the pixel
                g.drawLine(j, i, j, i);

            }

        }

    }

}