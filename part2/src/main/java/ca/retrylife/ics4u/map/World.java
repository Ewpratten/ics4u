package ca.retrylife.ics4u.map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.annotation.Nonnull;

import java.awt.Color;

import ca.retrylife.libics.graphics.Canvas2D;
import ca.retrylife.libics.math.MathUtils;

public class World {

    /* Square types */
    public enum SquareType {

        /* All possible types */
        kEmpty(new Color(237, 245, 224)), kLand(new Color(92, 219, 148)), kLake(new Color(101, 158, 188)),
        kOcean(new Color(5, 57, 107));

        private Color clr;

        private SquareType(Color clr) {
            this.clr = clr;
        }

        public Color getColour() {
            return clr;
        }
    }

    /* Drawing canvas */
    private Canvas2D canvas;

    /* Grid */
    private Dimension gridSize, gridSquare, frameSize;
    private SquareType[][] grid;

    /* Colouring */
    private final Color lineColor = new Color(141, 228, 175);
    // private final Color[] colours = { new Color(237, 245, 224), new Color(92,
    // 219, 148) };

    @SuppressWarnings("unused")
    public World(Dimension frameSize, Dimension gridSize) {

        // Create a canvas to draw to
        canvas = new Canvas2D(this::draw);
        canvas.setSize(frameSize);
        canvas.setPreferredSize(frameSize);
        canvas.setMinimumSize(frameSize);

        // Create a blank grid
        grid = new SquareType[gridSize.height][gridSize.width];

        // Fill the empty grid with empty squares
        // Important: will throw NPE if not done here
        clearGrid();

        // Calculate gridSquare size
        this.gridSize = gridSize;
        this.frameSize = frameSize;
        this.gridSquare = new Dimension((int) (frameSize.width / gridSize.width),
                (int) (frameSize.height / gridSize.height));

        // Debug, set a square
        trySetSquareValue(pixelToSquare(new Point(100, 100)), SquareType.kLand);

    }

    public Canvas2D getCanvas() {
        return canvas;
    }

    private void draw(Graphics2D g) {

        /* Render all boxes */
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                // Determine color for square
                Color c = grid[i][j].getColour();

                // Determine rect
                int x = j * gridSquare.width;
                int y = i * gridSquare.height;
                int width = x + gridSquare.width;
                int height = y + gridSquare.height;

                // Draw square
                g.setColor(c);
                g.fillRect(x, y, width, height);

            }
        }

        /* Render all lines */

        // Set line colour
        g.setColor(lineColor);

        // Render vertical dividing lines
        for (int i = 0; i < grid.length; i++) {

            // Calculate line height
            int height = gridSquare.height * i;

            // Draw line
            g.drawLine(0, height, frameSize.width, height);
        }

        // Render horizontal dividing lines
        for (int i = 0; i < grid[0].length; i++) {

            // Calculate line width
            int width = gridSquare.width * i;

            // Draw line
            g.drawLine(width, 0, width, frameSize.width);
        }

    }

    public void clearGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = SquareType.kEmpty;
            }
        }
    }

    public Point pixelToSquare(Point pix) {
        return new Point((int) (pix.x / gridSize.width), (int) (pix.y / gridSize.height));
    }

    public void trySetSquareValue(Point square, @Nonnull SquareType type) {

        // Ensure square can be set
        if (!(MathUtils.inRange(square.x, -1, grid[0].length) || MathUtils.inRange(square.y, -1, grid.length))) {

            // If not, just return
            return;
        }

        // Set the grid square
        grid[square.y][square.x] = type;

    }
}