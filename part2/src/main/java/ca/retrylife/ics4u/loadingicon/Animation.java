package ca.retrylife.ics4u.loadingicon;

import lombok.NonNull;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.awt.Point;
import java.awt.Graphics2D;

import ca.retrylife.ics4u.loadingicon.components.Bar;
import ca.retrylife.ics4u.loadingicon.components.Container;

public class Animation {

    public class Keyframe {
        Consumer<Integer> action;
        int timeout, animationStep;

        public Keyframe(Consumer<Integer> action, int timeout) {
            this.action = action;
            this.timeout = timeout;
            this.animationStep = 0;
        }
    }

    // Keep track of the animation frame count
    private ArrayList<Keyframe> frames = new ArrayList<>();

    /* Animation components */
    Container m_container;
    Bar m_bar;

    // Sizing Base
    final Dimension baseSizing = new Dimension(150, 50);

    public Animation(Point centre) {
        // Create each graphical component
        m_container = new Container(baseSizing.width, baseSizing.height, centre);
        m_bar = new Bar(baseSizing.width, baseSizing.height - 8, centre);

        // Hide bar
        m_bar.setHeight(0);

        /* Build animation */

        // Fade in
        frames.add(new Keyframe((i) -> {
            // Set container opacity
            m_container.setOpacity(i * 5);
        }, 20));

        // Fill box
        frames.add(new Keyframe((i) -> {
            // Determine bar height from progress
            double height = (baseSizing.height - 8) * ((double) i / 15);

            // Set the bar height
            m_bar.setHeight((int) height);

        }, 15));

        // Rest
        frames.add(new Keyframe((i) -> {
        }, 20));

        // Expand box
        frames.add(new Keyframe((i) -> {
            int newWidth = (int) (baseSizing.width + (400) * ((double) i / 30));

            m_bar.setWidth(newWidth, centre);
        }, 30));

        // Move left for calibration
        frames.add(new Keyframe((i) -> {
            // Find diff between current X and goal X
            int diff = m_bar.getMinX() - m_container.ox;

            m_container.setXOffset((int) (diff * ((double) i / 30)), centre);
        }, 30));

        // Reset the offset X
        frames.add(new Keyframe((i) -> {
            m_container.resetOrigin();
        }, 1));

        // Add multiple new keyframes
        int cycles = 3;
        for (int j = 0; j < cycles; j++) {

            // Add the "move right" keyframe
            frames.add(new Keyframe((i) -> {
                // Find diff between current X and goal X
                int diff = m_bar.getMaxX() - (m_container.ox + m_container.width);

                // Calculate a new ref point from container OX + half it's width (shifts happen
                // from centre, not edge).
                // We don't really care about Y here, because we dont need it
                Point og_pos = new Point(m_container.ox + m_container.width / 2, 0);

                // Determine how much we can move based on how close we are to the next keyframe
                int progress = (int) (diff * ((double) i / 60));

                m_container.setXOffset(progress, og_pos);
            }, 60));

            // Reset the offset X
            frames.add(new Keyframe((i) -> {
                m_container.resetOrigin();
            }, 1));

            // Add the "move left" keyframe
            frames.add(new Keyframe((i) -> {
                // Find diff between current X and goal X
                int diff = m_bar.getMinX() - m_container.ox;

                // Calculate a new ref point from container OX + half it's width (shifts happen
                // from centre, not edge).
                // We don't really care about Y here, because we dont need it
                Point og_pos = new Point(m_container.ox + m_container.width / 2, 0);

                // Determine how much we can move based on how close we are to the next keyframe
                int progress = (int) (diff * ((double) i / 60));

                m_container.setXOffset(progress, og_pos);
            }, 60));

            // Reset the offset X
            frames.add(new Keyframe((i) -> {
                m_container.resetOrigin();
            }, 1));
        }

        // re-centre the container
        frames.add(new Keyframe((i) -> {
            // Find diff between current X and goal X
            int diff = centre.x - (m_container.ox + (m_container.width / 2)) + 8;

            // Calculate a new ref point from container OX + half it's width (shifts happen
            // from centre, not edge).
            // We don't really care about Y here, because we dont need it
            Point og_pos = new Point(m_container.ox + m_container.width / 2, 0);

            // Determine how much we can move based on how close we are to the next keyframe
            int progress = (int) (diff * ((double) i / 30));

            m_container.setXOffset(progress, og_pos);
        }, 30));

        // Shrink box
        frames.add(new Keyframe((i) -> {
            int newWidth = (baseSizing.width + (400)) - (int) (416 * ((double) i / 30));

            m_bar.setWidth(newWidth, centre);
        }, 30));

        // Rest
        frames.add(new Keyframe((i) -> {
        }, 20));

        // Un-fill box
        frames.add(new Keyframe((i) -> {
            // Determine bar height from progress
            double height = (baseSizing.height - 8) - ((baseSizing.height - 8) * ((double) i / 15));

            // Set the bar height
            m_bar.setHeight((int) height);

        }, 15));

        // Hide bar
        frames.add(new Keyframe((i) -> {
            m_bar.setHeight(0);
        }, 1));

        // Fade out
        frames.add(new Keyframe((i) -> {
            // Set container opacity
            m_container.setOpacity(100 - (i * 5));
        }, 20));

        // Close the program
        frames.add(new Keyframe((i) -> {
            System.exit(0);
        }, 1));

    }

    /**
     * Draw thw next frame of animation to a Graphics object
     * 
     * @param g Canvas
     */
    public void drawNextFrame(@NonNull Graphics2D g) {

        handleAnimation();
        drawFrame(g);
        frame++;

    }

    public void handleAnimation() {
        // Ensure avalible keyframe
        if (frames.size() <= 0) {
            return;
        }

        // Get the keyframe
        Keyframe currentKeyframe = frames.get(0);

        // Check for finished animation
        if (currentKeyframe.animationStep >= currentKeyframe.timeout) {

            // Pop off the frame, and move to the next one
            frames.remove(0);
            return;
        }

        // Callback the keyframe updater
        currentKeyframe.action.accept(currentKeyframe.animationStep);

        currentKeyframe.animationStep++;
    }

    /**
     * Draw the current frame of animation to a Graphics object
     * 
     * @param g Canvas
     */
    public void drawFrame(@NonNull Graphics2D g) {

        // Draw each component
        m_container.draw(g);
        m_bar.draw(g);

    }
}