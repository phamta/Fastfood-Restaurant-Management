package TEST;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class CustomLabel {
    private boolean conditionMet = false; // Biến để kiểm tra điều kiện

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CustomLabel().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Custom Label Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(null);

        JPanel panel_listcombo = new JPanel();
        panel_listcombo.setBounds(0, 0, 500, 300);
        panel_listcombo.setLayout(null);
        frame.add(panel_listcombo);

        JLabel lblNewLabel_2 = new JLabel("Add to combo");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(221, 20, 130, 30);
        lblNewLabel_2.setOpaque(true); // Để cho phép thay đổi màu nền
        panel_listcombo.add(lblNewLabel_2);

        // Thêm MouseListener để xử lý sự kiện chuột
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (conditionMet) {
                    // Thực hiện hành động khi điều kiện được đáp ứng và label được nhấn
                    System.out.println("Label clicked");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (conditionMet) {
                    lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblNewLabel_2.setCursor(Cursor.getDefaultCursor());
            }
        });

        // Giả lập điều kiện được đáp ứng sau 3 giây
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conditionMet = true;
                lblNewLabel_2.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                lblNewLabel_2.setForeground(Color.GREEN);
            }
        });
        timer.setRepeats(false);
        timer.start();

        frame.setVisible(true);
    }
}
