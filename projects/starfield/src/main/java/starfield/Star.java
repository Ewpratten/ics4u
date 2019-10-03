/**
 * ISC4U - Starfield
 * By: Evan Pratten
 */
package starfield;

import java.awt.Point;

import starfield.App.Configuration;

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Drawable star
 * 
 * This works by finding a "target" along the window perimeter, and moving the
 * star's location towards it's target over time. Over time, the star will speed
 * up. The class also keeps track of the original values provided, so it can
 * reset itself instead of requiring a septate loop to recreate dead star
 * objects.
 */
public class Star {

    // Star location & target location
    private Point origin;
    private Point loc;
    private Point target;

    // Base star size
    private double size = 1;

    // Star speed & original speed
    private int speed, orig_speed;

    // Canvas size
    private int w, h;

    /**
     * Create a drawable star
     * 
     * @param speed Base movement speed (smaller numbers are faster)
     * @param loc   Starting location for star
     * @param h     Canvas height (used in target generation)
     * @param w     Canvas width (used in target generation)
     */
    public Star(int speed, Point loc, int h, int w) {
        this.loc = new Point(loc);
        this.origin = new Point(loc);

        this.target = Utils.randPerimeterPoint(w, h, Configuration.curviness);

        this.w = w;
        this.h = h;

        this.speed = speed;
        this.orig_speed = speed;
    }

    /**
     * Called by graphics loop to handle movement and re-creation
     */
    void update() {

        // Reset the star when it reaches it's target
        if (Utils.near(loc, target)) {
            reset();
            return;
        }

        // Determine speed (gradually gets faster)
        // Make sure a div by 0 error does not occur
        speed = Math.max(speed - 2, 1);

        // Slowly increase star size
        size += 0.05;

        // Calculate movement deltas
        double deltaX = (target.x - loc.x) / speed;
        double deltaY = (target.y - loc.y) / speed;

        // Modify star position based on deltas
        loc.x += deltaX;
        loc.y += deltaY;
    }

    /**
     * Reset the star to it's starting position / values
     */
    private void reset() {
        // Reset starting location
        loc = new Point(origin);

        // Choose a new target
        target = Utils.randPerimeterPoint(w, h, Configuration.curviness);

        // Reset speed
        speed = orig_speed;

        // Reset size
        size = 2;

    }

    /**
     * Draw the star on a canvas
     * 
     * @param g Graphics2D canvas
     */
    void render(Graphics2D g) {
        // Set draw colour
        g.setColor(Color.WHITE);

        // Fill an oval
        g.fillOval((int) loc.x, (int) loc.y, (int) size, (int) size);

        // g.drawLine((int) loc.x, (int) loc.y, (int) target.x, (int) target.y);
    }
}