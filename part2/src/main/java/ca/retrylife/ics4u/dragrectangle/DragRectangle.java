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

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;

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

    Point mouse_start = new Point(0, 0);
    Point mouse_current = new Point(0, 0);
    boolean dragging = false;
    Color foreColour = Color.GREEN.darker();
    Color backColour = Color.WHITE;
    Color stretchColour = Color.RED;

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
         * Draw a rect, handling all quadrants
         * 
         * @param g
         * @param dx
         * @param dy
         */
        private void drawRect(Graphics g, int dx, int dy) {
            if (dy < 0 && dx > 0) {
                g.drawRect(mouse_start.x, mouse_current.y, mouse_current.x - mouse_start.x,
                        mouse_start.y - mouse_current.y);

            } else if (dy < 0 && dx < 0) {
                // Up and to left
                g.drawRect(mouse_current.x, mouse_current.y, mouse_start.x - mouse_current.x,
                        mouse_start.y - mouse_current.y);

            } else if (dy > 0 && dx < 0) {
                g.drawRect(mouse_current.x, mouse_start.y, mouse_start.x - mouse_current.x,
                        mouse_current.y - mouse_start.y);

            } else {
                g.drawRect(mouse_start.x, mouse_start.y, mouse_current.x - mouse_start.x,
                        mouse_current.y - mouse_start.y);
            }
        }

        public void paintComponent(Graphics g) {

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int dx = mouse_current.x - mouse_start.x;
            int dy = mouse_current.y - mouse_start.y;

            if (dragging) {
                /*
                 * If you just try and draw a white rectangle, it erases all other rectangles
                 * too. Use XOR mode
                 */
                // g2.setColor(this.getBackground());

                // g2.setStroke(dashed);

                g2.setColor(stretchColour);

                // Draw border

                g2.setXORMode(this.getBackground());
                g2.setStroke(dashed);
                drawRect(g, dx, dy);

            } else {
                g2.setPaintMode();
                g2.setColor(foreColour);
                g2.setStroke(stroke);
                // Draw final box
                drawRect(g, dx, dy);

            }

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
