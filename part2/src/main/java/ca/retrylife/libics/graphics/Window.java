package ca.retrylife.libics.graphics;

import javax.swing.JFrame;
import java.awt.Point;
import java.util.function.Consumer;
import java.awt.Dimension;

public class Window extends JFrame {
    private static final long serialVersionUID = 2406077714629079044L;

    private Consumer<Window> drawCallback = null;
    private Thread thread;

    public Window(String name, Point pos, Dimension size) {
        super(name);

        this.setSize(size);
        this.setLocation(pos);
        this.setVisible(true);

        this.thread = new Thread(this::run);

    }

    public void registerUpdateHandler(Consumer<Window> callback) {
        drawCallback = callback;
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    private void run() {

        while (true) {

            // Check for if a callback was set
            if (drawCallback == null) {
                System.out.println("Window trying to draw nothing!!");
                continue;
            }

            // Run the draw callback
            drawCallback.accept(this);
        }

    }

}