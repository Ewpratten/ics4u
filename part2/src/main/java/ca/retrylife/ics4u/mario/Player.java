package ca.retrylife.ics4u.mario;

import ca.retrylife.libics.graphics.Window;
import ca.retrylife.libics.math.MathUtils;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Player {

    /* Config */
    private final double max_speed = 10;
    private double speed = 100;

    /* Locals */
    Window window;
    Point position;
    Dimension size;
    private double dx, dy = 0.0;

    public Player(Point pos, Dimension size) {
        // Set player pos
        position = pos;
        this.size = size;

        // Build a window to contain player sprite
        window = new Window("Mario", pos, size);
        window.registerUpdateHandler(this::update);

        // Set onClose callback
        window.onClose(() -> {
            System.exit(0);
        });

        // Set Keystroke listeners
        window.registerKBDCallback(65, this::handleLeftMOV); // A
        window.registerKBDCallback(68, this::handleRightMOV); // D
    }

    public void start() {
        window.start();
    }

    private void update(Window win) {

    }

    /**
     * To be called independently of the main update loop.. Once per keystroke
     */
    private void updatePos() {
        // Move window from velocities
        position.x += dx;
        position.y += dy;
        window.setLocation(position);
        dx = 0;
    }

    private void handleLeftMOV() {

        // Only allow movement if not about to collide
        if (predictCollision(new Point((int) (position.x - max_speed), position.y))) {
            dx = 0;
            System.out.println("WALL!");
            return;
        }

        dx = MathUtils.clamp(-speed, -max_speed, max_speed);

        updatePos();
    }

    private void handleRightMOV() {

        // Only allow movement if not about to collide
        if (predictCollision(new Point((int) (position.x + max_speed + size.getWidth()), position.y))) {
            dx = 0;
            System.out.println("WALL!");
            return;
        }

        dx = MathUtils.clamp(speed, -max_speed, max_speed);

        updatePos();
    }

    /**
     * Determine if the player is colliding with the world
     * 
     * @return Is colliding?
     */
    public boolean isCollidingWithWorld() {

        // If we predict our current location, Things work
        return predictCollision(position);
    }

    private boolean predictCollision(Point pos) {
        boolean leftTop = World.getInstance().isColliding(pos);
        boolean leftBottom = World.getInstance().isColliding(pos);
        boolean rightTop = World.getInstance().isColliding(pos);
        boolean rightBottom = World.getInstance().isColliding(pos);

        return leftTop || leftBottom || rightTop || rightBottom;
    }
}