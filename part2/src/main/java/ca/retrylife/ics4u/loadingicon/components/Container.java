package ca.retrylife.ics4u.loadingicon.components;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import ca.retrylife.libics.math.MathUtils;

public class Container {
    public int x, y, ox;
    public int width, height;
    private int opacity = 100;

    public Container(int width, int height, Point centre) {
        this(centre.x - (width / 2), centre.y - (height / 2), width, height);
    }

    public Container(int x, int y, int width, int height) {
        this.x = x;
        this.ox = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void resetOrigin() {
        this.ox = x;
    }

    public void setOpacity(double percent) {
        opacity = (int) Math.round(MathUtils.map(percent, 0, 100, 0, 255));
    }

    public void setXOffset(int offset, Point centre) {
        x = centre.x - (width / 2) + offset;
    }

    public void draw( Graphics g) {
        g.setColor(new Color(255, 255, 255, opacity));
        g.fillRect(x, y, width, height);

    }
}