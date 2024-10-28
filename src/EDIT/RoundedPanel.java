package EDIT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int radius;
	private Color backgroundColor;

	public RoundedPanel(int radius, Color backgroundColor) {
		this.radius = radius;
		this.backgroundColor = backgroundColor;
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension arcs = new Dimension(radius, radius);
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draws the rounded panel with borders.
		graphics.setColor(backgroundColor);
		graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);
//		graphics.setColor(new Color(254, 215, 124));
		graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
	}
	
	@Override
	public void setBackground(Color bg) {
		this.backgroundColor = bg;
		repaint();
	}
}