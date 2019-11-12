package ca.retrylife.libics.geometry;

public class Size {
    private double width, height;

    public Size(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

}