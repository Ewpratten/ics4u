package ca.retrylife.ics4u.recursetree;

import ca.retrylife.libics.frameworks.Assignment;
import ca.retrylife.libics.geometry.Line;
import ca.retrylife.libics.graphics.Canvas2D;
import ca.retrylife.libics.graphics.Window;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.BasicStroke;

public class RecurseTree extends Assignment {

    /* Config */
    public static class Constants {
        public static final int MAX_RECURSIVE_DEPTH = 50000;
        public static final int SPLITS = 3;
        public static final double ANGLE = 180 / SPLITS;
    }

    // Count recursions
    int recursions = 0;

    /* Window */
    Window window;
    Canvas2D canvas;

    // Draw index
    int indx = 0;

    ArrayList<Line> lines = new ArrayList<>();
    ArrayList<Line> shownLines = new ArrayList<>();

    public static void main(String[] args) {
        (new RecurseTree()).run();
    }

    public RecurseTree() {
        register("RecurseTree");

        /* Create window and panel */
        window = (new Window(_name)).autoConfig();
        canvas = new Canvas2D(this::draw);
        window.add(canvas);

        // Config window
        window.setFrameDelay(15);

        // Set the window visible
        window.setVisible(true);

    }

    private void renderLine(Branch b) {

        // Handle recursive limit
        recursions++;

        if (recursions >= Constants.MAX_RECURSIVE_DEPTH || b.length < 2) {
            return;

        }


        if (b.length > 2) {
            lines.add(b.getLine());
        }

        for (int i = 0; i < Constants.SPLITS; i++) {

            // Create a temp branch
            Branch bx;

            // Handle angle side
            if (i < Constants.SPLITS / 2) {
                bx = new Branch(b, Constants.ANGLE);
            } else {
                bx = new Branch(b, Constants.ANGLE * -1);
            }

            // Render the line
            renderLine(bx);
        }

    }

    @Override
    public void run() {

        // Calculate line
        renderLine(new Branch(new Point(window.getWidth() / 2, 0), 0.0, window.getHeight() / 3));

        // Show and start the window
        window.start();

    }

    /**
     * Executes the "list of instructions" for drawing, step-by-step
     */
    private void draw(Graphics2D g) {

        // Configure draw settings
        g.setStroke(new BasicStroke(2));

        // Draw each shown line
        // synchronized (g) {
        for (Line line : shownLines) {
            g.drawLine(line.base.x, line.base.y, line.end.x, line.end.y);

        }

        // Disallow adding lines if indx gets too high
        if (indx >= lines.size()) {
            return;
        }

        // Add next line to list of shown lines
        shownLines.add(lines.get(indx));

        // Incr the index
        indx++;

    }
}