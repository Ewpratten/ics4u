package ca.retrylife.ics4u.loadingicon;

import lombok.NonNull;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.function.Consumer;

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
    private int frame = 0;
    private ArrayList<Keyframe> frames = new ArrayList<>();

    /* Animation components */
    Container m_container;
    Bar m_bar;

    // Sizing
    final Rectangle baseSizing = new Rectangle(40, 40, 150, 50);

    public Animation() {
        // Create each graphical component
        m_container = new Container(baseSizing.x, baseSizing.y, baseSizing.width, baseSizing.height);
        m_bar = new Bar(baseSizing.x + 10, baseSizing.y + 4, baseSizing.width - 20, baseSizing.height - 8);

        /* Build animation */
        frames.add(new Keyframe((i) -> {
            m_container.setOpacity(i * 10);
        }, 10));
    }

    /**
     * Draw thw next frame of animation to a Graphics object
     * 
     * @param g Canvas
     */
    public void drawNextFrame(@NonNull Graphics g) {

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
    public void drawFrame(@NonNull Graphics g) {

        // Draw each component
        m_container.draw(g);
        // m_bar.draw(g);

    }
}