package ca.retrylife.ics4u.dragrectangle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;
import java.awt.Rectangle;

public class DragRectangle extends JFrame implements MouseListener, MouseMotionListener {
    public static void main(String[] args) {
        new DragRectangle();
    }

    // Constants
    final static Stroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
            new float[] { 9 }, 0);
    final static BasicStroke stroke = new BasicStroke(2.0f);

    // instance variables
    int scrW = 400, scrH = 400; // screen width and height
    DrawingPanel panel;

    /* Mouse trackers */
    Point mouse_start = new Point(0, 0);
    Point mouse_current = new Point(0, 0);
    boolean dragging = false;

    /* Colours */
    Color foreColour = Color.GREEN.darker();
    Color backColour = Color.WHITE;
    Color stretchColour = Color.RED;

    /* Rect tracker */
    ArrayList<Rectangle> rects = new ArrayList<>();

    DragRectangle() {
        this.setTitle("Dragging a rectangle");
        this.setSize(scrW, scrH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        panel = new DrawingPanel();
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        this.add(panel);
        this.validate();
        this.setVisible(true);
    }

    private class DrawingPanel extends JPanel {

        /**
         * Determine rect for current selection
         * 
         * @param dx Mouse dx
         * @param dy Mouse dy
         * @return Selection rect
         */
        private Rectangle determineRect(int dx, int dy) {
            if (dy < 0 && dx > 0) {
                return new Rectangle(mouse_start.x, mouse_current.y, mouse_current.x - mouse_start.x,
                        mouse_start.y - mouse_current.y);

            } else if (dy < 0 && dx < 0) {
                return new Rectangle(mouse_current.x, mouse_current.y, mouse_start.x - mouse_current.x,
                        mouse_start.y - mouse_current.y);

            } else if (dy > 0 && dx < 0) {
                return new Rectangle(mouse_current.x, mouse_start.y, mouse_start.x - mouse_current.x,
                        mouse_current.y - mouse_start.y);

            } else {
                return new Rectangle(mouse_start.x, mouse_start.y, mouse_current.x - mouse_start.x,
                        mouse_current.y - mouse_start.y);
            }
        }

        /**
         * Wrap Graphics2D and allow drawing rects with Rectangle
         * 
         * @param g2   Graphics obj
         * @param rect Rectangle to draw
         */
        private void drawRectFromRect(Graphics2D g2, Rectangle rect) {
            g2.drawRect(rect.x, rect.y, rect.width, rect.height);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Cast a Graphics2D object, and enable antialiasing
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            /* Render all rects */

            // Set drawing mode for stored rects
            g2.setPaintMode();
            g2.setColor(foreColour);
            g2.setStroke(stroke);

            // Draw each rect
            for (Rectangle rect : rects) {
                drawRectFromRect(g2, rect);
            }

            // Determine mouse position differences
            Point mouseDiff = new Point(mouse_current.x - mouse_start.x, mouse_current.y - mouse_start.y);

            if (dragging) {
                /*
                 * If you just try and draw a white rectangle, it erases all other rectangles
                 * too. Use XOR mode
                 */

                // Set drawing mode for selection box
                g2.setColor(stretchColour);
                g2.setXORMode(this.getBackground());
                g2.setStroke(dashed);

            } else {

                // Set draw mode for 1st time render
                g2.setPaintMode();
                g2.setColor(foreColour);
                g2.setStroke(stroke);

                // Add rect to rects
                rects.add(determineRect(mouseDiff.x, mouseDiff.y));

            }

            /* Handle selection rendering */

            // Determine selection rect
            Rectangle selection = determineRect(mouseDiff.x, mouseDiff.y);

            // Draw selection
            drawRectFromRect(g2, selection);

        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    // this is where the first point of the rectangle is drawn
    public void mousePressed(MouseEvent e) {
        mouse_start = e.getPoint();
    }

    public void mouseReleased(MouseEvent e) {
        dragging = false;
        panel.repaint();
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
        dragging = true;
        mouse_current = e.getPoint();
        panel.repaint();
    }
}
