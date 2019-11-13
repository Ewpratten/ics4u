package ca.retrylife.ics4u.mario;

import ca.retrylife.libics.files.FileUtils;
import ca.retrylife.libics.graphics.ScreenTools;
import ca.retrylife.libics.graphics.Window;
import ca.retrylife.libics.graphics.sprites.Sprite;
import ca.retrylife.libics.graphics.sprites.SpriteMap;
import ca.retrylife.libics.math.MathUtils;

import java.awt.Point;
import java.io.IOException;
import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class Player {

    /* Config */
    private final double max_speed = 10;
    private double speed = 100;

    /* Locals */
    Window window;
    Point position;
    Dimension size;
    private double dx, dy = 0.0;
    SpriteMap spriteMap;
    Sprite pSprite;

    public Player(Point pos, Dimension size) throws IOException {
        // Set player pos
        position = pos;
        this.size = size;

        // Build a window to contain player sprite
        window = new Window("Mario", pos, size);
        window.registerUpdateHandler(this::update);

        window.setUndecorated(true);

        window.setBackground(new Color(0, 0, 0, 0));

        // Set onClose callback
        window.onClose(() -> {
            System.exit(0);
        });

        // Set Keystroke listeners
        window.registerKBDCallback(65, this::handleLeftMOV); // A
        window.registerKBDCallback(68, this::handleRightMOV); // D
        window.registerKBDCallback(32, this::handleJump); // SPACE

        // Load Sprites
        spriteMap = new SpriteMap(ImageIO.read(FileUtils.getResource("mario/spritemap.png")), new Dimension(32, 64));
        pSprite = spriteMap.getSprite(0, 1);

        // Config JPanel for sprites
        JPanel pane = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(pSprite.getImage(), 0, 0, null);
            }
        };

        pane.setOpaque(false);

        window.add(pane);
        window.setVisible(true);
    }

    public void start() {
        window.start();
    }

    private void update(Window win) {
        // Calc jump velocity
        if (position.y + size.getHeight() >= ScreenTools.getScreenSize().getHeight()) {
            dy = 0;
        } else {
            dy = 0;
        }

        // Draw window
        position.y += dy;
        window.setLocation(position);
    }

    /**
     * To be called independently of the main update loop.. Once per keystroke
     */
    private void updatePos() {
        // Move window from velocities
        position.x += dx;
        dx = 0;
    }

    private void handleLeftMOV() {

        // Only allow movement if not about to collide
        if (predictCollision(new Point((int) (position.x - max_speed), position.y))) {
            dx = 0;
            System.out.println("WALL!");
            return;
        }

        dx = MathUtils.clamp(-speed, -max_speed, max_speed);

        updatePos();
    }

    private void handleRightMOV() {

        // Only allow movement if not about to collide
        if (predictCollision(new Point((int) (position.x + max_speed + size.getWidth()), position.y))) {
            dx = 0;
            System.out.println("WALL!");
            return;
        }

        dx = MathUtils.clamp(speed, -max_speed, max_speed);

        updatePos();
    }

    private void handleJump() {
        dy = -10;
        // updatePos();
    }

    /**
     * Determine if the player is colliding with the world
     * 
     * @return Is colliding?
     */
    public boolean isCollidingWithWorld() {

        // If we predict our current location, Things work
        return predictCollision(position);
    }

    private boolean predictCollision(Point pos) {
        boolean leftTop = World.getInstance().isColliding(pos);
        boolean leftBottom = World.getInstance().isColliding(pos);
        boolean rightTop = World.getInstance().isColliding(pos);
        boolean rightBottom = World.getInstance().isColliding(pos);

        return leftTop || leftBottom || rightTop || rightBottom;
    }
}