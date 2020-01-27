package loadingIcons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Point;

/***********************************************************************************************************
 * This is part of a nice attractive program written by a student as part of
 * their loading icon project. Your job is to put the three lines into objects,
 * instead of having 12 global variables and another 6 variables for the
 * centres!
 *
 * 1. Make a Line class; you can make this an inner class if you want. Create
 * the three lines (each Line object can be a global variable). (If we had more
 * than 3, we'd use an arraylist as our only global variable, with 'panel' and
 * 'angle' of course)
 *
 * Make sure that you can still draw the lines and that they still spin.
 *
 * 2. Now fix up the paintComponent so that the actual drawing (ie. the 3 lines
 * below) is done as a method in the Line class g2.rotate(angle,cx,cy);
 * g2.drawLine( (int)x1, (int)y1, (int)x2, (int)y2); g2.rotate(-angle,cx,cy);
 * 
 * Your paintComponent can then be quite simplified, so that it looks similar to
 * this:
 * 
 * @Override public void paintComponent(Graphics g) { super.paintComponent(g);
 * 
 *           Graphics2D g2 = (Graphics2D) g;
 *           g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
 *           RenderingHints.VALUE_ANTIALIAS_ON); g2.setStroke(new
 *           BasicStroke(3)); g2.setColor(Color.GREEN);
 * 
 *           line1.draw(g2); //the drawing of the line has been moved to a
 *           method in the Line class. line2.draw(g2); line3.draw(g2); }
 *
 *           3. The program should still look identical to the user when it
 *           runs, no matter what code changes you make.
 *
 ***********************************************************************************************************/
public class SpinningLines extends JFrame implements ActionListener {

	public static void main(String[] args) {
		new SpinningLines();
	}

	/* Constants */
	static final int panW = 425;
	static final int panH = 450;

	// Panel for graphics
	DrawingPanel panel;

	// All lines
	ArrayList<Line> lines = new ArrayList<>();

	SpinningLines() {

		// Create each line
		lines.add(new Line(new Point(212, 40), new Point(355, 280)));
		lines.add(new Line(new Point(212, 70), new Point(40, 280)));
		lines.add(new Line(new Point(70, 280), new Point(355, 280)));

		// Set up window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Set up the panel
		panel = new DrawingPanel();
		this.add(panel);
		this.pack();
		this.setVisible(true);

		// Start a drawing timer
		Timer timer = new Timer(30, this);
		timer.start();
	}

	class DrawingPanel extends JPanel {

		DrawingPanel() {

			// Set panel params
			this.setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(panW, panH));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Get G2 object, and configure rendering
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.GREEN);

			// Render each line
			lines.forEach((line) -> {
				line.draw(g2);
			});

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// Rotate each line
		lines.forEach((line) -> {
			line.rotateBy(0.05);
		});

		// Repaint the panel
		panel.repaint();
	}
}

/**
 * Things that can be drawn
 */
interface Drawable {
	/**
	 * Draw an object to a canvas
	 * 
	 * @param g2 Canvas graphics object
	 */
	public void draw(Graphics2D g2);
}

/**
 * A drawable line
 */
class Line implements Drawable {

	// Line vertices
	private Point p1, p2;

	// Line centre
	private Point c;

	// Line angle
	private double angle;

	/**
	 * Create a Line from two points
	 * 
	 * @param p1 Point 1
	 * @param p2 Point 2
	 */
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;

		// Calculate centre
		this.c = new Point((int) ((p1.getX() + p2.getX()) / 2.0), (int) ((p1.getY() + p2.getY()) / 2.0));
	}

	@Override
	public void draw(Graphics2D g2) {

		// Position the line
		g2.rotate(angle, c.getX(), c.getY());

		// Render the line
		g2.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());

		// Re-position the line
		g2.rotate(-angle, c.getX(), c.getY());
	}

	/**
	 * Rotate the line by an angle
	 * 
	 * @param angle Angle to rotate by
	 */
	public void rotateBy(double angle) {
		this.angle += angle;

	}

}