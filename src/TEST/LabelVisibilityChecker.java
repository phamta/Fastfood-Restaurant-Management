package TEST;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class LabelVisibilityChecker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Label Visibility Checker");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            int number = 12000000;
            String formattedNumber = formatNumber(number);

            JLabel label = new JLabel(formattedNumber);
            label.setPreferredSize(new Dimension(200, 30)); // Set a fixed size for demonstration
            frame.add(label);

            JButton checkButton = new JButton("Check Visibility");
            checkButton.addActionListener(e -> {
                if (isLabelContentVisible(label)) {
                    JOptionPane.showMessageDialog(frame, "The label displays all its content.");
                } else {
                    JOptionPane.showMessageDialog(frame, "The label does not display all its content.");
                }
            });
            frame.add(checkButton);

            frame.setSize(300, 150);
            frame.setVisible(true);
        });
    }

    public static boolean isLabelContentVisible(JLabel label) {
        FontMetrics fontMetrics = label.getFontMetrics(label.getFont());
        int textWidth = fontMetrics.stringWidth(label.getText());
        int labelWidth = label.getWidth();

        return textWidth <= labelWidth;
    }

    public static String formatNumber(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedNumber = decimalFormat.format(number).replace(',', '.');
        return formattedNumber;
    }
}
