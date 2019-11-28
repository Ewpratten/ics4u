package ca.retrylife.ics4u.loadingicon;

import ca.retrylife.libics.frameworks.Assignment;
import ca.retrylife.libics.graphics.Window;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JPanel;

public class App extends Assignment {
    Window window;

    JPanel graphicsPanel;
    Animation animation;

    public static void main(String[] args) {
        (new App()).run();
    }

    public App() {
        register("Loading Icon");

        // Create a window
        window = new Window("Loading Icon");

        // Create a new panel for drawing
        graphicsPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics arg0) {
                super.paintComponent(arg0);

                // Run custom paint method
                draw(arg0);

            }
        };

        // hide
        graphicsPanel.setOpaque(false);
        graphicsPanel.setBackground(new Color(0,0,0,0));

        // Config window
        window.setSize(400, 400);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.add(graphicsPanel);
        window.setUndecorated(true);
        window.setBackground(new Color(0,0,0,0));

        // Create an animation
        animation = new Animation();

    }

    @Override
    public void run() {
        // Register the drawing callback
        // window.registerUpdateHandler(this::draw);
        window.registerUpdateHandler((win) -> {
            // Repaint the window
            win.repaint();

            // Add a little delay to stop killing my laptop
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Failed to sleep");
            }

        });

        // Register the onClose
        window.onClose(() -> {
            System.exit(0);
        });

        // Show the window
        window.setVisible(true);
        window.repaint();
        window.start();

    }

    private void draw(Graphics g) {
        // g.draw3DRect(10, 10, 20, 15, true);
        // g.dra

        // Draw the next frame of the animation
        animation.drawNextFrame(g);
    }

    // private void 
}