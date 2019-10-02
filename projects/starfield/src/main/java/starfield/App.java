package starfield;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
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

    public class Configuration {
        static final int star_count = 50;
    }

    // window sizes
    static int winWidth = 800;
    static int winHeight = 600;

    JPanel mainPanel; // the main JPanel

    // booleans
    boolean toggle = true;

    // timer variables
    Timer timer; // timer object

    // Collection of drawable stars
    ArrayList<Star> starCollection = new ArrayList<Star>();

    // Point to contain spawn point for stars
    Point m_screenCentre;

    // Random number generator
    Random rand = new Random();

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

        // Maximize window
        // Toolkit tk = Toolkit.getDefaultToolkit();
        // winWidth = ((int) tk.getScreenSize().getWidth());
        // winHeight = ((int) tk.getScreenSize().getHeight());
        window.setSize(winWidth - 50, winHeight - 100);

        // Determine centre location
        m_screenCentre = new Point(winWidth / 2, winHeight / 2);

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
            // Determine a random angle for the star to target
            // int angle = rand.nextInt(720) - 360;

            // // Create a new star
            // starCollection.add(new Star(angle, 50, new Point(m_screenCentre.x, m_screenCentre.y), 2000));
            Point target = Utils.randPerimeterPoint(winWidth, winHeight);
            System.out.println(target);
            starCollection.add(new Star(50, new Point(m_screenCentre.x, m_screenCentre.y),
                    target));

            System.out.println("Star created");
        }
    }
    
    private class GrPanel extends JPanel {

        /**
         * Component display loop
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); // this draws the background

            // Graphics2D allows smoother graphics
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Set star colour
            g2.setColor(Color.YELLOW);

            // Draw each star
            for (Star s : starCollection) {

                // double size = Math.abs(sx[i]-cx)/15.0 + Math.abs(sy[i]-cy)/15.0;

                // Render the star
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