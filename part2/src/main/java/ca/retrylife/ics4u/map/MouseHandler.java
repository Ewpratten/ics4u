package ca.retrylife.ics4u.map;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;

/**
 * A class to adapt an EventListener to a polling-based system
 */
public class MouseHandler extends MouseAdapter {

    /* Mouse data */
    private Point pos = new Point(-1, -1);

    @Override
    public void mouseMoved(MouseEvent e) {

        // pos = e.getPoint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pos = e.getPoint();
    }

    public Point getMousePos() {
        return pos;
    }

}