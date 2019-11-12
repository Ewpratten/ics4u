package ca.retrylife.ics4u.mario;

import ca.retrylife.libics.graphics.Window;

import java.awt.Point;
import java.awt.Dimension;

public class Player {

    Window window;
    Point position;
    Dimension size;

    public Player(Point pos, Dimension size) {
        // Set player pos
        position = pos;
        this.size = size;


        // Build a window to contain player sprite
        window = new Window("Mario", pos, size);
        window.registerUpdateHandler(this::update);
    }

    private void update(Window win) {
        System.out.println(isCollidingWithWorld());
    }

    public void start() {
        window.start();
    }

    private boolean isCollidingWithWorld(){
        boolean leftTop = World.getInstance().isColliding(position);
        boolean leftBottom = World.getInstance().isColliding(position);
        boolean rightTop = World.getInstance().isColliding(position);
        boolean rightBottom = World.getInstance().isColliding(position);

        return leftTop || leftBottom || rightTop || rightBottom;
    }
}