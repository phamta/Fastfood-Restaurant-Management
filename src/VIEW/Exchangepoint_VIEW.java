package VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.Bonusitem_BLL;
import DTO.Bonusitem;
import EDIT.RoundedPanel;

public class Exchangepoint_VIEW extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panel_menu;
	private JScrollPane scrollPanepanel_menu;

	public Exchangepoint_VIEW(int radius, Color backgroundColor) {
		super(radius, backgroundColor);
		setBounds(0, 0, 1190, 730);
		setLayout(null);

		panel_menu = new JPanel();
		panel_menu.setBackground(new Color(249, 230, 186));
		panel_menu.setLayout(null);
		this.add(panel_menu);

		scrollPanepanel_menu = new JScrollPane(panel_menu);
		scrollPanepanel_menu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanepanel_menu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPanepanel_menu);
		
		int padding_side = 10;
//		panel_1.setBounds(10, 30, 1170, 670);
		panel_menu.setPreferredSize(new Dimension(this.getWidth() - 2 * padding_side, 1500));
		scrollPanepanel_menu.setBounds(10, 30, panel_menu.getPreferredSize().width, 670);
		
		List<Bonusitem> list = Bonusitem_BLL.getIstance().getAllBonusitem();
		displayPanelBonusitem(list);
		
		this.revalidate(); // Revalidate the content pane to apply changes
		this.repaint(); // Repaint the content pane to apply changes
		
	}
	
	
	private JPanel createPanelBonusitem(Bonusitem bonusitem, int x, int y) {
		JPanel panel = new JPanel();
		panel.setBounds(x, y, 155, 185);
//		panel.addMouseListener(createMouseListener(panel, item));
//		panel.addMouseMotionListener(createMouseMotionListener(panel));
		if(bonusitem.getStatus() == 1) {
			panel.setBackground(null);
		}
		else {
			panel.setBackground(Color.gray);
		}

		panel.setLayout(null);
		
	    // Create the popup menu
	    JPopupMenu popupMenu = new JPopupMenu();
	    JMenuItem menuItem = new JMenuItem(bonusitem.getStatus() == 1 ? "Xóa" : "Thêm");
	    popupMenu.add(menuItem);

	    // Add action listener to the menu item
	    menuItem.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (bonusitem.getStatus() == 1) {
	                bonusitem.setStatus(0);
	                panel.setBackground(Color.gray);
	                
	                menuItem.setText("Thêm");
	            } else {
	                bonusitem.setStatus(1);
	                panel.setBackground(null);
	                menuItem.setText("Xóa");
	            }
	        }
	    });

	    // Add mouse listener to the panel for showing the popup menu
	    panel.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if (e.isPopupTrigger()) {
	                showPopup(e);
	            }
	        }

	        @Override
	        public void mouseReleased(MouseEvent e) {
	            if (e.isPopupTrigger()) {
	                showPopup(e);
	            }
	        }

	        private void showPopup(MouseEvent e) {
	            popupMenu.show(e.getComponent(), e.getX(), e.getY());
	        }
	    });

		int letter_seize = 18;

		JLabel label_image = new JLabel();
		label_image.setBounds(0, 0, panel.getWidth(), 85);
		label_image.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		String s = bonusitem.getImagePath();
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

		JLabel label_name = new JLabel(bonusitem.getItemName());
		label_name.setBounds(5, label_image.getHeight(), panel.getWidth() - 5, 40);
		label_name.setFont(new Font("Tahoma", Font.PLAIN, letter_seize));
		panel.add(label_name);
		
		JLabel lblNewLabel_3 = new JLabel("Điểm");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(5, 115, 45, 35);
		panel.add(lblNewLabel_3);
		
		JTextField textField = new JTextField("" + bonusitem.getPoint());
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setText("10");
		textField.setBounds(50, 115, 100, 35);
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
		
	    // Add focus listener to the text field to update the bonus item when editing is done
	    textField.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusLost(FocusEvent e) {
	            try {
	                int newPoint = Integer.parseInt(textField.getText());
	                bonusitem.setPoint(newPoint);
	                Bonusitem_BLL.getIstance().updateBonusitem(bonusitem);
	                textField.setEditable(false);
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(panel, "Please enter a valid number for points.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
	                textField.setText("" + bonusitem.getPoint());
	            }
	        }
	    });

		JLabel label_price = new JLabel(bonusitem.getPrice() + " đ");
		label_price.setForeground(new Color(255, 0, 0));
		label_price.setBounds(5, panel.getHeight() - 30, panel.getWidth() - 5, 30);
		label_price.setForeground(new Color(255, 0, 0));
		label_price.setFont(new Font("Tahoma", Font.PLAIN, letter_seize));
		panel.add(label_price);

		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.putClientProperty("bonusitem", bonusitem);

		return panel;
	}
	
	private void displayPanelBonusitem(List<Bonusitem> list) {
		panel_menu.removeAll();
		int x = 10, y = 10;
		int maxHeight = 70; // Để lưu chiều cao lớn nhất của panel
		for (Bonusitem bonusitem : list) {
			JPanel panel = createPanelBonusitem(bonusitem, x, y);
			panel_menu.add(panel);

			int panelHeight = panel.getPreferredSize().height;
			if (panelHeight > maxHeight) {
				maxHeight = panelHeight;
			}
			
			if (x + panel.getWidth() * 2 + 10 > panel_menu.getPreferredSize().width) {
				y += panel.getHeight() + 10; // Y tăng lên theo chiều cao lớn nhất của panel trong hàng
				x = 10;
				maxHeight = 70; // Reset chiều cao lớn nhất
			} else {
				x += panel.getWidth() + 10;
			}
		}
		
		panel_menu.revalidate();
		panel_menu.repaint();
	}

}
