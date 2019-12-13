package ca.retrylife.ics4u.recursetree;

import java.awt.Graphics2D;
import java.awt.Point;

import ca.retrylife.libics.geometry.Line;

public class Branch {

    Point base;
    Point end;
    double theta;
    double length;

    public Branch(Branch last, double offset) {
        this(last.getEndPoint(), last.theta + offset, last.length / 2);
    }

    public Branch(Point base, double theta, double length) {
        // Set locals
        this.base = base;
        this.theta = theta;
        this.length = length;
        this.end = new Point();

        // Calc end point
        end.x = (int) (base.x + length * Math.sin(Math.toRadians(theta)));
        end.y = (int) (base.y + length * Math.cos(Math.toRadians(theta)));

    }

    public Point getBasePoint() {
        return base;
    }

    public Point getEndPoint() {
        return end;
    }

    public void draw(Graphics2D g) {

        g.drawLine(base.x, base.y, end.x, end.y);
    }

    public Line getLine() {
        return new Line(base, end);
    }

}