package TEST;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CustomRoundedLabel extends JLabel {
    private static final int BORDER_SIZE = 4;

    public CustomRoundedLabel(ImageIcon icon) {
        super(icon);
        setPreferredSize(new Dimension(icon.getIconWidth() + 2 * BORDER_SIZE, icon.getIconHeight() + 2 * BORDER_SIZE));
        setOpaque(false); // Make sure the label is transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Antialiasing for smooth border
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the border circle
        g2.setColor(Color.BLACK);
        g2.fill(new Ellipse2D.Double(0, 0, width, height));

        // Draw the inner circle (the image)
        g2.setClip(new Ellipse2D.Double(BORDER_SIZE, BORDER_SIZE, width - 2 * BORDER_SIZE, height - 2 * BORDER_SIZE));
        
        ImageIcon icon = (ImageIcon) getIcon();
        Image image = icon.getImage();
        int imageWidth = width - 2 * BORDER_SIZE;
        int imageHeight = height - 2 * BORDER_SIZE;
        g2.drawImage(image, BORDER_SIZE, BORDER_SIZE, imageWidth, imageHeight, this);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint the default border
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom Rounded Label Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        ImageIcon icon = new ImageIcon("\"D:\\chava\\Final\\src\\IMAGE\\burgerga.jpg\""); // Replace with the path to your image
        CustomRoundedLabel roundedLabel = new CustomRoundedLabel(icon);

        frame.add(roundedLabel);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }
}
