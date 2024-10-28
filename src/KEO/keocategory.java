package KEO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import EDIT.RoundedPanel;

public class keocategory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RoundedPanel panel_listcategory;
	private JTextField tf_searchcategory;
	private JTable table_listcategory;
	private RoundedPanel panel_detailcategory;
	private JTextField tf_namecategory;
	private JLabel label_savechange;
	private DefaultTableModel modeltable_listcategory;
	private JLabel label_cancel;
	private JLabel label_addcategory;
	private JScrollPane scrollPane_listitem;
	private JPanel panel_listitem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keocategory frame = new keocategory();
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
	public keocategory() {
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

		JPanel panel_content = new JPanel();
		panel_content.setBounds(310, 10, 1190, 730);
		panel_content.setLayout(null);
		getContentPane().add(panel_content);

		panel_listcategory = new RoundedPanel(50, new Color(249, 230, 186));
		panel_listcategory.setBounds(0, 0, 710, 730);
		panel_content.add(panel_listcategory);
		panel_listcategory.setLayout(null);

		tf_searchcategory = new JTextField();
		tf_searchcategory.setBounds(500, 20, 150, 25);
		panel_listcategory.add(tf_searchcategory);
		tf_searchcategory.setColumns(10);
		tf_searchcategory.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			}

			public void removeUpdate(DocumentEvent e) {
			}

			public void insertUpdate(DocumentEvent e) {
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 75, 680, 620);
		panel_listcategory.add(scrollPane);

		table_listcategory = new JTable();
		scrollPane.setViewportView(table_listcategory);

		String[] columnNames = { "Mã nhân viên", "Họ và tên", "Username", "Password" };
		modeltable_listcategory = new DefaultTableModel(columnNames, 0);

		label_addcategory = new JLabel("Thêm");
		label_addcategory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_addcategory.setVisible(false);
				label_cancel.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_addcategory.setHorizontalAlignment(SwingConstants.CENTER);
		label_addcategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_addcategory.setBorder(new LineBorder(Color.BLACK, 1));
		label_addcategory.setBounds(320, 13, 120, 30);
		panel_listcategory.add(label_addcategory);

		label_cancel = new JLabel("Hủy");
		label_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_addcategory.setVisible(true);
				label_cancel.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_cancel.setHorizontalAlignment(SwingConstants.CENTER);
		label_cancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_cancel.setBorder(new LineBorder(Color.BLACK, 1));
		label_cancel.setBounds(320, 13, 120, 30);
		panel_listcategory.add(label_cancel);
		label_cancel.setVisible(false);

		panel_detailcategory = new RoundedPanel(50, new Color(249, 230, 186));
		panel_detailcategory.setBounds(730, 0, 460, 710);
		panel_content.add(panel_detailcategory);
		panel_detailcategory.setLayout(null);

		JLabel label_namecategory = new JLabel("Tên");
		label_namecategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_namecategory.setBounds(10, 20, 100, 25);
		panel_detailcategory.add(label_namecategory);

		tf_namecategory = new JTextField();
		tf_namecategory.setToolTipText("");
		tf_namecategory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tf_namecategory.setColumns(10);
		tf_namecategory.setBackground(new Color(249, 230, 186));
		tf_namecategory.setBounds(140, 20, 270, 30);
		panel_detailcategory.add(tf_namecategory);

		JLabel lblBaoGm = new JLabel("Bao gồm");
		lblBaoGm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBaoGm.setBounds(10, 158, 100, 25);
		panel_detailcategory.add(lblBaoGm);

		JLabel lblNewLabel_1 = new JLabel("Tên món");
		lblNewLabel_1.setBounds(30, 193, 85, 25);
		panel_detailcategory.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel_1_2 = new JLabel("Giá bán");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(360, 193, 75, 25);
		panel_detailcategory.add(lblNewLabel_1_2);

		JLabel lblNewLabel = new JLabel("Tình trạng");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 70, 100, 25);
		panel_detailcategory.add(lblNewLabel);

		JRadioButton rdbutton_active = new JRadioButton("Đang bán");
		rdbutton_active.setBackground(new Color(249, 230, 186));
		rdbutton_active.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbutton_active.setBounds(140, 70, 120, 25);
		panel_detailcategory.add(rdbutton_active);

		JRadioButton rdbutton_disable = new JRadioButton("Dừng bán");
		rdbutton_disable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbutton_disable.setBackground(new Color(249, 230, 186));
		rdbutton_disable.setBounds(290, 70, 130, 25);
		panel_detailcategory.add(rdbutton_disable);

		label_savechange = new JLabel("Lưu thay đổi");
		label_savechange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

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
		label_savechange.setBounds(130, 115, 200, 35);
		panel_detailcategory.add(label_savechange);
		
		scrollPane_listitem = new JScrollPane();
		scrollPane_listitem.setBounds(0, 225, 460, 460);
		panel_detailcategory.add(scrollPane_listitem);

		panel_listitem = new JPanel();
		scrollPane_listitem.setViewportView(panel_listitem);
		panel_listitem.setBackground(new Color(249, 230, 186));
		panel_listitem.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 10, 460, 30);
		panel_listitem.add(panel_1);
		panel_1.setBackground(new Color(249, 230, 186));
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Mì cay thập cẩm đặc biệt size M");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(5, 0, 340, 30);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("300.000");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(350, 0, 90, 30);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);
	}
}
