package VIEW;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Account_BLL;
import DTO.Account;
import EDIT.RoundedPanel;

public class Management_VIEW extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// panel show interface management
	private JPanel panel_content;

	// panel choose which to management
	private JPanel panel_menubar;

	private JPanel panel_manaemp;
	private JPanel panel_manaitem;
	private JPanel panel_manacombo;
	private JPanel panel_manacategory;
	private JPanel panel_manaorder;
	private JPanel panel_manaschedule;
	private JPanel panel_manapayroll;
	private JPanel panel_manaaccount;
	private JPanel panel_manacustomer;
	private JPanel panel_manaexchangepoint;
	private JPanel panel_manatimekeeping;

	private JPanel panel_choice = null;

	private ManaEmployee_VIEW manaEmployee_VIEW;
	private Item_VIEW item_VIEW;
	private Combo_VIEW combo_VIEW;
	private Category_VIEW categoryView;
	private Bill_VIEW bill_VIEW;
	private Workschedule_VIEW workschedule_VIEW;
	private Payroll_VIEW payroll_VIEW = new Payroll_VIEW(50, new Color(254, 215, 124));
	private Account_VIEW account_VIEW = new Account_VIEW(50, new Color(254, 215, 124));
	private Customer_VIEW customer_VIEW = new Customer_VIEW(50, new Color(254, 215, 124));
	private Exchangepoint_VIEW exchangepoint_VIEW = new Exchangepoint_VIEW(50, new Color(249, 230, 186));
	private Timekeeping_VIEW timekeeping_VIEW = new Timekeeping_VIEW(50, new Color(254, 215, 124));
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Management_VIEW();
	}

	public Management_VIEW() {
		GUI();
	}

	private void GUI() {
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
		panel.setBackground(new Color(249, 230, 186));
		panel.setBounds(30, 30, 230, 75);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label_role = new JLabel("Vai trò: Quản lý");
		label_role.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_role.setBounds(10, 0, 220, 35);
		panel.add(label_role);
		
		JLabel label_name = new JLabel("Văn Bá Phạm Tấn");
		label_name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_name.setBounds(10, 40, 220, 35);
		panel.add(label_name);

		panel_menubar = new JPanel();
		panel_menubar.setBounds(30, 155, 230, 605);
		contentPane.add(panel_menubar);
		panel_menubar.setLayout(null);
		panel_menubar.setBackground(new Color(249, 230, 186));

		panel_manaemp = new JPanel();
		panel_manaemp.setBounds(0, 0, 230, 50);
		panel_manaemp.setBackground(new Color(249, 230, 186));
		panel_menubar.add(panel_manaemp);
		panel_manaemp.setLayout(null);
		panel_manaemp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(manaEmployee_VIEW == null) {
					manaEmployee_VIEW = new ManaEmployee_VIEW();
				}
				AddPanel(manaEmployee_VIEW);
				if (panel_choice != null) {
					panel_choice.setBackground(null); // Reset background color of previous panel
				}
				panel_manaemp.setBackground(Color.YELLOW); // Set background color to yellow when selected
				panel_choice = panel_manaemp; // Update the current panel
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				panel_manaemp.setCursor(cursor);
				if (panel_choice != panel_manaemp) {
					panel_manaemp.setBackground(Color.gray);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				panel_manaemp.setCursor(cursor);
				if (panel_choice != panel_manaemp) {
					panel_manaemp.setBackground(null);
				}
			}
		});

		JLabel label_menuimageemployee = new JLabel("");
		label_menuimageemployee.setBounds(0, 0, 60, 50);
		panel_manaemp.add(label_menuimageemployee);

		JLabel lblNewLabel = new JLabel("Nhân viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(60, 0, 170, 50);
		panel_manaemp.add(lblNewLabel);

		panel_content = new RoundedPanel(50, getBackground());
		panel_content.setBounds(310, 30, 1190, 730);
		contentPane.add(panel_content);
		panel_content.setLayout(null);

		panel_manaitem = new JPanel();
		panel_manaitem.setLayout(null);
		panel_manaitem.setBounds(0, 55, 230, 50);
		panel_menubar.add(panel_manaitem);
		panel_manaitem.setBackground(null);
//		panel_manaitem.addMouseListener(createMouseListener(panel_manaitem, item_VIEW));
		panel_manaitem.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if(item_VIEW  == null) {
		            item_VIEW = new Item_VIEW(50, new Color(249, 230, 186));
		        }
		        AddPanel(item_VIEW);
		        if (panel_choice != null) {
		            panel_choice.setBackground(null); // Reset background color of previous panel
		        }
		        panel_manaitem.setBackground(Color.YELLOW); // Set background color to yellow when selected
		        panel_choice = panel_manaitem; // Update the current panel
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		        panel_manaitem.setCursor(cursor);
		        if (panel_choice != panel_manaitem) {
		            panel_manaitem.setBackground(Color.gray);
		        }
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        Cursor cursor = Cursor.getDefaultCursor();
		        panel_manaitem.setCursor(cursor);
		        if (panel_choice != panel_manaitem) {
		            panel_manaitem.setBackground(null);
		        }
		    }
		});

		JLabel label_menuitemimage = new JLabel("");
		label_menuitemimage.setBounds(0, 0, 60, 50);
		panel_manaitem.add(label_menuitemimage);

		JLabel panel_manaitem_1 = new JLabel("Thực đơn");
		panel_manaitem_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_manaitem_1.setBounds(60, 0, 170, 50);
		panel_manaitem.add(panel_manaitem_1);

		panel_manacombo = new JPanel();
		panel_manacombo.setLayout(null);
		panel_manacombo.setBounds(0, 110, 230, 50);
		panel_menubar.add(panel_manacombo);
		panel_manacombo.setBackground(null);
		panel_manacombo.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if(combo_VIEW  == null) {
		            combo_VIEW = new Combo_VIEW();
		        }
		        AddPanel(combo_VIEW);
		        if (panel_choice != null) {
		            panel_choice.setBackground(null); // Reset background color of previous panel
		        }
		        panel_manacombo.setBackground(Color.YELLOW); // Set background color to yellow when selected
		        panel_choice = panel_manacombo; // Update the current panel
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		        panel_manacombo.setCursor(cursor);
		        if (panel_choice != panel_manacombo) {
		            panel_manacombo.setBackground(Color.gray);
		        }
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        Cursor cursor = Cursor.getDefaultCursor();
		        panel_manacombo.setCursor(cursor);
		        if (panel_choice != panel_manacombo) {
		            panel_manacombo.setBackground(null);
		        }
		    }
		});

		JLabel label_menuempimg_1 = new JLabel("");
		label_menuempimg_1.setBounds(0, 0, 60, 50);
		panel_manacombo.add(label_menuempimg_1);

		JLabel lblCombo = new JLabel("Combo");
		lblCombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCombo.setBounds(60, 0, 170, 50);
		panel_manacombo.add(lblCombo);

		panel_manacategory = new JPanel();
		panel_manacategory.setLayout(null);
		panel_manacategory.setBounds(0, 165, 230, 50);
		panel_manacategory.setBackground(null);
		panel_menubar.add(panel_manacategory);
		panel_manacategory.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if(categoryView  == null) {
		            categoryView = new Category_VIEW(50, new Color(254, 215, 124));
		        }
		        AddPanel(categoryView);
		        if (panel_choice != null) {
		            panel_choice.setBackground(null); // Reset background color of previous panel
		        }
		        panel_manacategory.setBackground(Color.YELLOW); // Set background color to yellow when selected
		        panel_choice = panel_manacategory; // Update the current panel
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		        panel_manacategory.setCursor(cursor);
		        if (panel_choice != panel_manacategory) {
		            panel_manacategory.setBackground(Color.gray);
		        }
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        Cursor cursor = Cursor.getDefaultCursor();
		        panel_manacategory.setCursor(cursor);
		        if (panel_choice != panel_manacategory) {
		            panel_manacategory.setBackground(null);
		        }
		    }
		});

		JLabel label_menuempimg_1_1 = new JLabel("");
		label_menuempimg_1_1.setBounds(0, 0, 60, 50);
		panel_manacategory.add(label_menuempimg_1_1);

		JLabel lblLoiSnPhm = new JLabel("Loại sản phẩm");
		lblLoiSnPhm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoiSnPhm.setBounds(60, 0, 170, 50);
		panel_manacategory.add(lblLoiSnPhm);
		
		panel_manaorder = new JPanel();
		panel_manaorder.setLayout(null);
		panel_manaorder.setBackground(new Color(249, 230, 186));
		panel_manaorder.setBounds(0, 220, 230, 50);
		panel_menubar.add(panel_manaorder);
		panel_manaorder.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if(bill_VIEW  == null) {
		            bill_VIEW = new Bill_VIEW(50, new Color(254, 215, 124));
		        }
		        AddPanel(bill_VIEW);
		        if (panel_choice != null) {
		            panel_choice.setBackground(null); // Reset background color of previous panel
		        }
		        panel_manaorder.setBackground(Color.YELLOW); // Set background color to yellow when selected
		        panel_choice = panel_manaorder; // Update the current panel
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		        panel_manaorder.setCursor(cursor);
		        if (panel_choice != panel_manaorder) {
		            panel_manaorder.setBackground(Color.gray);
		        }
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        Cursor cursor = Cursor.getDefaultCursor();
		        panel_manaorder.setCursor(cursor);
		        if (panel_choice != panel_manaorder) {
		            panel_manaorder.setBackground(null);
		        }
		    }
		});

		JLabel label_menuempimg_1_2 = new JLabel("");
		label_menuempimg_1_2.setBounds(0, 0, 60, 50);
		panel_manaorder.add(label_menuempimg_1_2);

		JLabel lblHan = new JLabel("Hóa đơn");
		lblHan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHan.setBounds(60, 0, 170, 50);
		panel_manaorder.add(lblHan);
		
//		JPanel panel_manasale = new JPanel();
//		panel_manasale.setLayout(null);
//		panel_manasale.setBounds(0, 250, 230, 50);
//		panel_menubar.add(panel_manasale);
//
//		JLabel label_menuempimg_1_3 = new JLabel("");
//		label_menuempimg_1_3.setBounds(0, 0, 60, 50);
//		panel_manasale.add(label_menuempimg_1_3);
//
//		JLabel lblDoanhThu = new JLabel("Doanh thu");
//		lblDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		lblDoanhThu.setBounds(60, 0, 170, 50);
//		panel_manasale.add(lblDoanhThu);
		
		panel_manaschedule = new JPanel();
		panel_manaschedule.setLayout(null);
		panel_manaschedule.setBackground(new Color(249, 230, 186));
		panel_manaschedule.setBounds(0, 275, 230, 50);
		panel_menubar.add(panel_manaschedule);
		panel_manaschedule.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if(workschedule_VIEW  == null) {
		            workschedule_VIEW = new Workschedule_VIEW(50, new Color(254, 215, 124));
		        }
		        AddPanel(workschedule_VIEW);
		        if (panel_choice != null) {
		            panel_choice.setBackground(null); // Reset background color of previous panel
		        }
		        panel_manaschedule.setBackground(Color.YELLOW); // Set background color to yellow when selected
		        panel_choice = panel_manaschedule; // Update the current panel
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		        Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		        panel_manaschedule.setCursor(cursor);
		        if (panel_choice != panel_manaschedule) {
		            panel_manaschedule.setBackground(Color.gray);
		        }
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        Cursor cursor = Cursor.getDefaultCursor();
		        panel_manaschedule.setCursor(cursor);
		        if (panel_choice != panel_manaschedule) {
		            panel_manaschedule.setBackground(null);
		        }
		    }
		});
		
		JLabel label_menuempimg_1_4 = new JLabel("");
		label_menuempimg_1_4.setBounds(0, 0, 60, 50);
		panel_manaschedule.add(label_menuempimg_1_4);
		
		JLabel lblPhnCng = new JLabel("Lịch làm");
		lblPhnCng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhnCng.setBounds(60, 0, 170, 50);
		panel_manaschedule.add(lblPhnCng);
		
		panel_manapayroll = new JPanel();
		panel_manapayroll.setLayout(null);
		panel_manapayroll.setBackground(new Color(249, 230, 186));
		panel_manapayroll.setBounds(0, 330, 230, 50);
		panel_manapayroll.addMouseListener(createMouseListener(panel_manapayroll, payroll_VIEW));
		panel_menubar.add(panel_manapayroll);
		
		JLabel label_menuempimg_1_5 = new JLabel("");
		label_menuempimg_1_5.setBounds(0, 0, 60, 50);
		panel_manapayroll.add(label_menuempimg_1_5);
		
		JLabel lblBngLng = new JLabel("Bảng lương");
		lblBngLng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBngLng.setBounds(60, 0, 170, 50);
		panel_manapayroll.add(lblBngLng);
		
		panel_manaaccount = new JPanel();
		panel_manaaccount.setLayout(null);
		panel_manaaccount.setBackground(new Color(249, 230, 186));
		panel_manaaccount.setBounds(0, 385, 230, 50);
		panel_manaaccount.addMouseListener(createMouseListener(panel_manaaccount, account_VIEW));
		panel_menubar.add(panel_manaaccount);

		JLabel label_menuempimg_1_6 = new JLabel("");
		label_menuempimg_1_6.setBounds(0, 0, 60, 50);
		panel_manaaccount.add(label_menuempimg_1_6);

		JLabel lblTiKhon = new JLabel("Tài khoản");
		lblTiKhon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTiKhon.setBounds(60, 0, 170, 50);
		panel_manaaccount.add(lblTiKhon);
		
		panel_manaexchangepoint = new JPanel();
		panel_manaexchangepoint.setLayout(null);
		panel_manaexchangepoint.setBackground(new Color(249, 230, 186));
		panel_manaexchangepoint.setBounds(0, 440, 230, 50);
		panel_manaexchangepoint.addMouseListener(createMouseListener(panel_manaexchangepoint, exchangepoint_VIEW));
		panel_menubar.add(panel_manaexchangepoint);
		
		JLabel label_menuempimg_1_7 = new JLabel("");
		label_menuempimg_1_7.setBounds(0, 0, 60, 50);
		panel_manaexchangepoint.add(label_menuempimg_1_7);
		
		JLabel lbliim = new JLabel("Đổi điểm");
		lbliim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbliim.setBounds(60, 0, 170, 50);
		panel_manaexchangepoint.add(lbliim);
		
		panel_manacustomer = new JPanel();
		panel_manacustomer.setLayout(null);
		panel_manacustomer.setBackground(new Color(249, 230, 186));
		panel_manacustomer.setBounds(0, 495, 230, 50);
		panel_manacustomer.addMouseListener(createMouseListener(panel_manacustomer, customer_VIEW));
		panel_menubar.add(panel_manacustomer);
		
		JLabel label_menuempimg_1_8 = new JLabel("");
		label_menuempimg_1_8.setBounds(0, 0, 60, 50);
		panel_manacustomer.add(label_menuempimg_1_8);
		
		JLabel lblKhchHng = new JLabel("Khách hàng");
		lblKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKhchHng.setBounds(60, 0, 170, 50);
		panel_manacustomer.add(lblKhchHng);
		
		panel_manatimekeeping = new JPanel();
		panel_manatimekeeping.setLayout(null);
		panel_manatimekeeping.setBackground(new Color(249, 230, 186));
		panel_manatimekeeping.setBounds(0, 550, 230, 50);
		panel_manatimekeeping.addMouseListener(createMouseListener(panel_manatimekeeping, timekeeping_VIEW));
		panel_menubar.add(panel_manatimekeeping);

		JLabel label_menuempimg_1_9 = new JLabel("");
		label_menuempimg_1_9.setBounds(0, 0, 60, 50);
		panel_manatimekeeping.add(label_menuempimg_1_9);

		JLabel lblCh = new JLabel("Chấm công");
		lblCh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCh.setBounds(60, 0, 170, 50);
		panel_manatimekeeping.add(lblCh);

		panel_manaemp.dispatchEvent(
				new MouseEvent(panel_manaemp, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false));
		setVisible(true);
	}

	private void AddPanel(JPanel panel) {
		panel_content.removeAll();
		panel_content.add(panel);
		panel_content.revalidate();
		panel_content.repaint();
	}

	private MouseAdapter createMouseListener(JPanel panel, JPanel panel_view) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddPanel(panel_view);
				if (panel_choice != null) {
					panel_choice.setBackground(null); // Reset background color of previous panel
				}
				panel.setBackground(Color.YELLOW); // Set background color to yellow when selected
				panel_choice = panel; // Update the current panel
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				panel.setCursor(cursor);
				if (panel_choice != panel) {
					panel.setBackground(Color.gray);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				panel.setCursor(cursor);
				if (panel_choice != panel) {
					panel.setBackground(null);
				}
			}

		};
	}

}
