package VIEW;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BLL.Category_BLL;
import BLL.Employee_BLL;
import BLL.Item_BLL;
import BLL.Timekeeping_BLL;
import BLL.Workschedule_BLL;
import DTO.Category;
import DTO.Employee;
import DTO.Item;
import DTO.Itemplus;
import DTO.Timekeeping;
//import DTO.Itemplus;
import EDIT.RoundedPanel;

public class Menu_VIEW extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_tiennhan;
	private JButton button_pay;
	private JButton button_huy;

	private List<Item> itemList;
	private List<Itemplus> orderList;
	private JLabel label_name;
	private JPanel panel_menu;
	private JPanel panel_order;
	private JLabel label_total;

	private JPopupMenu menu_employee;
	private JMenuItem menuitem_info;
	private JMenuItem menuitem_logout;
	private JLabel label_avatar;
	private JTextField tf_search;
	private JPanel panel_suggestion;
	private Timekeeping timekeeping;

	private static String employeeid;

	public void GUI() {

//		SetTimekeeping();
		this.orderList = new ArrayList<Itemplus>();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Set frame to full screen
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Get screen dimensions
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);

		contentPane = new RoundedPanel(40, new Color(254, 215, 124));
		contentPane.setBackground(new Color(254, 215, 124));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);

		JComboBox<String> comboBox_type = new JComboBox<>();
		comboBox_type.setBounds(30, 20, 120, 30);
		comboBox_type.addItem("Tất cả");
		for (Category category : Category_BLL.getInstance().getAllCategory()) {
			comboBox_type.addItem(category.toString());
		}
		comboBox_type.addItem("Combo");
		comboBox_type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//                JComboBox<String> comboBox = ((JComboBox<String>)e.getSource());
				String selectedType = (String) comboBox_type.getSelectedItem();

				switch (selectedType) {
				case "Combo":
					displayItems(Item_BLL.getInstance().getAlCombotoItem());
					break;

				case "Tất cả":
					displayItems(itemList);
					break;

				default:
					displayItems(Item_BLL.getInstance().getAllByType(selectedType));
					break;
				}
			}
		});
		contentPane.add(comboBox_type);

		tf_search = new JTextField();
		tf_search.setBounds(600, 20, 200, 30);
		tf_search.setColumns(10);
		tf_search.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSearch();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSearch();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSearch();
			}

			private void updateSearch() {
				String nameitem = tf_search.getText();
				panel_suggestion.removeAll();

				if (nameitem.isEmpty()) {
					panel_suggestion.setVisible(false);
					return;
				}

				String type = comboBox_type.getSelectedItem().toString();
				switch (type) {
				case "Tất cả":
					List<Item> list = Item_BLL.getInstance().getItemByTypeandName("", nameitem);
					if (list.isEmpty()) {
						JLabel label = new JLabel("Không tìm tháy món ăn phù hợp");
						System.out.println("empty");
						panel_suggestion.add(label);
						panel_suggestion.setVisible(true);
					} else {
						for (Item item : list) {
							JLabel label = new JLabel();
							label.setText(item.getItemName());
							label.setPreferredSize(new Dimension(200, 30));
							label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							label.addMouseListener(new MouseAdapter() {

								@Override
								public void mouseClicked(MouseEvent e) {
									panel_suggestion.setVisible(false);
									Itemplus itemplus = new Itemplus(item, 1);
									Detail_VIEW detai_view = new Detail_VIEW(Menu_VIEW.this, itemplus);
									detai_view.setVisible(true);

								}

							});
							panel_suggestion.add(label);
						}
						panel_suggestion.setVisible(true);

					}
					panel_suggestion.revalidate();
					panel_suggestion.repaint();
					break;

				default:
					break;
				}
			}
		});
		contentPane.add(tf_search);

		panel_suggestion = new JPanel();
		panel_suggestion.setBounds(600, 50, 200, 140);
		panel_suggestion.setVisible(false);
		contentPane.add(panel_suggestion);

		panel_menu = new RoundedPanel(40, new Color(249, 230, 186));
//		panel_menu.setBackground(new Color(249, 230, 186));
		panel_menu.setPreferredSize(new Dimension(900, 1400));
		panel_menu.setLayout(null);

		// Create a JScrollPane and add the panel_menu to it
		JScrollPane scrollPane = new JScrollPane(panel_menu);
//        JScrollPane scrollPane = new raven.
		scrollPane.setBounds(30, 70, 900, 700);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Add the scroll pane to the content pane
		contentPane.add(scrollPane);

//		contentPane.add(panel_menu);

		panel_order = new JPanel();
		panel_order.setBackground(new Color(249, 230, 186));
		panel_order.setBounds(1030, 70, 450, 550);
		panel_order.setLayout(null);
		contentPane.add(panel_order);

		label_name = new JLabel("Văn Bá Phạm Tấn");
		label_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_name.setBounds(1200, 25, 120, 20);
		contentPane.add(label_name);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(249, 230, 186));
		panel.setBounds(1030, 620, 450, 150);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tổng tiền");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 10, 120, 30);
		panel.add(lblNewLabel);

		String s = "/IMAGE/iconnen2.png";
		button_pay = new JButton("Thanh toán");
		button_pay.setHorizontalTextPosition(SwingConstants.RIGHT);
		button_pay.setBounds(30, 90, 140, 40);
		button_pay.setBackground(new Color(0, 255, 0));
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(Menu_VIEW.class.getResource(s)); // Thay test bằng tên class của bạn nếu cần
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (icon != null && icon.getIconWidth() != -1) {

			Image image = icon.getImage(); // Lấy đối tượng Image từ ImageIcon
			Image scaledImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh
			ImageIcon scaledIcon = new ImageIcon(scaledImage); // Tạo ImageIcon mới từ ảnh đã thay đổi kích thước

			button_pay.setIcon(scaledIcon);
		} else {
			System.out.println("Image not found or unable to load.");
		}

		button_pay.setEnabled(false);
		button_pay.addActionListener(this);
		panel.add(button_pay);

		button_huy = new JButton("Hủy");
		button_huy.setBounds(290, 90, 140, 40);
		button_huy.setBackground(new Color(255, 0, 0));
		button_huy.setEnabled(false);
		button_huy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				orderList.clear();
				displayitemplus(orderList, 10, 5);
				label_total.setText("");
				tf_tiennhan.setText("");
			}
		});
		panel.add(button_huy);

		JLabel lblTinNhn = new JLabel("Tiền nhận");
		lblTinNhn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTinNhn.setBounds(10, 50, 120, 30);
		panel.add(lblTinNhn);

		label_total = new JLabel();
		label_total.setBounds(260, 10, 150, 30);
		panel.add(label_total);

		tf_tiennhan = new JTextField();
		tf_tiennhan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_tiennhan.setBounds(260, 50, 150, 30);
		tf_tiennhan.addActionListener(this);
		panel.add(tf_tiennhan);
		tf_tiennhan.setColumns(10);
		tf_tiennhan.setEditable(false);

		this.setVisible(true);
	}

	private void SetTimekeeping() {
		timekeeping = new Timekeeping();

		LocalDate currentDate = LocalDate.now();
		String date = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		String schedule = Workschedule_BLL.getInstance().getWorkScheduleByEmployeeAndDate(date, employeeid);

		String startTimeString = schedule.split(" - ")[0];
		LocalTime startTime = LocalTime.parse(startTimeString, DateTimeFormatter.ofPattern("HH:mm"));

		String inTimeString = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
		LocalTime inTime = LocalTime.parse(inTimeString, DateTimeFormatter.ofPattern("HH:mm"));

		float lateHours = 0;
		if (inTime.isAfter(startTime)) {
			Duration duration = Duration.between(startTime, inTime);
			lateHours = duration.toMinutes() / 60.0f; // Chuyển đổi từ phút sang giờ
		}

		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());

		timekeeping.setDay(date);
		timekeeping.setIn_time(inTimeString);
		timekeeping.setEmployeeID(employeeid);
		timekeeping.setWeek(weekNumber);
		timekeeping.setLate(lateHours >= 0.1f ? lateHours : 0.0f);
	}

	// create panel for product
	private JPanel createItemPanel(Item item, int x, int y) {
		JPanel panel = new JPanel();
		panel.setBounds(x, y, 160, 180);
		panel.addMouseListener(createMouseListener(panel, item));
		panel.addMouseMotionListener(createMouseMotionListener(panel));
		panel.setLayout(null);

		JLabel label_name = new JLabel(item.getItemName());
		label_name.setBounds(10, 80, 150, 30);
		panel.add(label_name);

		JLabel label_price = new JLabel(item.getPrice() + " đ");
		label_price.setForeground(new Color(255, 0, 0));
		label_price.setBounds(10, 150, 150, 30);
		panel.add(label_price);

		JLabel label_image = new JLabel();
		label_image.setBounds(0, 0, panel.getWidth(), 80);
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

		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.putClientProperty("item", item);

		return panel;
	}

	private void displayItems(List<Item> list) {
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
			if (x + panel.getWidth() * 2 + 10 > 950) {
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

	private JPanel createItemplusPanel(Itemplus itemplus, int x, int y) {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0));
		panel.setBounds(x, y, 430, 80);
		panel.setLayout(null);

		JLabel label_image = new JLabel();
		label_image.setBounds(0, 0, 120, 80);
		String s = itemplus.getImagePath();
		ImageIcon icon = new ImageIcon(getClass().getResource(s));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(120, 80, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label_image.setIcon(scaledIcon);
		panel.add(label_image);

		JLabel label_name = new JLabel();
		label_name.setForeground(new Color(255, 255, 255));
		label_name.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_name.setBounds(130, 0, 240, 30);
		label_name.setText(itemplus.getItemName());
		panel.add(label_name);

		JLabel label_delete = new JLabel();
		label_delete.setBounds(370, 0, 60, 80);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/IMAGE/bin.png"));
		Image deleteImage = deleteIcon.getImage();
		Image scaledDeleteImage = deleteImage.getScaledInstance(30, 40, Image.SCALE_SMOOTH);
		ImageIcon scaledDeleteIcon = new ImageIcon(scaledDeleteImage);
		label_delete.setIcon(scaledDeleteIcon);
		// Center the image in label_delete
		label_delete.setHorizontalAlignment(SwingConstants.CENTER);
		label_delete.setVerticalAlignment(SwingConstants.CENTER);

		label_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Itemplus clickitemplus = (Itemplus) panel.getClientProperty("itemplus");
				orderList.remove(clickitemplus);
				displayitemplus(orderList, 10, 5);
			}
		});

		panel.add(label_delete);

		JLabel label_price = new JLabel("Tổng tiền");
		label_price.setForeground(new Color(255, 255, 0));
		label_price.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_price.setBounds(130, 50, 160, 30);
		panel.add(label_price);

		JLabel label_quantity = new JLabel();
		label_quantity.setForeground(new Color(255, 255, 255));
		label_quantity.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_quantity.setBounds(300, 50, 50, 30);
		label_quantity.setText(itemplus.getQuantity() + "");
		panel.add(label_quantity);

		panel.putClientProperty("itemplus", itemplus);
		return panel;
	}

	public void detailtomenu(Itemplus itemplus) {

		boolean trung = false;
		for (Itemplus ip : this.orderList) {
			if (ip.getItemID().compareTo(itemplus.getItemID()) == 0) {
				ip.setQuantity(ip.getQuantity() + itemplus.getQuantity());
				trung = true;
				break;
			}
		}
//		panel_order.removeAll();
		int x = 10, y = 5;
		if (!trung) {
			this.orderList.add(itemplus);
		}
		displayitemplus(orderList, x, y);
	}

	private void displayitemplus(List<Itemplus> orderList, int x, int y) {
		panel_order.removeAll();
		int total = 0;
		for (Itemplus itemplus : orderList) {
			JPanel panel = createItemplusPanel(itemplus, x, y);
			total += itemplus.getTotal();
			panel_order.add(panel);
			y += 90;
		}
		label_total.setText(((total == 0) ? "" : String.valueOf(total)));
		tf_tiennhan.setText(((total == 0) ? "" : String.valueOf(total)));

		panel_order.revalidate();
		panel_order.repaint();
		button_pay.setEnabled(!orderList.isEmpty());
		button_huy.setEnabled(!orderList.isEmpty());
		tf_tiennhan.setEditable(!orderList.isEmpty());
	}

	private MouseAdapter createMouseListener(JPanel panel, Item item) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Retrieve item from panel's client property
				Item clickedItem = (Item) panel.getClientProperty("item");
				if (clickedItem != null) {
					Itemplus itemplus = new Itemplus(clickedItem, 1);

					Detail_VIEW d = new Detail_VIEW(Menu_VIEW.this, itemplus);

					// Set the position of Detail_VIEW based on the mouse click location
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					d.setLocation(x, y);

					d.setVisible(true);
				}
			}
		};
	}

	private MouseAdapter createMouseMotionListener(JPanel panel) {
		return new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				panel.setCursor(cursor);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				panel.setCursor(cursor);
			}
		};
	}

	public void clickavatar(String imagepath) {
		menu_employee = new JPopupMenu();
		menuitem_info = new JMenuItem("Thông tin tài khoản");
		menuitem_logout = new JMenuItem("Đăng xuất");
		menu_employee.add(menuitem_info);
		menu_employee.add(menuitem_logout);

		menuitem_info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Open the Employee_VIEW frame
				Employee_VIEW view = new Employee_VIEW(Menu_VIEW.employeeid);
				view.setVisible(true);
			}
		});

		menuitem_logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LocalTime currentTime = LocalTime.now();
				String outTimeString = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));

				String inTimeString = timekeeping.getIn_time();
				LocalTime inTime = LocalTime.parse(inTimeString, DateTimeFormatter.ofPattern("HH:mm"));
				
				float workingHour = 0;
		        if (currentTime.isAfter(inTime)) {
		            Duration duration = Duration.between(inTime, currentTime);
		            workingHour = duration.toMinutes() / 60.0f;  // Chuyển đổi từ phút sang giờ
		        }
		        timekeeping.setWorking_hour(workingHour);
		        timekeeping.setOut_time(outTimeString);
		        Timekeeping_BLL.getInstance().addTimekeepingRecord(timekeeping);
				dispose();
			}
		});

		label_avatar = new JLabel();
		label_avatar.setBounds(1350, 5, 55, 55);
		ImageIcon icon = new ImageIcon(getClass().getResource(imagepath));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(label_avatar.getWidth(), label_avatar.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label_avatar.setIcon(scaledIcon);

		label_avatar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menu_employee.show(e.getComponent(), e.getX() + 10, e.getY());
			}
		});
		contentPane.add(label_avatar);
	}

	public Menu_VIEW(String employeeid) {
		Menu_VIEW.employeeid = employeeid;
		GUI();
		Employee employee = Employee_BLL.getInstance().getEmployeeByID(employeeid);
		label_name.setText(employee.getFullname());
		clickavatar(employee.getImage());
		this.itemList = Item_BLL.getInstance().getAllItem();
		this.itemList.addAll(Item_BLL.getInstance().getAlCombotoItem());
		displayItems(itemList);
	}

	public static void main(String args[]) {
		new Menu_VIEW(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_pay || e.getSource() == tf_tiennhan) {
			String tienNhanText = tf_tiennhan.getText();
			int tienNhan = 0;

			// Check if the input is not empty
			if (tienNhanText.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Chưa nhập tiền nhận.", "Input Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				// Parse the input to a integer
				tienNhan = Integer.parseInt(tienNhanText);
			} catch (NumberFormatException ex) {
				// Handle invalid number format
				JOptionPane.showMessageDialog(null, "Nhập dữ liệu không hợp lệ.", "Input Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Check if the parsed number is positive
			int get = Integer.parseInt(label_total.getText());
			if (tienNhan < get) {
				JOptionPane.showMessageDialog(null, "Tiền nhận không nhỏ hơn tổng tiền .", "Input Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// If all checks pass, proceed with creating the Order_VIEW
			Order_VIEW order = new Order_VIEW(orderList, get, tienNhan, employeeid);
			order.setVisible(true);
		}
	}

}
