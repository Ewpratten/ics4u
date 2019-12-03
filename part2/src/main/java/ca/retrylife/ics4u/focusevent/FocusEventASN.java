/**
 * ICS4U - WindowFocusEvent demo
 * By: Evan Pratten
 * 
 * Goals:
 *  - Display current window focus status on screen
 */

package ca.retrylife.ics4u.focusevent;

import ca.retrylife.libics.frameworks.Assignment;
import ca.retrylife.libics.graphics.Canvas2D;
import ca.retrylife.libics.graphics.Window;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.FontMetrics;

public class FocusEventASN extends Assignment {

    /* Consts */
    private final Color FG_COLOR_1 = new Color(0, 224, 56);
    private final Color FG_COLOR_2 = new Color(255, 97, 97);
    private final Font MAIN_FONT = new Font("TimesRoman", Font.BOLD, 20);

    private final String INFO_MSG = "This will behave differently on different systems";
    private final String NON_FOCUSED_MSG = "Window not focused";
    private final String FOCUSED_MSG = "Window focused";

    /* State trackers */
    private boolean isFocused = false;

    /* Window objects */
    Window window;

    // Focus listener
    WindowFocusListener winFocusListener = new WindowFocusListener() {
        @Override
        public void windowLostFocus(WindowEvent arg0) {
            isFocused = false;
        }

        @Override
        public void windowGainedFocus(WindowEvent arg0) {
            isFocused = true;
        }
    };

    public static void main(String[] args) {
        (new FocusEventASN()).run();
    }

    public FocusEventASN() {
        // Create a window with some default params
        window = (new Window("FocusEvent")).autoConfig();

        // Give the window a nice colour
        window.setBackground(new Color(240, 240, 241));

        // Add a canvas to the window
        // This is just a JPanel hidden behind a consumer to clean up the code a bit
        window.add(new Canvas2D(this::draw));

        // Add event listener for window focus
        window.addWindowFocusListener(winFocusListener);

        // Show the window
        window.setVisible(true);

    }

    @Override
    public void run() {

        // Start the window thread
        window.start();

    }

    /**
     * Get the point at which to draw text so it is centred on the screen
     * 
     * @param metrics Font information
     * @param text    Text to be calculated
     * @return Graphics2D.drawString position
     */
    private Point getTextPosition(FontMetrics metrics, String text) {
        int x = (window.getWidth() - metrics.stringWidth(text)) / 2;
        int y = ((window.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

        return new Point(x, y);
    }

    /**
     * Callback method for drawing to the screen
     * 
     * @param g Graphics2D object
     */
    private void draw(Graphics2D g) {
        synchronized (g) {
            // Clear the screen
            g.clearRect(0, 0, window.getWidth(), window.getHeight());

            // Print warning
            g.setColor(Color.gray);
            g.setFont(MAIN_FONT);

            Point loc = getTextPosition(g.getFontMetrics(), INFO_MSG);
            g.drawString(INFO_MSG, loc.x, window.getHeight() - 25);

            // Handle drawing for each window state
            if (isFocused) {

                // Set text settings
                g.setColor(FG_COLOR_1);

                // Determine where to draw text
                loc = getTextPosition(g.getFontMetrics(), FOCUSED_MSG);

                g.drawString(FOCUSED_MSG, loc.x, loc.y);

            } else {

                // Set text settings
                g.setColor(FG_COLOR_2);

                // Determine where to draw text
                loc = getTextPosition(g.getFontMetrics(), NON_FOCUSED_MSG);

                g.drawString(NON_FOCUSED_MSG, loc.x, loc.y);

            }
        }

        // Wait a bit to provide a steady framerate
        window.trySleep(25);
    }

}