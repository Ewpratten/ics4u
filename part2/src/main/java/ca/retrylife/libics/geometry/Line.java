package ca.retrylife.libics.geometry;

import java.awt.Point;

public class Line {
    public Point base, end;

    Line(Point end) {
        this(new Point(0, 0), end);
    }

    public Line(Point base, Point end) {
        this.base = base;
        this.end = end;
    }

}