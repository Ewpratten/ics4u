package ca.retrylife.ics4u.tricollision;

import ca.retrylife.libics.frameworks.Assignment;
import ca.retrylife.libics.graphics.Canvas2D;
import ca.retrylife.libics.graphics.Window;

import java.awt.Graphics2D;

public class TriCollision extends Assignment {

    Window window;
    Canvas2D canvas;

    public static void main(String[] args) {
        (new TriCollision()).run();
    }

    public TriCollision() {
        register("TriCollision");

        // Config window
        window = (new Window("TriCollision")).autoConfig();
        canvas = new Canvas2D(this::draw);
        window.add(canvas);

        // Display window
        window.setVisible(true);
        
    }
    
    @Override
    public void run() {
        
    }

    private void draw(Graphics2D g) {
        
    }
}