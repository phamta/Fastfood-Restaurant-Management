package KEO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class keobill extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPanel suggestionPanel;

    // Dữ liệu mẫu
    private List<String> data = List.of("Burger gà", "Burger bò", "Pizza", "Mì Ý", "Salad", "Gà rán");
    private JTextField textField_addcustomername;
    private JTextField textField_addcustomerphone;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    keobill frame = new keobill();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public keobill() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame to full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(254, 215, 124));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        setContentPane(contentPane);
        
        JPanel panel_order = new JPanel();
        panel_order.setBounds(80, 90, 800, 650);
        panel_order.setBackground(new Color(249, 230, 186));
        contentPane.add(panel_order);
        panel_order.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Đơn hàng");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(340, 10, 120, 40);
        panel_order.add(lblNewLabel);
        
        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 0, 0));
        separator.setBounds(50, 60, 700, 2);
        panel_order.add(separator);
        
        JLabel lblNewLabel_1 = new JLabel("Tên món");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1.setBounds(50, 80, 100, 20);
        panel_order.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Đơn giá");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2.setBounds(380, 80, 80, 20);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setVerticalAlignment(SwingConstants.CENTER);
        panel_order.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Số lượng");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2_1.setBounds(530, 80, 80, 20);
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setVerticalAlignment(SwingConstants.CENTER);
        panel_order.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("Tổng");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2_2.setBounds(670, 80, 80, 20);
        lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2.setVerticalAlignment(SwingConstants.CENTER);
        panel_order.add(lblNewLabel_2_2);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(Color.BLACK);
        separator_1.setBounds(50, 110, 700, 2);
        panel_order.add(separator_1);
        
        JLabel lblNewLabel_3 = new JLabel("Burger gà");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(50, 130, 280, 20);
        panel_order.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("500.000");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_4.setBounds(380, 130, 80, 20);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setVerticalAlignment(SwingConstants.CENTER);
        panel_order.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("1");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_5.setBounds(530, 130, 80, 20);
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setVerticalAlignment(SwingConstants.CENTER);
        panel_order.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("1.500.000");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_6.setBounds(670, 130, 80, 20);
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6.setVerticalAlignment(SwingConstants.CENTER);
        panel_order.add(lblNewLabel_6);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBackground(Color.BLACK);
        separator_2.setBounds(50, 526, 700, 2);
        panel_order.add(separator_2);
        
        JLabel lblNewLabel_7 = new JLabel("Tổng tiền");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_7.setBounds(250, 550, 80, 20);
        panel_order.add(lblNewLabel_7);
        
        JLabel lblNewLabel_7_1 = new JLabel("Tiền nhận");
        lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_7_1.setBounds(250, 580, 80, 20);
        panel_order.add(lblNewLabel_7_1);
        
        JLabel lblNewLabel_7_2 = new JLabel("Tiền thừa");
        lblNewLabel_7_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_7_2.setBounds(250, 610, 80, 20);
        panel_order.add(lblNewLabel_7_2);
        
        JLabel label_tongtien = new JLabel("");
        label_tongtien.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label_tongtien.setBounds(360, 550, 80, 20);
        panel_order.add(label_tongtien);
        
        JLabel label_tiennhan = new JLabel("");
        label_tiennhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label_tiennhan.setBounds(360, 580, 80, 20);
        panel_order.add(label_tiennhan);
        
        JLabel label_tienthua = new JLabel("");
        label_tienthua.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label_tienthua.setBounds(360, 610, 80, 20);
        panel_order.add(label_tienthua);
        
                // Panel để hiển thị gợi ý
                suggestionPanel = new JPanel();
                suggestionPanel.setBounds(176, 291, 300, 150);
                panel_order.add(suggestionPanel);
                suggestionPanel.setVisible(false);
                suggestionPanel.setLayout(null);
                
                JPanel panel = new JPanel();
                panel.setBounds(490, 161, 300, 210);
                panel_order.add(panel);
                panel.setLayout(null);
                
                JLabel lblNewLabel_8 = new JLabel("Tên:");
                lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblNewLabel_8.setBounds(30, 25, 40, 20);
                panel.add(lblNewLabel_8);
                
                JLabel lblNewLabel_9 = new JLabel("Số điện thoại:");
                lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblNewLabel_9.setBounds(30, 60, 105, 20);
                panel.add(lblNewLabel_9);
                
                JLabel lblNewLabel_10 = new JLabel("Số điểm:");
                lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblNewLabel_10.setBounds(30, 95, 70, 20);
                panel.add(lblNewLabel_10);
                
                JLabel lblNewLabel_11 = new JLabel("Dùng điểm");
                lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
                lblNewLabel_11.setBounds(30, 130, 90, 20);
                panel.add(lblNewLabel_11);
                
                JButton buttonOK = new JButton("OK");
                buttonOK.setFont(new Font("Tahoma", Font.PLAIN, 14));
                buttonOK.setBounds(40, 165, 70, 30);
                panel.add(buttonOK);
                
                JButton buttonCancel = new JButton("Cancel");
                buttonCancel.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                	}
                });
                buttonCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
                buttonCancel.setBounds(140, 165, 70, 30);
                panel.add(buttonCancel);
                
                JLabel label_name = new JLabel("");
                label_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
                label_name.setBounds(70, 25, 230, 20);
                panel.add(label_name);
                
                JLabel label_phone = new JLabel("");
                label_phone.setFont(new Font("Tahoma", Font.PLAIN, 16));
                label_phone.setBounds(135, 60, 100, 20);
                panel.add(label_phone);
                
                JLabel label_point = new JLabel("");
                label_point.setFont(new Font("Tahoma", Font.PLAIN, 16));
                label_point.setBounds(100, 95, 100, 20);
                panel.add(label_point);
        
        textField = new JTextField();
        textField.setBounds(1000, 90, 300, 30);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton button_addcustomer = new JButton("Thêm");
        button_addcustomer.setFont(new Font("Tahoma", Font.PLAIN, 16));
        button_addcustomer.setBounds(1350, 90, 100, 30);
        contentPane.add(button_addcustomer);
        
        JButton button_OK = new JButton("OK");
        button_OK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        button_OK.setBounds(1000, 700, 85, 30);
        contentPane.add(button_OK);
        
        JButton button_huy = new JButton("Hủy");
        button_huy.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        button_huy.setBounds(1120, 700, 85, 30);
        contentPane.add(button_huy);
        
        JButton button_return = new JButton("Quay lại");
        button_return.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        button_return.setBounds(1240, 700, 85, 30);
        contentPane.add(button_return);
        
        JPanel panel_addcustomer = new JPanel();
        panel_addcustomer.setBounds(1000, 120, 300, 150);
        contentPane.add(panel_addcustomer);
        panel_addcustomer.setLayout(null);
        
        JLabel lblNewLabel_8_1 = new JLabel("Tên:");
        lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_8_1.setBounds(30, 20, 40, 20);
        panel_addcustomer.add(lblNewLabel_8_1);
        
        JLabel lblNewLabel_9_1 = new JLabel("Số điện thoại:");
        lblNewLabel_9_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_9_1.setBounds(30, 60, 105, 20);
        panel_addcustomer.add(lblNewLabel_9_1);
        
        textField_addcustomername = new JTextField();
        textField_addcustomername.setBounds(80, 20, 180, 25);
        panel_addcustomer.add(textField_addcustomername);
        textField_addcustomername.setColumns(10);
        
        textField_addcustomerphone = new JTextField();
        textField_addcustomerphone.setBounds(140, 60, 120, 25);
        panel_addcustomer.add(textField_addcustomerphone);
        textField_addcustomerphone.setColumns(10);
        
        JButton button_addcustomerok = new JButton("Hoàn tất");
        button_addcustomerok.setFont(new Font("Tahoma", Font.PLAIN, 16));
        button_addcustomerok.setBounds(95, 100, 100, 30);
        panel_addcustomer.add(button_addcustomerok);

        // Lắng nghe sự kiện nhập liệu trong JTextField
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });
    }

    private void updateSuggestions() {
        String input = textField.getText().toLowerCase();
        suggestionPanel.removeAll();

        if (input.isEmpty()) {
            suggestionPanel.setVisible(false);
            return;
        }

        List<String> suggestions = new ArrayList<>();
        for (String item : data) {
            if (item.toLowerCase().contains(input)) {
                suggestions.add(item);
            }
        }

        if (suggestions.isEmpty()) {
            suggestionPanel.setVisible(false);
        } else {
            for (String suggestion : suggestions) {
                JLabel suggestionLabel = new JLabel(suggestion);
                suggestionLabel.setPreferredSize(new Dimension(textField.getWidth(), 30));
                suggestionLabel.setOpaque(true);
                suggestionLabel.setBackground(Color.WHITE);
                suggestionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                suggestionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        textField.setText(suggestion);
                        suggestionPanel.setVisible(false);
                    }
                });
                suggestionPanel.add(suggestionLabel);
            }
            suggestionPanel.setVisible(true);
        }

        suggestionPanel.revalidate();
        suggestionPanel.repaint();
    }
}
