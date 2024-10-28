package EDIT;

import javax.swing.*;
import java.awt.*;

public class DashedCircleLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	public DashedCircleLabel(String text) {
		super(text, SwingConstants.CENTER);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// draw dashed circle
		float[] dashPattern = { 10, 10 };
		g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
		g2d.setColor(Color.BLACK);

		int diameter = Math.min(getWidth(), getHeight()) - 4;
		int x = (getWidth() - diameter) / 2;
		int y = (getHeight() - diameter) / 2;
		g2d.drawOval(x, y, diameter, diameter);
	}

	@Override
	public Dimension getPreferredSize() {
		FontMetrics fm = getFontMetrics(getFont());
		int size = Math.max(fm.stringWidth(getText()), fm.getHeight());
		return new Dimension(size + 20, size + 20);
	}
}