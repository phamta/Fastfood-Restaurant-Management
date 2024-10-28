package KEO;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import BLL.Item_BLL;
import DTO.Item;
import EDIT.RoundedPanel;
import TEST.DashedCircleLabel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class keoitem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_menubar;
	private RoundedPanel panel_content;
	private JPanel panel_menu;
	private RoundedPanel panel_detailitem;
	
	private String itemid;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keoitem frame = new keoitem();
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
	public keoitem() {
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

		JPanel panel = new JPanel();
		panel.setBounds(30, 30, 230, 75);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_menubar = new JPanel();
		panel_menubar.setBounds(30, 140, 230, 620);
		contentPane.add(panel_menubar);
		panel_menubar.setLayout(null);

//		panel_content = new RoundedPanel(50, new Color(249, 230, 186));
////		panel_content.setBackground(new Color(255, 255, 255));
//		panel_content.setBounds(310, 30, 720, 730);
//		contentPane.add(panel_content);
//		panel_content.setLayout(null);
//
//		panel_menu = new JPanel();
//		panel_menu.setBackground(new Color(249, 230, 186));
//		int distant = 10;
////		panel_menu.setBounds(10, 30, 700, 670);
//		panel_menu.setPreferredSize(new Dimension(panel_content.getWidth()-2*distant, 2000));
////		panel_content.add(panel_menu);
//		panel_menu.setLayout(null);
//
//		JScrollPane scrollPanepanel_menu = new JScrollPane(panel_menu);
//		scrollPanepanel_menu.setBounds(10, 30, panel_menu.getPreferredSize().width, 670);
//		scrollPanepanel_menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		scrollPanepanel_menu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		panel_content.add(scrollPanepanel_menu);
		
		panel_content = new RoundedPanel(50, new Color(249, 230, 186));
//		panel_content.setBackground(new Color(255, 255, 255));
		panel_content.setBounds(310, 30, 730, 730);
		contentPane.add(panel_content);
		panel_content.setLayout(null);

		panel_menu = new JPanel();
		panel_menu.setBackground(new Color(249, 230, 186));
		int distant = 10;
//		panel_1.setBounds(10, 30, 1170, 670);
		panel_menu.setPreferredSize(new Dimension(700, 1500));
//		panel_content.add(panel_1);
		panel_menu.setLayout(null);

		JScrollPane scrollPanepanel_menu = new JScrollPane(panel_menu);
		scrollPanepanel_menu.setBounds(10, 30, panel_menu.getPreferredSize().width, 670);
		scrollPanepanel_menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanepanel_menu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_content.add(scrollPanepanel_menu);


//		List<Item> list = Item_BLL.getInstance().getAllItem();
//		displayItems(list);
		
		panel_detailitem= new RoundedPanel(50, new Color(249, 230, 186));
		panel_detailitem.setBounds(1060, 30, 450, 730);
		
		
		
		contentPane.add(panel_detailitem);
		panel_detailitem.setLayout(null);
		
		JLabel label_cancel = new JLabel("X");
		label_cancel.setForeground(new Color(255, 0, 0));
		label_cancel.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_cancel.setBounds(400, 10, 30, 30);
		panel_detailitem.add(label_cancel);
		
		JLabel label_ok = new JLabel("");
		label_ok.setBounds(350, 10, 30, 30);
		panel_detailitem.add(label_ok);
		ImageIcon icon = new ImageIcon(getClass().getResource("/IMAGE/tichxanh.png"));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(label_ok.getWidth(), label_ok.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label_ok.setIcon(scaledIcon);
		
	}

	// create panel for product
	private JPanel createItemPanel(Item item, int x, int y) {
		JPanel panel = new JPanel();
		panel.setBounds(x, y, 155, 170);
//		panel.addMouseListener(createMouseListener(panel, item));
//		panel.addMouseMotionListener(createMouseMotionListener(panel));
		panel.setLayout(null);

		int letter_seize = 18;

		JLabel label_image = new JLabel();
		label_image.setBounds(0, 0, panel.getWidth(), 85);
		label_image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		String s = item.getImagePath();
		if (s != null) {
			try {
				ImageIcon icon = new ImageIcon(getClass().getResource(s));
				if (icon != null) {
					Image image = icon.getImage();
					Image scaledImage = image.getScaledInstance(label_image.getWidth(), label_image.getHeight(),
							Image.SCALE_SMOOTH);
					ImageIcon scaledIcon = new ImageIcon(scaledImage);
					label_image.setIcon(scaledIcon);
				}
			} catch (NullPointerException e) {
				System.err.println("NullPointerException: " + e.getMessage());
				e.printStackTrace();
			} catch (Exception ex) {
				System.err.println("Exception: " + ex.getMessage());
				ex.printStackTrace();
			}
		} else {
			System.err.println("Image path is null.");
		}
		panel.add(label_image);

		JLabel label_name = new JLabel(item.getItemName());
		label_name.setBounds(5, label_image.getHeight(), panel.getWidth() - 5, 30);
		label_name.setFont(new Font("Tahoma", Font.PLAIN, letter_seize));
		panel.add(label_name);

		JLabel label_price = new JLabel(item.getPrice() + " đ");
		label_price.setForeground(new Color(255, 0, 0));
		label_price.setBounds(5, panel.getHeight() - 35, panel.getWidth() - 5, 35);
		label_price.setForeground(new Color(255, 0, 0));
		label_price.setFont(new Font("Tahoma", Font.PLAIN, letter_seize));
		panel.add(label_price);

		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.putClientProperty("item", item);

		return panel;
	}

	private void displayItems(List<Item> list) {
		String type = "Burger";
		panel_menu.removeAll();
		int x = 10, y = 10;
		int maxHeight = 70; // Để lưu chiều cao lớn nhất của panel
		for (Item item : list) {
			JPanel panel = createItemPanel(item, x, y);
			panel_menu.add(panel);

			int panelHeight = panel.getPreferredSize().height;
			if (panelHeight > maxHeight) {
				maxHeight = panelHeight;
			}
			// Kiểm tra xem panel tiếp theo có thể được thêm vào hàng hiện tại không
			if (x + panel.getWidth() * 2 + 10 > panel_menu.getPreferredSize().width
					|| item.getType().compareTo(type) != 0) {
				y += panel.getHeight() + 10; // Y tăng lên theo chiều cao lớn nhất của panel trong hàng
				x = 10;
				maxHeight = 70; // Reset chiều cao lớn nhất
				type = item.getType();
			} else {
				x += panel.getWidth() + 10;
			}
		}
		JPanel panel_add = new JPanel();
		panel_add.setBounds(x, y, 155, 170);
		panel_add.setLayout(null);

		DashedCircleLabel label_add = new DashedCircleLabel("+");
		label_add.setSize(50, 50);
		int add_x = (panel_add.getWidth() - label_add.getWidth()) / 2;
		int add_y = (panel_add.getHeight() - 50) / 2;
		label_add.setLocation(add_x, add_y);
		panel_add.add(label_add);
		panel_add.addMouseListener(createMouseListener(panel_add));
//		System.out.println(add_x + " " + add_y);
//      circleLabel.setSize(50, 50);
//      System.out.println(panel_1_1.getWidth() + " " + panel_1_1.getHeight() + " " + circleLabel.getWidth() + " " + circleLabel.getHeight());

		panel_menu.add(panel_add);
		
		JPanel panel = new JPanel();
		panel.setBounds(210, 10, 155, 170);
		panel_menu.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 155, 85);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Burger gà");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(5, 85, 150, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("40.000");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(5, 145, 150, 25);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Điểm");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(5, 110, 45, 35);
		panel.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setText("10");
		textField.setBounds(50, 110, 105, 35);
		textField.setBackground(null);
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		textField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    textField.setEditable(true);
                }
            }
        });

		panel_menu.revalidate();
		panel_menu.repaint();
	}

	private MouseAdapter createMouseListener(JPanel panel) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				panel_content.setBounds(310, 30, 720, 730);
//				panel_menu.setPreferredSize(new Dimension(900,670));
//				panel_menu.revalidate();
//				panel_menu.repaint();
				resizePanels();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				panel.setCursor(cursor);
				panel.setBackground(Color.green);

			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				panel.setCursor(cursor);
				panel.setBackground(null);

			}

		};
	}
	
    protected void resizePanels() {
        contentPane.remove(panel_content); // Remove the old panel

        panel_content = new RoundedPanel(50, new Color(249, 230, 186));
        panel_content.setBounds(310, 30, 720, 730);
        contentPane.add(panel_content);
        panel_content.setLayout(null);

        panel_menu = new JPanel();
        panel_menu.setBackground(new Color(249, 230, 186));
        int distant = 10;
        panel_menu.setPreferredSize(new Dimension(panel_content.getWidth() - 2 * distant, 2000));
        panel_menu.setLayout(null);

        JScrollPane scrollPanepanel_menu = new JScrollPane(panel_menu);
        scrollPanepanel_menu.setBounds(10, 30, panel_menu.getPreferredSize().width, 670);
        scrollPanepanel_menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanepanel_menu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel_content.add(scrollPanepanel_menu);
        
		List<Item> list = Item_BLL.getInstance().getAllItem();
		displayItems(list);
        
        contentPane.revalidate(); // Revalidate the content pane to apply changes
        contentPane.repaint(); // Repaint the content pane to apply changes
    }
}
