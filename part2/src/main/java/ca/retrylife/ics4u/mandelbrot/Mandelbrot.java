package ca.retrylife.ics4u.mandelbrot;

import ca.retrylife.libics.frameworks.Assignment;
import ca.retrylife.libics.graphics.Canvas2D;
import ca.retrylife.libics.graphics.Window;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;

public class Mandelbrot extends Assignment {

    Window window;
    Canvas2D canvas;

    private static class Consts {
        final static double xmin = -2.0;
        final static double xmax = +1.0;
        final static double ymin = -1.0;
        final static double ymax = +1.0;
        static double xstep = 1.0;
        static double ystep = 1.0;

    }

    public static void main(String[] args) {
        (new Mandelbrot()).run();
    }

    public Mandelbrot() {
        register("Mandelbrot");

        // Create the window and canvas
        window = (new Window("Mandelbrot")).autoConfig();
        canvas = new Canvas2D(this::draw);

        // Add canvas to window
        window.add(canvas);

        // Show window
        window.setVisible(true);

        // Config the steps
        Consts.xstep = (Consts.xmax - Consts.xmin) / window.getWidth();
        Consts.ystep = (Consts.ymax - Consts.ymin) / window.getHeight();

    }

    @Override
    public void run() {
        window.start();

    }

    private void draw(Graphics2D g) {

        int n; // Recurse count
        Color c;
        Point p;

        for (double x = Consts.xmin; x < Consts.xmax; x += Consts.xstep) {
            for (double y = Consts.ymin; y < Consts.ymax; y += Consts.ystep) {

                n = recurse(x, y);

                if (n == 0) {
                    c = Color.BLACK;
                } else {
                    // c = new Color(0, n%256, 0);
                    c = Color.getHSBColor((float) n / 512, .8f, 1.f);
                }

                g.setColor(c);

                p = toPix(x, y, window.getWidth(), window.getHeight());
                g.drawLine(p.x, p.y, p.x, p.y);
            }
        }

    }

    private int recurse(double cReal, double cImag) {

        double zReal = 0.;
        double zImag = 0.;
        double tmp;

        int n = 0;

        while (n++ < 512 && (zReal * zReal + zImag * zImag) < 4) {
            tmp = zReal * zReal - zImag * zImag + cReal;

            zImag = 2 * zReal * zImag + cImag;
            zReal = tmp;

        }

        return (n >= 512) ? 0 : n;
    }

    Point toPix(double x, double y, int w, int h) {
        int px = (int) ((x - Consts.xmin) * (w / (Consts.xmax - Consts.xmin)));
        int py = (int) ((-1 * y - Consts.ymin) * (h / (Consts.ymax - Consts.ymin)));
        return new Point(px, py);
    }

}