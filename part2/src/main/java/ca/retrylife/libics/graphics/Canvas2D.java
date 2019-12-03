package ca.retrylife.libics.graphics;

import java.awt.Graphics;
import java.util.function.Consumer;

import javax.swing.JPanel;
import java.awt.Graphics2D;

/**
 * JPanel wrapper to allow drawing callbacks with consumers
 */
public class Canvas2D extends JPanel {

    private static final long serialVersionUID = 5793192846238783606L;

    private Consumer<Graphics2D> draw_callback;

    /**
     * JPanel wrapper to allow drawing callbacks with consumers
     * 
     * @param callback Callback method. Called every frame
     */
    public Canvas2D(Consumer<Graphics2D> callback) {
        this.draw_callback = callback;
    }

    @Override
    protected void paintComponent(Graphics arg0) {
        super.paintComponent(arg0);

        // Callback
        draw_callback.accept((Graphics2D) arg0);

    }
}