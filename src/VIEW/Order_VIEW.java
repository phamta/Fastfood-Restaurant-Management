 package VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import BLL.Bonusitem_BLL;
import BLL.Customer_BLL;
import BLL.Order_BLL;
import BLL.Orderdetail_BLL;
import DTO.Bonusitem;
import DTO.Customer;
import DTO.Itemplus;
import DTO.Order;

public class Order_VIEW extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_order;
	private JLabel label_tongtien;
	private JLabel label_tiennhan;
	private JLabel label_tienthua;
	private JPanel suggestionPanel;
	private JTextField tf_searchcustomer;
	private JPanel panel_customer;
	private JButton button_exchangepoint;
	private JButton buttonCancel;
	private JLabel label_name;
	private JLabel label_phone;
	private JLabel label_point;

	private JPanel panel_addcustomer;
	private JTextField tf_addcustomername;
	private JTextField tf_addcustomerphone;
	
	private static List<Itemplus> orderList;
	private static int total;
	private static int get;
	private static String employeeid;
	private Customer customer = null;
	private Bonusitem bonusitem = null;
	private JPanel panel_exchangepoint;
	private JScrollPane scrollPane_exchangepoint;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Order_VIEW(orderList, total, get, employeeid);
	}

	public Order_VIEW(List<Itemplus> orderList, int total, int get, String employeeid) {
		Order_VIEW.orderList = orderList;
		Order_VIEW.get = get;
		Order_VIEW.total = total;
		Order_VIEW.employeeid = employeeid;
		GUI();
		displayorder(orderList);
		displaypay(total, get);
	}

	public void GUI() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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

		panel_order = new JPanel();
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

		label_tongtien = new JLabel("");
		label_tongtien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_tongtien.setBounds(360, 550, 80, 20);
		panel_order.add(label_tongtien);

		label_tiennhan = new JLabel("");
		label_tiennhan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_tiennhan.setBounds(360, 580, 80, 20);
		panel_order.add(label_tiennhan);

		label_tienthua = new JLabel("");
		label_tienthua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_tienthua.setBounds(360, 610, 80, 20);
		panel_order.add(label_tienthua);
		
		panel_exchangepoint = new JPanel();
		panel_exchangepoint.setBackground(new Color(249, 230, 186));
		panel_exchangepoint.setLayout(null);
		panel_exchangepoint.setVisible(false);
		panel_exchangepoint.setPreferredSize(new Dimension(800, 2000));
		contentPane.add(panel_exchangepoint);
		
		scrollPane_exchangepoint = new JScrollPane(panel_exchangepoint);
		scrollPane_exchangepoint.setBounds(80, 90, 800, 650);
        scrollPane_exchangepoint.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_exchangepoint.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane_exchangepoint);
		List<Bonusitem> list = Bonusitem_BLL.getIstance().getAllBonusitem();
		displayPanelBonusitem(list);

		tf_searchcustomer = new JTextField();
		tf_searchcustomer.setBounds(1000, 90, 300, 30);
		
		tf_searchcustomer.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			      
			      updatesearch();
			  }
			  public void removeUpdate(DocumentEvent e) {
			      
			      updatesearch();
			  }
			  public void insertUpdate(DocumentEvent e) {
			      
			      updatesearch();
			  }
			});
		contentPane.add(tf_searchcustomer);
		tf_searchcustomer.setColumns(10);
		
		panel_addcustomer = new JPanel();
		panel_addcustomer.setBounds(1000, 120, 300, 150);
		contentPane.add(panel_addcustomer);
		panel_addcustomer.setLayout(null);
		panel_addcustomer.setVisible(false);
		displayaddcustomer();
		
        JButton button_addcustomer = new JButton("Thêm");
        button_addcustomer.setFont(new Font("Tahoma", Font.PLAIN, 16));
        button_addcustomer.setBounds(1350, 90, 100, 30);
        contentPane.add(button_addcustomer);
        button_addcustomer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panel_addcustomer.setVisible(true);
        	}
        });
        

		// Panel để hiển thị gợi ý
		suggestionPanel = new JPanel();
//        suggestionPanel.setLayout(new BoxLayout(suggestionPanel, BoxLayout.Y_AXIS));
		suggestionPanel.setBounds(1000, 120, 300, 150);
		suggestionPanel.setVisible(false); // Ẩn panel khi không có gợi ý
		contentPane.add(suggestionPanel);

		panel_customer = new JPanel();
		panel_customer.setBounds(1000, 150, 300, 210);
//		suggestionPanel.add(panel_customer);
		panel_customer.setLayout(null);
		panel_customer.setVisible(false);
		contentPane.add(panel_customer);
		
        JButton button_OK = new JButton("OK");
        button_OK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Order order = new Order();
        		
                // Get time when event happen
                LocalDateTime now = LocalDateTime.now();

                // format time
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // change to string
                String datetime = now.format(formatter);        		
                order.setDatetime(datetime);
        		
                Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
                SimpleDateFormat temp = new SimpleDateFormat("ddMMyyyyHHmmss");
                String formattedDate = temp.format(date);
                
                String orderid = "ORD" + employeeid.substring(3) + formattedDate;
                order.setOrderID(orderid);
                
                order.setTotal(total);
                order.setTake(get);
                order.setReturnmoney(get - total);
                order.setEmployeeID(employeeid);
                if(customer != null) {
                	order.setCustomerID(customer.getCustomerID());
                	customer.setPoint(customer.getPoint() + 1);
                	if(bonusitem != null) {
                		customer.setPoint(customer.getPoint() - bonusitem.getPoint());
                		order.setBonusitemID(bonusitem.getItemID());
                	}
                	Customer_BLL.getInstance().updatePoint(customer);
                }
                
        		Order_BLL.getInstance().AddOrder(order);
        		Orderdetail_BLL.getInstance().AddOrderdetail(orderid, orderList);
        		
        		JOptionPane.showMessageDialog(null, "Hoàn thành đơn hàng");
        		dispose();
        	}
        });
        button_OK.setBounds(1000, 700, 85, 30);
        contentPane.add(button_OK);
        
//        JButton button_huy = new JButton("Hủy");
//        button_huy.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		dispose();
////        		orderList.clear();
//        	}
//        });
//        button_huy.setBounds(1120, 700, 85, 30);
//        contentPane.add(button_huy);
        
        JButton button_return = new JButton("Quay lại");
        button_return.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        button_return.setBounds(1240, 700, 85, 30);
        contentPane.add(button_return);
	}

	private void displayorder(List<Itemplus> orderList) {
		int x = 50, y = 130;
		for (Itemplus itemplus : orderList) {
			JLabel label_nameitem = new JLabel(itemplus.getItemName());
			label_nameitem.setFont(new Font("Tahoma", Font.PLAIN, 16));
			label_nameitem.setBounds(x, y, 280, 20);
			panel_order.add(label_nameitem);

			JLabel label_price = new JLabel(itemplus.getPrice() + "");
			label_price.setFont(new Font("Tahoma", Font.PLAIN, 16));
			label_price.setBounds(380, y, 80, 20);
			label_price.setHorizontalAlignment(SwingConstants.CENTER);
			label_price.setVerticalAlignment(SwingConstants.CENTER);
			panel_order.add(label_price);

			JLabel label_quantity = new JLabel("" + itemplus.getQuantity());
			label_quantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
			label_quantity.setBounds(530, y, 80, 20);
			label_quantity.setHorizontalAlignment(SwingConstants.CENTER);
			label_quantity.setVerticalAlignment(SwingConstants.CENTER);
			panel_order.add(label_quantity);

			JLabel label_total = new JLabel("" + itemplus.getTotal());
			label_total.setFont(new Font("Tahoma", Font.PLAIN, 16));
			label_total.setBounds(670, y, 80, 20);
			label_total.setHorizontalAlignment(SwingConstants.CENTER);
			label_total.setVerticalAlignment(SwingConstants.CENTER);
			panel_order.add(label_total);

			y += 30;
		}
		
		if(bonusitem != null) {
			JLabel label_bonusitem = new JLabel("Đổi điểm: " + bonusitem.getItemName());
			label_bonusitem.setBounds(x, y, 500, 20);
			label_bonusitem.setFont(new Font("Tahoma", Font.PLAIN, 16));
			panel_order.add(label_bonusitem);
		}
	}

	private void displaypay(int total, int get) {
		int give = get - total;
		label_tongtien.setText("" + total);
		label_tiennhan.setText("" + get);
		label_tienthua.setText("" + give);
	}
	
	private void displaycustomer(Customer customer) {
		Order_VIEW.this.customer = customer;
		
		JLabel lblNewLabel_8 = new JLabel("Tên:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(30, 25, 40, 20);
		panel_customer.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Số điện thoại:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(30, 60, 105, 20);
		panel_customer.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Số điểm:");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_10.setBounds(30, 95, 70, 20);
		panel_customer.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Dùng điểm");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(30, 130, 90, 20);
		panel_customer.add(lblNewLabel_11);

		button_exchangepoint = new JButton("OK");
		button_exchangepoint.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_exchangepoint.setBounds(40, 165, 70, 30);
		button_exchangepoint.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panel_order.setVisible(false);
        		scrollPane_exchangepoint.setVisible(true);
        	}
        });
		panel_customer.add(button_exchangepoint);

		buttonCancel = new JButton("Cancel");
		buttonCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonCancel.setBounds(140, 165, 70, 30);
		buttonCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		panel_order.setVisible(true);
        		scrollPane_exchangepoint.setVisible(false);
        	}
        });
		panel_customer.add(buttonCancel);

		label_name = new JLabel("" + customer.getFullname());
		label_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_name.setBounds(70, 25, 230, 20);
		panel_customer.add(label_name);

		label_phone = new JLabel("" + customer.getPhone_number());
		label_phone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_phone.setBounds(135, 60, 100, 20);
		panel_customer.add(label_phone);

		label_point = new JLabel("" + customer.getPoint());
		label_point.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_point.setBounds(100, 95, 100, 20);
		panel_customer.add(label_point);
		
	    label_name.setText(customer.getFullname());

	    label_phone.setText(customer.getPhone_number());

	    label_point.setText(String.valueOf(customer.getPoint()));
	}
	
	private void displayaddcustomer() {
        JLabel lblNewLabel_8_1 = new JLabel("Tên:");
        lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_8_1.setBounds(30, 20, 40, 20);
        panel_addcustomer.add(lblNewLabel_8_1);
        
        JLabel lblNewLabel_9_1 = new JLabel("Số điện thoại:");
        lblNewLabel_9_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_9_1.setBounds(30, 60, 105, 20);
        panel_addcustomer.add(lblNewLabel_9_1);
        
        tf_addcustomername = new JTextField();
        tf_addcustomername.setBounds(80, 20, 180, 25);
        panel_addcustomer.add(tf_addcustomername);
        tf_addcustomername.setColumns(10);
        
        tf_addcustomerphone = new JTextField();
        tf_addcustomerphone.setBounds(140, 60, 120, 25);
        panel_addcustomer.add(tf_addcustomerphone);
        tf_addcustomerphone.setColumns(10);
        
        JButton button_addcustomerok = new JButton("Hoàn tất");
        button_addcustomerok.setFont(new Font("Tahoma", Font.PLAIN, 16));
        button_addcustomerok.setBounds(95, 100, 100, 30);
        panel_addcustomer.add(button_addcustomerok);
        button_addcustomerok.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String id = String.format("CUS%03d", Customer_BLL.getInstance().getAllCustomer().size() + 1);
        		String name = tf_addcustomername.getText();
        		String phone_number = tf_addcustomerphone.getText();
        		Customer customer = new Customer(id, phone_number, name, 0);
        		panel_addcustomer.setVisible(false);
        		panel_customer.setVisible(true);
        		displaycustomer(customer);
        		Customer_BLL.getInstance().addCustomer(customer);
        	}
        });
	}

	public void updatesearch() {
		String phone_number = tf_searchcustomer.getText();
		suggestionPanel.removeAll();
		panel_customer.removeAll();
		panel_customer.setVisible(false);

		if (phone_number.isEmpty()) {
			suggestionPanel.setVisible(false);
			return;
		}

		List<Customer> list = Customer_BLL.getInstance().SearchCustomerIntrie(phone_number);
		if (list.isEmpty()) {
			JLabel label = new JLabel("Khong tim thay khach hang phu hop");
			System.out.println("empty");
			suggestionPanel.add(label);
			suggestionPanel.setVisible(true);
		} else {
			for (Customer customer : list) {
				JLabel label = new JLabel();
				label.setText(customer.toString());
//        		System.out.println(customer.toString());
//        		label.setBounds(x, y, 300, 30);
				label.setPreferredSize(new Dimension(300, 30));
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						suggestionPanel.setVisible(false);
						panel_customer.setVisible(true);
						displaycustomer(customer);
//						System.out.println(customer.toString());
						
					}

				});
				suggestionPanel.add(label);
			}
			suggestionPanel.setVisible(true);
		}
		suggestionPanel.revalidate();
		suggestionPanel.repaint();
	}
	
	private JPanel createPanelBonusitem(Bonusitem bonusitem, int x, int y) {
		JPanel panel = new JPanel();
		panel.setBounds(x, y, 155, 185);
//		panel.addMouseListener(createMouseListener(panel, item));
//		panel.addMouseMotionListener(createMouseMotionListener(panel));
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(bonusitem.getPoint() > customer.getPoint()) {
					JOptionPane.showMessageDialog(null, "Điểm không đủ để đổi món này");
				}
				else {
					Order_VIEW.this.bonusitem = bonusitem;
					panel_exchangepoint.setVisible(false);
					panel_order.setVisible(true);
					displayorder(orderList);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		panel.setBackground(null);

		panel.setLayout(null);

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
		System.out.println(list.size());
		panel_exchangepoint.removeAll();
		int x = 10, y = 10;
		int maxHeight = 70; // Để lưu chiều cao lớn nhất của panel
		for (Bonusitem bonusitem : list) {
			JPanel panel = createPanelBonusitem(bonusitem, x, y);
			panel_exchangepoint.add(panel);

			int panelHeight = panel.getPreferredSize().height;
			if (panelHeight > maxHeight) {
				maxHeight = panelHeight;
			}
			
			if (x + panel.getWidth() * 2 + 10 > panel_exchangepoint.getPreferredSize().width) {
				y += panel.getHeight() + 10; // Y tăng lên theo chiều cao lớn nhất của panel trong hàng
				x = 10;
				maxHeight = 70; // Reset chiều cao lớn nhất
			} else {
				x += panel.getWidth() + 10;
			}
		}
		
		panel_exchangepoint.revalidate();
		panel_exchangepoint.repaint();
	}

}
