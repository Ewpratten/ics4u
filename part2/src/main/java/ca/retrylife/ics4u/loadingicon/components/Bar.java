package ca.retrylife.ics4u.loadingicon.components;

import java.awt.Point;

import java.awt.Color;
import java.awt.Graphics;

public class Bar {
    private int x, y, width, height;

    public Bar(int width, int height, Point centre) {
        this(centre.x - (width / 2), centre.y - (height / 2), width, height);
    }

    public Bar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Set the box width, while preserving position
     * 
     * @param width
     */
    public void setWidth(int width, Point centre) {
        this.x = centre.x - (width / 2);
        this.width = width;
    }

    public int getMinX() {
        return x;
    }

    public int getMaxX() {
        return x + width;
    }

    public void draw( Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);

    }
}