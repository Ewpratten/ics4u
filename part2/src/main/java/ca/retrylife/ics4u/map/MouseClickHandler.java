package ca.retrylife.ics4u.map;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.BiConsumer;
import java.awt.Point;

/**
 * A simple adapter for mouse clicks
 */
public class MouseClickHandler extends MouseAdapter {

    /* Handler */
    BiConsumer<Point, Integer> handler;

    public MouseClickHandler(BiConsumer<Point, Integer> clickHandler) {
        this.handler = clickHandler;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        // Read mouse data
        Point pos = e.getPoint();
        int event = e.getButton();

        // Pass data along to handler
        handler.accept(pos, event);

    }

}