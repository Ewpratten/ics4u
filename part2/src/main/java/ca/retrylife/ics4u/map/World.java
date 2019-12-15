package ca.retrylife.ics4u.map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.annotation.Nonnull;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import ca.retrylife.libics.graphics.Canvas2D;
import ca.retrylife.libics.math.MathUtils;
import ca.retrylife.libics.math.noise.BooleanNoiseMap;
import ca.retrylife.libics.utils.PrintUtils;

public class World {

    /* Square types */
    public enum SquareType {

        /* All possible types */
        kEmpty(new Color(237, 245, 224)), kLand(new Color(92, 219, 148)), kLake(new Color(101, 158, 188)),
        kOcean(new Color(5, 57, 107)), kMask(new Color(246, 76, 113));

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
    @SuppressWarnings("unused")
    private Dimension gridSize, gridSquare, frameSize;
    private SquareType[][] grid;

    /* Colouring */
    private final Color lineColor = new Color(141, 228, 175);

    /* World */
    BooleanNoiseMap terrainGenerator;

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

        // Do terrain generation
        terrainGenerator = new BooleanNoiseMap(gridSize.width, gridSize.height, 6);
        terrainGenerator.compute(100);

        // Generate and load the map
        int[][] generatedMap = terrainGenerator.getMap();
        setFromIntArray(generatedMap);

    }

    /**
     * Get the drawing JPanel
     * 
     * @return Panel
     */
    public Canvas2D getCanvas() {
        return canvas;
    }

    /**
     * Draw the frame
     * 
     * @param g Graphics obj
     */
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

    /**
     * Clear the grid
     */
    public void clearGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = SquareType.kEmpty;
            }
        }
    }

    /**
     * Convert a pixel position, to the coordinate of the square it is inside
     * 
     * @param pix Pixel position
     * @return Square position
     */
    public Point pixelToSquare(Point pix) {
        return new Point((int) (pix.x / gridSquare.width), (int) (pix.y / gridSquare.height));
    }

    /**
     * Try to set the value of a square, or do nothing if not possible
     * 
     * @param square Square to set
     * @param type   Value
     */
    public void trySetSquareValue(Point square, @Nonnull SquareType type) {

        // Ensure square can be set
        if (!(MathUtils.inRange(square.x, -1, grid[0].length) || MathUtils.inRange(square.y, -1, grid.length))) {

            // If not, just return
            return;
        }

        // Set the grid square
        grid[square.y][square.x] = type;

    }

    /**
     * Handle a "splash". Splashes are things like placing a lake
     * 
     * @param square Where the splash occured
     */
    public void handleSplash(Point square) {

        // Ensure square can be set
        if (!isValidPoint(square)) {

            // Must do something here to make my linter happy
            System.out.print("");

            // If not, just return
            return;
        }

        // Determine if the splash is for land, or water
        SquareType type = grid[square.y][square.x];

        // If the splash is on part of the map that has already been touched, do nothing
        if (type != SquareType.kEmpty) {

            // Must do something here to make my linter happy
            System.out.print("");

            // Return if un-splashible
            return;
        }

        // Create a fill mask, and determine if this splash is an ocean of not
        boolean is_ocean = splash(square);

        // Check the board for fill masks, and set them based on is_ocean
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                // Check if the current is a mask square
                if (grid[i][j] == SquareType.kMask) {

                    // Set square type based on is_ocean
                    grid[i][j] = (is_ocean) ? SquareType.kOcean : SquareType.kLake;
                }

            }
        }

    }

    /**
     * Check if a point is valid (is it a valid array index)
     * 
     * @param loc Point to check
     * @return Is valid?
     */
    private boolean isValidPoint(Point loc) {
        return (MathUtils.inRange(loc.x, 0, grid[0].length - 1) && MathUtils.inRange(loc.y, 0, grid.length - 1));
    }

    /**
     * Check if a point is touching the edge of the grid by checking the validity of
     * the points around it. This should be used in conjunction with isValidPoint,
     * as it will throw false-positives for invalid points.
     * 
     * @param loc Point to check
     * @return Is it touching the edge
     */
    private boolean isPointTouchingEdge(Point loc) {
        return (!isValidPoint(new Point(loc.x - 1, loc.y - 1))) || (!isValidPoint(new Point(loc.x + 1, loc.y + 1)));
    }

    /**
     * Check if a grid square is empty (invalid squares count as full)
     * 
     * @param loc Square
     * @return Is empty?
     */
    private boolean isEmpty(Point loc) {

        // It is full if it does not exist
        if (!isValidPoint(loc)) {
            return false;
        }

        return grid[loc.y][loc.x] == SquareType.kEmpty;

    }

    private boolean splash(Point start) {

        // Define the next point to check
        Point next;

        // Store if the splash has hit the edge of the screen
        boolean hasHitEdge = false;

        // Check for an edge hit
        hasHitEdge = isPointTouchingEdge(start);

        // Plot the splash
        grid[start.y][start.x] = SquareType.kMask;

        /* Handle each corner (ensure the corner is "splashable") */

        // Handle left corner
        if (isEmpty((next = new Point(start.x - 1, start.y)))) {

            // Set edge hit tracker if the recursive step hit the edge
            if (splash(next)) {
                hasHitEdge = true;
            }

        }

        // Handle right corner
        if (isEmpty((next = new Point(start.x + 1, start.y)))) {

            // Set edge hit tracker if the recursive step hit the edge
            if (splash(next)) {
                hasHitEdge = true;
            }

        }

        // Handle up
        if (isEmpty((next = new Point(start.x, start.y - 1)))) {

            // Set edge hit tracker if the recursive step hit the edge
            if (splash(next)) {
                hasHitEdge = true;
            }

        }

        // Handle down
        if (isEmpty((next = new Point(start.x, start.y + 1)))) {

            // Set edge hit tracker if the recursive step hit the edge
            if (splash(next)) {
                hasHitEdge = true;
            }

        }

        // Return if edge has been hit
        return hasHitEdge;
    }

    /**
     * Set the grid from an int array (Used for converting noise data to a map)
     * 
     * @param arr Int data
     */
    private void setFromIntArray(int[][] arr) {
        // Do assertions
        assertEquals(grid.length, arr.length);
        assertEquals(grid[0].length, arr[0].length);

        // Copy array
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                // SquareType to store
                SquareType type;

                // Determine type
                switch (arr[i][j]) {
                case 0:
                    type = SquareType.kEmpty;
                    break;
                case 1:
                    type = SquareType.kLand;
                    break;
                case 2:
                    type = SquareType.kLake;
                    break;
                case 3:
                    type = SquareType.kOcean;
                    break;
                case 4:
                    type = SquareType.kMask;
                default:
                    type = SquareType.kEmpty;

                }

                // Set type
                grid[i][j] = type;

            }
        }
    }
}