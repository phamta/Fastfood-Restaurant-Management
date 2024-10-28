package KEO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BLL.Category_BLL;
import BLL.Item_BLL;
import DTO.Category;

public class keo2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tf_tiennhan;
    private JTextField tf_search;
//	private RoundedPanel panel_menu;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    keo2 frame = new keo2();
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
    public keo2() {
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
        
        // Replace JPanel with RoundedPanel for panel_menu
//        panel_menu = new RoundedPanel(40, new Color(249, 230, 186));
//        panel_menu.setBounds(30, 70, 950, 700);
//        contentPane.add(panel_menu);
//        panel_menu.setLayout(null);
        
        JPanel panel_order = new JPanel();
        panel_order.setBackground(new Color(249, 230, 186));
        panel_order.setBounds(1030, 70, 450, 550);
        contentPane.add(panel_order);
        panel_order.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 0, 0));
        panel_1.setBounds(10, 5, 430, 80);
        panel_order.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel label_image = new JLabel("New label");
        label_image.setBounds(0, 0, 120, 80);
        panel_1.add(label_image);
        
        JLabel label_name = new JLabel("Tên món");
        label_name.setForeground(new Color(255, 255, 255));
        label_name.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_name.setBounds(130, 0, 240, 30);
        panel_1.add(label_name);
        
        JLabel label_delete = new JLabel("");
        label_delete.setBounds(370, 0, 60, 80);
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/IMAGE/bin.png"));
        Image deleteImage = deleteIcon.getImage();
        Image scaledDeleteImage = deleteImage.getScaledInstance(30, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledDeleteIcon = new ImageIcon(scaledDeleteImage);
        label_delete.setIcon(scaledDeleteIcon);
        // Center the image in label_delete
        label_delete.setHorizontalAlignment(SwingConstants.CENTER);
        label_delete.setVerticalAlignment(SwingConstants.CENTER);
        panel_1.add(label_delete);
        
        JLabel label_price = new JLabel("Tổng tiền");
        label_price.setForeground(new Color(255, 255, 0));
        label_price.setFont(new Font("Tahoma", Font.BOLD, 15));
        label_price.setBounds(130, 50, 160, 30);
        panel_1.add(label_price);
        
        JLabel label_quantity = new JLabel("1");
        label_quantity.setForeground(new Color(255, 255, 255));
        label_quantity.setFont(new Font("Tahoma", Font.BOLD, 15));
        label_quantity.setBounds(300, 50, 50, 30);
        panel_1.add(label_quantity);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(39, 246, 160, 180);
        panel_order.add(panel_2);
        panel_2.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setBounds(0, 0, 160, 80);
        panel_2.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Aquafina 500ml");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(10, 80, 150, 30);
        panel_2.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("100.000 đ");
        lblNewLabel_2_1.setForeground(new Color(255, 0, 0));
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2_1.setBounds(10, 150, 150, 30);
        panel_2.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("100.000 đ");
        lblNewLabel_2_2.setForeground(new Color(255, 0, 0));
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblNewLabel_2_2.setBounds(10, 170, 170, 30);
        panel_2.add(lblNewLabel_2_2);
        
        JLabel lblVnBPhm = new JLabel("");
        lblVnBPhm.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblVnBPhm.setBounds(1200, 25, 120, 20);
        contentPane.add(lblVnBPhm);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(249, 230, 186));
        panel.setBounds(1030, 620, 450, 150);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Tổng tiền");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(10, 10, 120, 30);
        panel.add(lblNewLabel);
        
        JButton button_pay = new JButton("New button");
        button_pay.setBackground(new Color(0, 255, 0));
        button_pay.setBounds(30, 90, 130, 50);
        panel.add(button_pay);
        
        JButton button_huy = new JButton("New button");
        button_huy.setBackground(new Color(255, 0, 0));
        button_huy.setBounds(290, 90, 130, 50);
        panel.add(button_huy);
        
        JLabel lblTinNhn = new JLabel("Tiền nhận");
        lblTinNhn.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTinNhn.setBounds(10, 50, 120, 30);
        panel.add(lblTinNhn);
        
        JLabel label_total = new JLabel("");
        label_total.setBounds(260, 10, 150, 30);
        panel.add(label_total);
        
        tf_tiennhan = new JTextField();
        tf_tiennhan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tf_tiennhan.setBounds(260, 50, 150, 30);
        panel.add(tf_tiennhan);
        tf_tiennhan.setColumns(10);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(1060, 620, 390, 5);
        contentPane.add(separator);
        
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Option 1");
        JMenuItem menuItem2 = new JMenuItem("Option 2");
        popupMenu.add(menuItem1);
        popupMenu.add(menuItem2);
        
        JLabel label_avatar = new JLabel("New label");
        label_avatar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        label_avatar.setBounds(1300, 5, 55, 55);
        contentPane.add(label_avatar);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(249, 230, 186));
        panel_3.setBounds(30, 70, 950, 700);
        contentPane.add(panel_3);
        panel_3.setLayout(null);
        
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Tất cả");
        for(Category category: Category_BLL.getInstance().getAllCategory()) {
        	comboBox.addItem(category.toString());
        }
        comboBox.addItem("Combo");
        comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
				JComboBox<String> comboBox = ((JComboBox<String>)e.getSource());
                String selectedType = (String) comboBox.getSelectedItem();
                
                System.out.println("Loại món được chọn: " + selectedType);
        	}
        });
        comboBox.setBounds(30, 20, 120, 30);
        panel_3.add(comboBox);
        
        tf_search = new JTextField();
        tf_search.setBounds(500, 20, 200, 30);
        tf_search.setColumns(10);
        tf_search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLabel();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLabel();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLabel();
            }

            private void updateLabel() {
                tf_tiennhan.setText("Current text: " + tf_search.getText());
            }
        });
        panel_3.add(tf_search);
        
        JPanel panel_suggestion = new JPanel();
        panel_suggestion.setBounds(500, 50, 200, 150);
        panel_3.add(panel_suggestion);
        
        JPanel panel_4 = new JPanel();
        panel_4.setBounds(140, 109, 176, 125);
        panel_3.add(panel_4);
        panel_4.setLayout(null);
        
        
    }
}
