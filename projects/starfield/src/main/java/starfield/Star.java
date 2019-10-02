package starfield;

import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Color;

public class Star {

    Point origin;
    Point loc;
    Point target;
    int angle;
    int size = 10;

    public Star(int speed, Point loc, Point target) {
        this.loc = loc;
        this.origin = loc;

        // System.out.println(angle);

        // // Calculate target position
        // int adj = (int) (Math.cos(angle) * aim);
        // int opp = (int) (Math.sin(angle) * aim);

        // System.out.println(""+adj+", "+ opp+", "+Math.cos(angle) * aim);

        // this.target = new Point(adj, opp);
        // this.angle = angle;
        this.target = target;
    }

    void update() {

        // Check if loc is near target
        if (Utils.near(loc, target)) {
            // Reset the star
            reset();
            System.out.println("Respawn");
            return;
        }

        // Determine distance
        double dist = Math.sqrt(Math.pow(target.x - loc.x, 2) + Math.pow(target.y - loc.y, 2));

        // Determine deltas
        double deltaX = target.x - loc.x;
        double deltaY = target.y - loc.y;
        double direction = Math.atan(deltaY / deltaX);

        // Determine speed
        // Currently hard-coded
        int speed = 5;

        // Determine modifier
        int mod = 1;
        if (target.x < loc.x || target.y < loc.y) {
            mod = -1;
        }

        // Move location point
        loc.x += speed * Math.cos(direction) * mod;
        loc.y += speed * Math.sin(direction) * mod;
    }

    private void reset() {
        loc.x = origin.x;
        loc.y = origin.y;

        System.out.println("");

        System.out.println(loc);
    }

    Point getPos() {
        return loc;
    }

    void render(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.fillOval((int) loc.x, (int) loc.y, (int) size, (int) size);

        g.setColor(Color.BLUE);
        g.drawLine((int) loc.x, (int) loc.y, (int) target.x, (int) target.y);
    }
}