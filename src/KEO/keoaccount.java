package KEO;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import BLL.Account_BLL;
import DTO.Account;
import EDIT.RoundedPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class keoaccount extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_menubar;
	private JPanel panel_content;
	private RoundedPanel panel_listaccount;
	private JTextField tf_search;
	private RoundedPanel panel_detailaccount;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel label_employeeid;
	private JLabel label_employeename;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
//	private JTextField tf_username;
//	private JTextField tf_password;
	private JTable table_account;
	private DefaultTableModel modeltable_account;
	private JLabel label_savechange;
	private JLabel lblNewLabel_5;
	private JRadioButton rdbutton_disable;
	private JRadioButton rdbutton_active;

	private String employeeid;
	private JTextField tf_username;
	private JTextField tf_password;
	private JRadioButton rdbutton_savestate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keoaccount frame = new keoaccount();
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
	public keoaccount() {
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

		panel_content = new JPanel();
		panel_content.setBackground(new Color(254, 215, 124));
		panel_content.setBounds(310, 30, 1190, 730);
		contentPane.add(panel_content);
		panel_content.setLayout(null);

		panel_listaccount = new RoundedPanel(50, new Color(249, 230, 186));
		panel_listaccount.setBounds(0, 0, 710, 730);
		panel_content.add(panel_listaccount);
		panel_listaccount.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tìm kiếm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(365, 10, 90, 30);
		panel_listaccount.add(lblNewLabel);

		tf_search = new JTextField();
		tf_search.setBounds(470, 15, 150, 25);
		panel_listaccount.add(tf_search);
		tf_search.setColumns(10);
		tf_search.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				searchAccount();
		}

		public void removeUpdate(DocumentEvent e) {
			searchAccount();
		}

		public void insertUpdate(DocumentEvent e) {
			searchAccount();
		}
	});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 75, 680, 620);
		panel_listaccount.add(scrollPane);

		table_account = new JTable();
		table_account.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Set selection mode to single selection
		scrollPane.setViewportView(table_account);
		table_account.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int selectedRow = table_account.getSelectedRow();
					if (selectedRow != -1) {
						employeeid = modeltable_account.getValueAt(selectedRow, 0).toString();
						showdetailAccount(employeeid);
					}
				}
			}
		});

		String[] columnNames = { "Mã nhân viên", "Họ và tên", "Username", "Password" };
		modeltable_account = new DefaultTableModel(columnNames, 0);

		panel_detailaccount = new RoundedPanel(50, new Color(249, 230, 186));
		panel_detailaccount.setBounds(730, 0, 460, 730);
		panel_content.add(panel_detailaccount);
		panel_detailaccount.setLayout(null);

		lblNewLabel_1 = new JLabel("Mã nhân viên:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 50, 130, 30);
		panel_detailaccount.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Tên nhân viên:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 100, 140, 30);
		panel_detailaccount.add(lblNewLabel_2);

		label_employeeid = new JLabel("");
		label_employeeid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_employeeid.setBounds(150, 50, 200, 30);
		panel_detailaccount.add(label_employeeid);

		label_employeename = new JLabel("");
		label_employeename.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_employeename.setBounds(150, 100, 310, 30);
		panel_detailaccount.add(label_employeename);

		lblNewLabel_3 = new JLabel("Username:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 150, 100, 30);
		panel_detailaccount.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Password:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(10, 200, 100, 30);
		panel_detailaccount.add(lblNewLabel_4);

		tf_username = new JTextField();
		tf_username.setBackground(new Color(249, 230, 186));
		tf_username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_username.setBounds(120, 150, 300, 30);
		tf_username.setColumns(10);
		tf_username.setBorder(null); // Remove the border
		panel_detailaccount.add(tf_username);
//		tf_username.getDocument().addDocumentListener(new DocumentListener() {
//			public void changedUpdate(DocumentEvent e) {
//				turnonSaveMode();
//			}
//
//			public void removeUpdate(DocumentEvent e) {
//				turnonSaveMode();
//			}
//
//			public void insertUpdate(DocumentEvent e) {
//				turnonSaveMode();
//			}
//		});
		
		tf_username.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Called when a key is typed
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Called when a key is pressed
            }

            @Override
            public void keyReleased(KeyEvent e) {
                turnonSaveMode();
            }
        });

		tf_password = new JTextField();
		tf_password.setBackground(new Color(249, 230, 186));
		tf_password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_password.setColumns(10);
		tf_password.setBounds(120, 200, 300, 30);
		tf_password.setBorder(null); // Remove the border
		panel_detailaccount.add(tf_password);
//		tf_password
		tf_password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Called when a key is typed
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Called when a key is pressed
            }

            @Override
            public void keyReleased(KeyEvent e) {
                turnonSaveMode();
            }
        });

		label_savechange = new JLabel("Lưu thay đổi");
		label_savechange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Account account = Account_BLL.getInstance().GetUserByEmployeeID(employeeid);
				account.setStatus(rdbutton_active.isSelected() ? 1 : 0);
				account.setUsername(tf_username.getText());
				account.setPassword(tf_password.getText());
				Account_BLL.getInstance().updateAccount(account);
				turnoffSaveMode();
				LoadAllAccount();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_savechange.setHorizontalAlignment(SwingConstants.CENTER);
		label_savechange.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_savechange.setBorder(new LineBorder(Color.BLACK, 1));
		label_savechange.setBounds(130, 303, 200, 35);
		panel_detailaccount.add(label_savechange);

		lblNewLabel_5 = new JLabel("Tình trạng:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(10, 250, 100, 30);
		panel_detailaccount.add(lblNewLabel_5);

		rdbutton_active = new JRadioButton("Hoạt động");
		rdbutton_active.setBackground(new Color(249, 230, 186));
		rdbutton_active.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbutton_active.setBounds(140, 250, 120, 30);
		panel_detailaccount.add(rdbutton_active);
		rdbutton_active.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRadioButtonChange(rdbutton_active);
			}
		});

		rdbutton_disable = new JRadioButton("Vô hiệu hóa");
		rdbutton_disable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbutton_disable.setBackground(new Color(249, 230, 186));
		rdbutton_disable.setBounds(287, 250, 140, 30);
		panel_detailaccount.add(rdbutton_disable);
		rdbutton_disable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRadioButtonChange(rdbutton_disable);
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(rdbutton_active);
		group.add(rdbutton_disable);

		// Add ActionListeners
		rdbutton_savestate = null;

		LoadAllAccount();
	}

	private void showdetailAccount(String employeeid) {
		Account account = Account_BLL.getInstance().GetUserByEmployeeID(employeeid);
		label_employeeid.setText(employeeid);
		label_employeename.setText(account.getEmployeeName());
		tf_username.setText(account.getUsername());
		tf_password.setText(account.getPassword());

		if (account.getStatus() == 1) {
			rdbutton_active.setSelected(true);
			rdbutton_savestate = rdbutton_active;
		} else {
			rdbutton_disable.setSelected(true);
			rdbutton_savestate = rdbutton_disable;
		}
	}

	private void LoadAllAccount() {
		List<Account> list = Account_BLL.getInstance().getAllAccount();
		for (Account account : list) {
			Object[] row = { account.getUserID(), account.getEmployeeName(), account.getUsername(),
					account.getPassword() };
			modeltable_account.addRow(row);
		}
		table_account.setModel(modeltable_account);
	}
	
	private void searchAccount() {
		modeltable_account.setRowCount(0);
		String search = tf_search.getText();
		List<Account> list = Account_BLL.getInstance().searchAccount(search);
		for (Account account : list) {
			Object[] row = { account.getUserID(), account.getEmployeeName(), account.getUsername(),
					account.getPassword() };
			modeltable_account.addRow(row);
		}
		table_account.setModel(modeltable_account);
	}

	private void handleRadioButtonChange(JRadioButton rdbutton_current) {
		if (rdbutton_current != rdbutton_savestate) {
			rdbutton_savestate = rdbutton_current;
			if (rdbutton_current == rdbutton_active) {
				turnonSaveMode();
			} else if (rdbutton_current == rdbutton_disable) {
				turnonSaveMode();
			}
		}
	}

	private void turnonSaveMode() {
		label_savechange.setBorder(new LineBorder(Color.GREEN, 1));
		label_savechange.setForeground(Color.green);
	}
	private void turnoffSaveMode() {
		label_savechange.setBorder(new LineBorder(Color.black, 1));
		label_savechange.setForeground(Color.black);
	}
};
