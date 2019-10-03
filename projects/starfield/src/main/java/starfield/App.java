/**
 * ISC4U - Starfield
 * By: Evan Pratten
 */
package starfield;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Point;

public class App {
    public static void main(String[] args) {
        new App(35);
    }

    // Program configuration data
    public class Configuration {

        // Star settings
        static final int star_count = 200;
        static final int speed_deviation = 80;
        static final int jiggle_factor = 50;
        static final int curviness = 360;

        // Window settings
        static final int win_width = 1080;
        static final int win_height = 720;

    }

    JPanel mainPanel; // the main JPanel

    // timer variables
    Timer timer; // timer object

    // Collection of drawable stars
    ArrayList<Star> starCollection = new ArrayList<Star>();

    // Point to contain spawn point for stars
    Point m_screenCentre;

    // Random number generator
    Random rand = new Random();

    /**
     * Create a new starfield
     * 
     * @param refresh_rate Window refresh rate
     */
    App(int refresh_rate) {

        // Configure timer
        TimerListener tl = new TimerListener();
        timer = new Timer(refresh_rate, tl);
        timer.setInitialDelay(1000);
        timer.start();

        // Build a window & panel
        JFrame window = new JFrame("Star Field");
        mainPanel = new GrPanel();
        mainPanel.setBackground(Color.BLACK);

        // Register the panel
        window.add(mainPanel);

        // Window setup
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(Configuration.win_width, Configuration.win_height);

        // Determine centre location
        m_screenCentre = new Point(Configuration.win_width / 2, Configuration.win_height / 2);

        // Create some stars
        createStars(Configuration.star_count);

        // Final window configuration
        window.setLocationRelativeTo(null); // centre window
        window.setVisible(true); // must be the last (almost) thing that you do
        window.setResizable(true);
    }

    /**
     * Create some stars
     * 
     * @param num Number of stars to create
     */
    void createStars(int num) {
        for (int i = 0; i < num; i++) {

            // Choose a random speed modifier (this keeps some randomness)
            int speed_mod = rand.nextInt(Configuration.speed_deviation);

            // Choose some error for x and y starting points to cause randomness
            int x_offset = rand.nextInt(Configuration.jiggle_factor) - (Configuration.jiggle_factor / 2);
            int y_offset = rand.nextInt(Configuration.jiggle_factor) - (Configuration.jiggle_factor / 2);

            // Create a new star
            starCollection
                    .add(new Star(50 + speed_mod, new Point(m_screenCentre.x + x_offset, m_screenCentre.y + y_offset),
                            Configuration.win_height, Configuration.win_width));

        }
    }

    private class GrPanel extends JPanel {
        private static final long serialVersionUID = 8224188443773486242L;

        /**
         * Graphics loop. Renders each star, and handles window
         */
        @Override
        public void paintComponent(Graphics g) {

            // Render the background
            super.paintComponent(g);

            // Graphics2D allows smoother graphics
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Render each star
            for (Star s : starCollection) {
                s.render(g2);
            }

        }
    }

    private class TimerListener implements ActionListener {

        /**
         * Run once per frame, should be used to update objects
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            // Update each star
            for (Star s : starCollection) {
                s.update();
            }

            // Re-draw the screen
            mainPanel.repaint();
        }
    }
}