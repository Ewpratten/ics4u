package ca.retrylife.libics.geometry;

import java.awt.Point;

public class Ray {

    private Point basePoint, headingPoint;
    private double heading;

    public Ray(Point base, Point heading) {

        // Set heading
        this.headingPoint = new Point(heading);

        // Set base
        this.basePoint = base;

        // Set angle
        heading.translate(-base.x, -base.y);
        this.heading = Math.toDegrees(Math.atan2(heading.y, heading.x));

        // Handle flipped heading
        if (heading.y < 0) {
            this.heading *= -1;
        }

    }

    public Ray(Point base, double heading) {

    }

    public double getHeading() {
        return heading;
    }

    public Point getBasePoint() {
        return basePoint;
    }

    public Point getHeadingPoint() {
        return headingPoint;
    }
}