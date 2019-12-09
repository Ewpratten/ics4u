package ca.retrylife.libics.graphics;

import javax.swing.JFrame;
import javax.swing.KeyStroke;

import java.awt.Point;
import java.util.HashMap;
import java.util.function.Consumer;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;

/**
 * Wrapper and utils for a JFrame window
 */
public class Window extends JFrame {
    private static final long serialVersionUID = 2406077714629079044L;

    private Consumer<Window> drawCallback = null;
    private Thread thread;
    private HashMap<Integer, Runnable> keybindings = new HashMap<Integer, Runnable>();

    private final long DEFAULT_FRAME_DELAY = 25L;

    /**
     * Create a new Window, and display it
     * 
     * @param name Window title
     * @param pos  Window position
     * @param size Window size
     */
    public Window(String name, Point pos, Dimension size) {
        this(name);

        // Config window
        this.setSize(size);
        this.setLocation(pos);

    }

    public Window(String name) {
        super(name);

        // Create a thread for drawing callbacks
        this.thread = new Thread(this::run);

        // Enable keystroke callbacks to handle registered callbacks
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                handleKeystroke(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /**
     * Register a callback method for each window frame
     * 
     * @param callback Callback method
     */
    public void registerUpdateHandler(Consumer<Window> callback) {
        drawCallback = callback;
    }

    /**
     * Start drawing thread
     */
    public void start() {
        thread.start();
    }

    /**
     * Stop drawing thread
     */
    public void stop() {
        thread.interrupt();
    }

    /**
     * Run in thread, handles callbacks
     */
    private void run() {

        while (true) {

            // Check for if a callback was set
            if (drawCallback == null) {
                // System.out.println("Window trying to draw nothing!!");

                // Just repaint self
                repaint();

                // Wait a bit as a default
                try{
                    Thread.sleep(DEFAULT_FRAME_DELAY);
                } catch (InterruptedException e) {
                    System.out.println("Window default drawing method interrupted");
                    e.printStackTrace();
                }

                continue;
            }

            // Run the draw callback
            drawCallback.accept(this);
        }

    }

    /**
     * Register a keystroke callback for a specific char
     * 
     * @param key      Char to watch for
     * @param callback Method to run on keypress (non-latching)
     */
    public void registerKBDCallback(char key, Runnable callback) {
        KeyStroke ks = KeyStroke.getKeyStroke(key, 0);
        registerKBDCallback(ks.getKeyCode(), callback);
    }

    /**
     * Register a keystroke callback for a specific key code
     * 
     * @param key      Key code to watch for
     * @param callback Method to run on keypress (non-latching)
     */
    public void registerKBDCallback(int keycode, Runnable callback) {
        keybindings.put(keycode, callback);
    }

    /**
     * Remove a keyboard event callback
     * 
     * @param key Key char to remove
     */
    public void unregisterKBDCallback(char key) {
        KeyStroke ks = KeyStroke.getKeyStroke(key, 0);
        keybindings.remove(ks.getKeyCode());
    }

    /**
     * Remove a keyboard event callback
     * 
     * @param key Key code to remove
     */
    public void unregisterKBDCallback(int keycode) {
        keybindings.remove(keycode);
    }

    /**
     * Handle callbacks for keystrokes (Keypressed event)
     * 
     * @param ke Event
     */
    private void handleKeystroke(KeyEvent ke) {

        // Run the callback if it exists
        if (keybindings.containsKey(ke.getKeyCode())) {
            // Run the callback
            keybindings.get(ke.getKeyCode()).run();
        }

    }

    /**
     * Set an action on window close
     * 
     * @param run Event runnable
     */
    public void onClose(Runnable run) {

        // Register the JFrame callback
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                // Run the runnable
                run.run();
            }
        });
    }

    /**
     * Automatically configure the window with some default params
     * 
     * @return Window
     */
    public Window autoConfig() {

        // Simple config
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(new Color(240, 240, 241));

        return this;
    }

    public Window makeInvisible() {
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        return this;
    }

    public void trySleep(long ms) {
        try{
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}