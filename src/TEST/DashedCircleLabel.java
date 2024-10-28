package TEST;

import javax.swing.*;
import java.awt.*;

public class DashedCircleLabel extends JLabel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DashedCircleLabel(String text) {
        super(text, SwingConstants.CENTER);
        setOpaque(false); // Để đảm bảo phần nền của JLabel là trong suốt
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Vẽ vòng tròn nét đứt
        float[] dashPattern = {10, 10};
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
        return new Dimension(size + 20, size + 20); // Thêm một khoảng trống để đảm bảo vòng tròn không bị cắt
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dashed Circle Label Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        DashedCircleLabel circleLabel = new DashedCircleLabel("+");
        circleLabel.setBounds(150, 100, 50, 50); // Set vị trí và kích thước cho JLabel
        frame.add(circleLabel);

        frame.setVisible(true);
    }
}
