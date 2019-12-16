package ca.retrylife.ics4u.map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handler for the various program keyboard shortcuts
 * 
 * Shortcuts:
 * Save  - CTRL+S
 * Load  - CTRL+L
 * Reset - CTRL+R
 */
public class KeypressHandler implements KeyListener {

    private Runnable onSave, onLoad, onReset;

    public KeypressHandler(Runnable onSave, Runnable onLoad, Runnable onReset) {
        
        // Set locals
        this.onSave = onSave;
        this.onLoad = onLoad;
        this.onReset = onReset;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void keyPressed(KeyEvent e) {

        // Handle Save
        if ((e.getKeyCode() == KeyEvent.VK_S) && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
            onSave.run();
        }

        // Handle Load
        if ((e.getKeyCode() == KeyEvent.VK_L) && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
            onLoad.run();
        }

        // Handle Reset
        if ((e.getKeyCode() == KeyEvent.VK_R) && (e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
            onReset.run();
        }

    }

    @Override
    public void keyReleased(KeyEvent arg0) {

    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }
    
}