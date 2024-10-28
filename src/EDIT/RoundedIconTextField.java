package EDIT;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class RoundedIconTextField extends JTextField {
    private Color fillColor;
    private Color lineColor;
    private int strokeWidth;
    private Icon prefixIcon;
    private Icon suffixIcon;

    private boolean suffixIconClicked = false;
    private String actualText = "";

    public RoundedIconTextField() {
        fillColor = new Color(236, 240, 241);
        lineColor = new Color(52, 152, 219);
        strokeWidth = 2;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        attachSuffixIconClickListener();
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(String imagePath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
//        Image image = icon.getImage();
//        Image scaledImage = image.getScaledInstance(40, 30, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        this.suffixIcon = icon;
        initBorder();
        repaint(); // Ensure the component is repainted when the icon changes
    }

    @Override
    public void setText(String text) {
        actualText = text;
        super.setText(suffixIconClicked ? actualText : maskText(actualText));
    }

    @Override
    public String getText() {
//        return actualText;
        return super.getText();
    }

    private String maskText(String text) {
        return "*".repeat(text.length());
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int s = strokeWidth;
            int w = getWidth() - (2 * s);
            int h = getHeight() - (2 * s);
            g2d.setColor(fillColor);
            g2d.fillRoundRect(s, s, w, h, h, h); // Fill background with rounded corners
            g2d.setStroke(new BasicStroke(s));
            g2d.setColor(lineColor);
            g2d.drawRoundRect(s, s, w, h, h, h); // Draw rounded border
        }
        super.paintComponent(g);
        paintIcon(g); // Paint icons
    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 3, y, this);
        }
        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 3, y, this);
        }
    }

    private void initBorder() {
        int left = 5;
        int right = 5;
        if (prefixIcon != null) {
            left = prefixIcon.getIconWidth() + 5;
        }
        if (suffixIcon != null) {
            right = suffixIcon.getIconWidth() + 5;
        }
        setBorder(BorderFactory.createEmptyBorder(5, left, 5, right));
    }

    private void attachSuffixIconClickListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (suffixIcon != null) {
                    int x = e.getX();
                    int y = e.getY();
                    int iconX = getWidth() - suffixIcon.getIconWidth() - 3;
                    int iconY = (getHeight() - suffixIcon.getIconHeight()) / 2;
                    int iconWidth = suffixIcon.getIconWidth();
                    int iconHeight = suffixIcon.getIconHeight();

                    if (x >= iconX && x <= iconX + iconWidth && y >= iconY && y <= iconY + iconHeight) {
                        suffixIconClicked = !suffixIconClicked;
                        if (suffixIconClicked) {
                            setSuffixIcon("/IMAGE/iconcloseye.png");
                            RoundedIconTextField.this.setText(actualText); // Show actual text
                        } else {
                            setSuffixIcon("/IMAGE/iconopeneye.png");
                            RoundedIconTextField.this.setText(actualText); // Show masked text
                        }
                    }
                }
            }
        });
    }
}
