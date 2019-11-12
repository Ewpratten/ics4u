package ca.retrylife.ics4u.mario;

import java.awt.Dimension;
import java.awt.Point;

import ca.retrylife.libics.graphics.ScreenTools;
import ca.retrylife.libics.math.MathUtils;

public class World {
    private static World instance;

    Dimension screenSize;

    public World() {

        // Get the screen size 
        screenSize = ScreenTools.getScreenSize();
    }

    public static World getInstance() {
        if (instance == null) {
            instance = new World();
        }

        return instance;
    }

    public boolean isColliding(Point p) {
        return !(MathUtils.inRange(p.x, 0, screenSize.getWidth()) && MathUtils.inRange(p.y, 0, screenSize.getHeight()));
    }
}