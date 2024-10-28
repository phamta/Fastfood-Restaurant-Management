package KEO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import BLL.Order_BLL;
import BLL.Orderdetail_BLL;
import DTO.Itemplus;
import DTO.Order;
import EDIT.RoundedPanel;

public class keoquanli extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// panel show interface management
	private JPanel panel_content;

	// panel choose which to management
	private JPanel panel_menubar;

	// container of employee management
//	private JTable table;
//	private JTextField tf_employeeid;
//	private JTextField tf_fullname;
//	private JTextField tf_phonenumber;
//	private JTextField tf_address;
//	private JTextField tf_salary;
//	private JDateChooser dateChooser;
//	private JComboBox<String> comboBox_gender;
//	private DefaultTableModel model;
//	private JLabel label_avatar;
//	private JComboBox<String> comboBox_search;
//	private JTextField tf_search;
//	private JLabel lblNewLabel_2;
//	private JComboBox<String> comboBox;
//	private JTable table_1;
//	private JLabel lblNewLabel_1;
//	private JTextField tf_itemid;
//	private JTextField tf_nameitem;
//	private JTextField tf_price;
//	private JButton button_update;
//	private JButton btnDelete;
//	private JButton button_clear;
	private JPanel panel_manaemp;
	private RoundedPanel panel_detaiorder;
	private RoundedPanel panel_listorder;
	private JTextField tf_searchorder;
	private JTable table_listorder;
	private DefaultTableModel modeltable_listorder;

	private String orderid;
	private JLabel label_datetime;
	private JLabel label_employeeid;
	private JLabel label_getmoney;
	private JLabel label_total;
	private JLabel label_change;
	private JPanel panel_listdetailorder;
	private JScrollPane scrollPane_listdetailorder;
	private JDateChooser dateChooser_start;
	private JDateChooser dateChooser_end;
	private JButton button_excel;
	private JLabel label_statistical;
	private JScrollPane scrollPane;
	private JPanel panel_statistical;
	
	private ChartPanel panel_chart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keoquanli frame = new keoquanli();
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
	public keoquanli() {
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

		panel_manaemp = new JPanel();
		panel_manaemp.setBounds(0, 0, 230, 50);
		panel_menubar.add(panel_manaemp);
		panel_manaemp.setLayout(null);
		panel_manaemp.putClientProperty("choice", "Employee");

		JLabel label_menuempimg = new JLabel("");
		label_menuempimg.setBounds(0, 0, 60, 50);
		panel_manaemp.add(label_menuempimg);

		JLabel label = new JLabel("Nhân viên");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(60, 0, 170, 50);
		panel_manaemp.add(label);

		JPanel panel_manaitem = new JPanel();
		panel_manaitem.setLayout(null);
		panel_manaitem.setBounds(0, 55, 230, 50);
		panel_menubar.add(panel_manaitem);

		JLabel label_menuitemimage = new JLabel("");
		label_menuitemimage.setBounds(0, 0, 60, 50);
		panel_manaitem.add(label_menuitemimage);

		JLabel panel_manaitem_1 = new JLabel("Thực đơn");
		panel_manaitem_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_manaitem_1.setBounds(60, 0, 170, 50);
		panel_manaitem.add(panel_manaitem_1);

		JPanel panel_manacombo = new JPanel();
		panel_manacombo.setLayout(null);
		panel_manacombo.setBounds(0, 110, 230, 50);
		panel_menubar.add(panel_manacombo);

		JLabel label_menuempimg_1 = new JLabel("");
		label_menuempimg_1.setBounds(0, 0, 60, 50);
		panel_manacombo.add(label_menuempimg_1);

		JLabel lblCombo = new JLabel("Combo");
		lblCombo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCombo.setBounds(60, 0, 170, 50);
		panel_manacombo.add(lblCombo);

		JPanel panel_manacategory = new JPanel();
		panel_manacategory.setLayout(null);
		panel_manacategory.setBounds(0, 165, 230, 50);
		panel_menubar.add(panel_manacategory);

		JLabel label_menuempimg_1_1 = new JLabel("");
		label_menuempimg_1_1.setBounds(0, 0, 60, 50);
		panel_manacategory.add(label_menuempimg_1_1);

		JLabel lblLoiSnPhm = new JLabel("Loại sản phẩm");
		lblLoiSnPhm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoiSnPhm.setBounds(60, 0, 170, 50);
		panel_manacategory.add(lblLoiSnPhm);

		JPanel panel_manaorder = new JPanel();
		panel_manaorder.setLayout(null);
		panel_manaorder.setBounds(0, 220, 230, 50);
		panel_menubar.add(panel_manaorder);

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

		JPanel panel_manaschedule = new JPanel();
		panel_manaschedule.setLayout(null);
		panel_manaschedule.setBounds(0, 275, 230, 50);
		panel_menubar.add(panel_manaschedule);

		JLabel label_menuempimg_1_4 = new JLabel("");
		label_menuempimg_1_4.setBounds(0, 0, 60, 50);
		panel_manaschedule.add(label_menuempimg_1_4);

		JLabel lblPhnCng = new JLabel("Lịch làm");
		lblPhnCng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhnCng.setBounds(60, 0, 170, 50);
		panel_manaschedule.add(lblPhnCng);

		JPanel panel_manapayroll = new JPanel();
		panel_manapayroll.setLayout(null);
		panel_manapayroll.setBounds(0, 330, 230, 50);
		panel_menubar.add(panel_manapayroll);

		JLabel label_menuempimg_1_5 = new JLabel("");
		label_menuempimg_1_5.setBounds(0, 0, 60, 50);
		panel_manapayroll.add(label_menuempimg_1_5);

		JLabel lblBngLng = new JLabel("Bảng lương");
		lblBngLng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBngLng.setBounds(60, 0, 170, 50);
		panel_manapayroll.add(lblBngLng);

		JPanel panel_manaaccount = new JPanel();
		panel_manaaccount.setLayout(null);
		panel_manaaccount.setBounds(0, 385, 230, 50);
		panel_menubar.add(panel_manaaccount);

		JLabel label_menuempimg_1_6 = new JLabel("");
		label_menuempimg_1_6.setBounds(0, 0, 60, 50);
		panel_manaaccount.add(label_menuempimg_1_6);

		JLabel lblTiKhon = new JLabel("Tài khoản");
		lblTiKhon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTiKhon.setBounds(60, 0, 170, 50);
		panel_manaaccount.add(lblTiKhon);

		JPanel panel_manapoint = new JPanel();
		panel_manapoint.setLayout(null);
		panel_manapoint.setBounds(0, 440, 230, 50);
		panel_menubar.add(panel_manapoint);

		JLabel label_menuempimg_1_7 = new JLabel("");
		label_menuempimg_1_7.setBounds(0, 0, 60, 50);
		panel_manapoint.add(label_menuempimg_1_7);

		JLabel lbliim = new JLabel("Đổi điểm");
		lbliim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbliim.setBounds(60, 0, 170, 50);
		panel_manapoint.add(lbliim);

		JPanel panel_manacustomer = new JPanel();
		panel_manacustomer.setLayout(null);
		panel_manacustomer.setBounds(0, 495, 230, 50);
		panel_menubar.add(panel_manacustomer);

		JLabel label_menuempimg_1_8 = new JLabel("");
		label_menuempimg_1_8.setBounds(0, 0, 60, 50);
		panel_manacustomer.add(label_menuempimg_1_8);

		JLabel lblKhchHng = new JLabel("Khách hàng");
		lblKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKhchHng.setBounds(60, 0, 170, 50);
		panel_manacustomer.add(lblKhchHng);

		JPanel panel_manatimekeeping = new JPanel();
		panel_manatimekeeping.setLayout(null);
		panel_manatimekeeping.setBounds(0, 550, 230, 55);
		panel_menubar.add(panel_manatimekeeping);

		JLabel label_menuempimg_1_9 = new JLabel("");
		label_menuempimg_1_9.setBounds(0, 0, 60, 55);
		panel_manatimekeeping.add(label_menuempimg_1_9);

		JLabel lblCh = new JLabel("Chờ");
		lblCh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCh.setBounds(60, 0, 170, 55);
		panel_manatimekeeping.add(lblCh);

		panel_content = new JPanel();
		panel_content.setBackground(new Color(254, 215, 124));
		panel_content.setBounds(310, 30, 1190, 730);
		contentPane.add(panel_content);
		panel_content.setLayout(null);

		panel_listorder = new RoundedPanel(50, new Color(249, 230, 186));
		panel_listorder.setBounds(0, 0, 710, 730);
		panel_content.add(panel_listorder);
		panel_listorder.setLayout(null);

		tf_searchorder = new JTextField("ORD003");
		tf_searchorder.setBounds(395, 20, 150, 25);
		panel_listorder.add(tf_searchorder);
		tf_searchorder.setColumns(10);
		tf_searchorder.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				Loaddatatotable();
			}

			public void removeUpdate(DocumentEvent e) {
				Loaddatatotable();
			}

			public void insertUpdate(DocumentEvent e) {
				Loaddatatotable();
			}

		});

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", Font.PLAIN, 20), new java.awt.Color(102, 102, 102))); // NOI18N
		scrollPane.setBounds(10, 75, 680, 550);
		panel_listorder.add(scrollPane);

		table_listorder = new JTable();
		table_listorder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table_listorder);
		table_listorder.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int selectedRow = table_listorder.getSelectedRow();
					if (selectedRow != -1) {
						orderid = modeltable_listorder.getValueAt(selectedRow, 0).toString();
						showDetailOrder(orderid);

					}
				}
			}
		});

		String[] columnNames = { "Mã hóa đơn", "Khách hàng", "Đổi điểm" };
		modeltable_listorder = new DefaultTableModel(columnNames, 0);

		JLabel lblNewLabel_1 = new JLabel("Từ:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 20, 35, 20);
		panel_listorder.add(lblNewLabel_1);

		dateChooser_start = new JDateChooser();
		dateChooser_start.setDateFormatString("dd/MM/yyyy");
		dateChooser_start.setBounds(50, 20, 120, 25);
		panel_listorder.add(dateChooser_start);

		dateChooser_start.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					Date startDate = dateChooser_start.getDate();
					Date endDate = dateChooser_end.getDate();
					if (startDate != null && endDate != null && startDate.after(endDate)) {
						JOptionPane.showMessageDialog(null, "Start date must be before or equal to end date.");
						dateChooser_start.setDate(endDate);
					} else {
						getOrderByDateRange();
					}
				}
			}
		});

		JLabel lblNewLabel_2 = new JLabel("Đến:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(200, 20, 45, 20);
		panel_listorder.add(lblNewLabel_2);

		dateChooser_end = new JDateChooser();
		dateChooser_end.setDateFormatString("dd/MM/yyyy");
		dateChooser_end.setBounds(250, 20, 120, 25);
		panel_listorder.add(dateChooser_end);
		dateChooser_end.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					Date startDate = dateChooser_start.getDate();
					Date endDate = dateChooser_end.getDate();
					if (startDate != null && endDate != null && endDate.before(startDate)) {
						JOptionPane.showMessageDialog(null, "End date must be after or equal to start date.");
						dateChooser_end.setDate(startDate);
					} else {
						getOrderByDateRange();
					}
				}
			}
		});

		// get today'date
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		dateChooser_start.setDate(today);

		// get tomorrow's date
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		dateChooser_end.setDate(tomorrow);

		label_statistical = new JLabel("Thống kê");
		label_statistical.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				turnonStatisticalMode();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_statistical.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_statistical.setBounds(560, 20, 120, 30);
		label_statistical.setBorder(new LineBorder(Color.BLACK, 1));
		label_statistical.setHorizontalAlignment(SwingConstants.CENTER);
		panel_listorder.add(label_statistical);
		
		JLabel label_exit = new JLabel("Thoát");
		label_exit.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	turnoffStatisticalMode();
		    }
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    }
		    @Override
		    public void mouseExited(MouseEvent e) {
		    }
		});
		label_exit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_exit.setBounds(960, 20, 120, 30);
		label_exit.setBorder(new LineBorder(Color.BLACK, 1));
		label_exit.setHorizontalAlignment(SwingConstants.CENTER);
		panel_listorder.add(label_exit);

		button_excel = new JButton("Xuất excel");
		button_excel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_listorder.getSelectedRow();
				if (selectedRow != -1) {
					try {
						orderid = modeltable_listorder.getValueAt(selectedRow, 0).toString();
						Export_Excel_Bill(orderid);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn trước");
				}
			}
		});
		button_excel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button_excel.setBounds(475, 650, 175, 40);
		panel_listorder.add(button_excel);
		
		panel_statistical = new JPanel();
		panel_statistical.setBackground(new Color(249, 230, 186));
		panel_statistical.setBounds(0, 70, 1190, 640);
		panel_listorder.add(panel_statistical);
		panel_statistical.setLayout(null);
		panel_statistical.setVisible(false);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(20, 10, 150, 30);
		comboBox.addItem("Doanh số hằng tháng");
		comboBox.addItem("Sản phẩm bán chạy");
		panel_statistical.add(comboBox);
		
		JLabel label_showchar = new JLabel("Hiển thị biểu đồ");
		label_showchar.setHorizontalAlignment(SwingConstants.CENTER);
		label_showchar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_showchar.setBorder(new LineBorder(Color.BLACK, 1));
		label_showchar.setBounds(220, 10, 180, 40);
		label_showchar.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	int index = comboBox.getSelectedIndex();
		    	if(index == 0) {
		    		showMonthlySale();
		    	}
		    	else {
		    		showItemCounts();
		    	}
		    }
		    @Override
		    public void mouseEntered(MouseEvent e) {
		    }
		    @Override
		    public void mouseExited(MouseEvent e) {
		    }
		});
		panel_statistical.add(label_showchar);


		panel_detaiorder = new RoundedPanel(50, new Color(249, 230, 186));
		panel_detaiorder.setBounds(730, 0, 460, 730);
		panel_content.add(panel_detaiorder);
		panel_detaiorder.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hóa đơn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(0, 70, 450, 25);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_detaiorder.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("Thời gian");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(10, 115, 120, 25);
		panel_detaiorder.add(lblNewLabel_3);

		label_datetime = new JLabel("12/02/2004 18:24:30");
		label_datetime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_datetime.setBounds(150, 115, 200, 25);
		panel_detaiorder.add(label_datetime);

		JLabel lblNewLabel_3_1 = new JLabel("Mã nhân viên");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(10, 155, 120, 25);
		panel_detaiorder.add(lblNewLabel_3_1);

		label_employeeid = new JLabel("EMP003");
		label_employeeid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_employeeid.setBounds(150, 155, 200, 25);
		panel_detaiorder.add(label_employeeid);

		JLabel lblNewLabel_3_1_1 = new JLabel("Name");
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1_1.setBounds(10, 195, 80, 25);
		panel_detaiorder.add(lblNewLabel_3_1_1);

		scrollPane_listdetailorder = new JScrollPane();
		scrollPane_listdetailorder.setBounds(0, 230, 460, 350);
		panel_detaiorder.add(scrollPane_listdetailorder);

		panel_listdetailorder = new JPanel();
		scrollPane_listdetailorder.setViewportView(panel_listdetailorder);
		panel_listdetailorder.setBackground(new Color(249, 230, 186));
//		panel_listdetailorder.setLayout(new BoxLayout(panel_listdetailorder, BoxLayout.Y_AXIS));
		panel_listdetailorder.setLayout(null);


		JLabel lblNewLabel_6 = new JLabel("Tổng tiền");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(175, 590, 100, 25);
		panel_detaiorder.add(lblNewLabel_6);

		label_total = new JLabel("70.000 đồng");
		label_total.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_total.setHorizontalAlignment(SwingConstants.RIGHT);
		label_total.setBounds(280, 590, 160, 25);
		panel_detaiorder.add(label_total);

		JLabel lblNewLabel_6_2 = new JLabel("Tiền nhận");
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6_2.setBounds(175, 625, 100, 25);
		lblNewLabel_6_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_detaiorder.add(lblNewLabel_6_2);

		JLabel lblNewLabel_6_3 = new JLabel("Tiền thừa");
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6_3.setBounds(175, 660, 100, 25);
		panel_detaiorder.add(lblNewLabel_6_3);

		label_getmoney = new JLabel("100.000 đồng");
		label_getmoney.setHorizontalAlignment(SwingConstants.RIGHT);
		label_getmoney.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_getmoney.setBounds(280, 625, 160, 25);
		panel_detaiorder.add(label_getmoney);

		label_change = new JLabel("30.000 đồng");
		label_change.setHorizontalAlignment(SwingConstants.RIGHT);
		label_change.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_change.setBounds(280, 660, 160, 25);
		panel_detaiorder.add(label_change);

		JLabel lblNewLabel_9 = new JLabel("QTY");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_9.setBounds(170, 195, 45, 25);
		panel_detaiorder.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Price");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(220, 195, 100, 25);
		panel_detaiorder.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Total");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(340, 195, 100, 25);
		panel_detaiorder.add(lblNewLabel_11);

//		Map<Integer, Integer> monthlyRevenue = getMonthlyRevenue(Order_BLL.getInstance().getAllOrder());
//		XYSeries series = new XYSeries("Doanh thu");
//
//        for (Map.Entry<Integer, Integer> entry : monthlyRevenue.entrySet()) {
//            series.add(entry.getKey(), entry.getValue());
//        }
//
//        XYSeriesCollection dataset = new XYSeriesCollection(series);
//
//        JFreeChart lineChart = ChartFactory.createXYLineChart(
//            "Doanh thu năm 2024", // title
//            "Tháng",
//            "Doanh thu",
//            dataset,
//            PlotOrientation.VERTICAL,
//            true, true, false
//        );
//
//        XYPlot plot = lineChart.getXYPlot();
//        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
//        domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//
//        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//        renderer.setSeriesShapesVisible(0, true);
//        renderer.setBaseToolTipGenerator(new XYToolTipGenerator() {
//            @Override
//            public String generateToolTip(XYDataset dataset, int series, int item) {
//            	double revenue = dataset.getYValue(series, item);
//            	return String.format("%.0f", revenue);
//            }
//        });
//        plot.setRenderer(renderer);
//		
//        ChartPanel chartPanel = new ChartPanel(lineChart);
//        chartPanel.setBounds(50, 30, 1090, 540);
//		panel_content.add(chartPanel);
//		contentPane.revalidate();
//        contentPane.repaint();

//		JButton btnShowRevenueChart = new JButton("Show Revenue Chart");
		getOrderByDateRange();

	}

	public String getFormattedDate(JDateChooser dateChooser) {
		Date date = dateChooser.getDate();
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.format(date);
		}
		return "";
	}

	private void getOrderByDateRange() {
		modeltable_listorder.setRowCount(0);
		String date_start = getFormattedDate(dateChooser_start);
		String date_end = getFormattedDate(dateChooser_end);
		List<Order> list = Order_BLL.getInstance().getOrderByDateRange(date_start, date_end);
		for (Order order : list) {
			Object[] row = { order.getOrderID(), order.getCustomerName(), order.getBonusitemName() };
			modeltable_listorder.addRow(row);
		}
		table_listorder.setModel(modeltable_listorder);
	}

	private void Loaddatatotable() {
		modeltable_listorder.setRowCount(0);
		String search = tf_searchorder.getText();

		List<Order> list = Order_BLL.getInstance().getOrderByID(search);
		for (Order order : list) {
			Object[] row = { order.getOrderID(), order.getCustomerName(), order.getBonusitemName() };
			modeltable_listorder.addRow(row);
		}

		table_listorder.setModel(modeltable_listorder);
	}

	private void showDetailOrder(String orderid) {
		Order order = Order_BLL.getInstance().getOrderByID(orderid).getFirst();
		label_datetime.setText(order.getDatetime());
		label_employeeid.setText(order.getEmployeeID());
		label_total.setText("" + order.getTotal());
		label_getmoney.setText("" + order.getTake());
		label_change.setText("" + order.getReturnmoney());

		List<Itemplus> list = Orderdetail_BLL.getInstance().GetItemplusByOrderID(orderid);
		display(list);
	}

	public boolean isLabelContentVisible(JLabel label) {
		FontMetrics fontMetrics = label.getFontMetrics(label.getFont());
		int textWidth = fontMetrics.stringWidth(label.getText());
		int labelWidth = label.getPreferredSize().width;

		return textWidth <= labelWidth;

	}

	private JPanel createPanelItemplus(Itemplus itemplus, int y) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(249, 230, 186));

		int check;
		JLabel label_nameitem = new JLabel(itemplus.getItemName());
		label_nameitem.setPreferredSize(new Dimension(150, 30));
		label_nameitem.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		label_nameitem.setBorder(new LineBorder(Color.black, 1));
		if (isLabelContentVisible(label_nameitem)) {
			label_nameitem.setBounds(0, 0, 150, 30);
			panel.setBounds(5, y, 430, 30);
			panel.add(label_nameitem);
			check = 0;

		} else {
			label_nameitem.setPreferredSize(new Dimension(430, 30));
			label_nameitem.setBounds(0, 0, 430, 30);
			panel.setBounds(5, y, 430, 60);
			panel.add(label_nameitem);
			check = 30;
		}

//		System.out.println(check);
		JLabel label_quantity = new JLabel(itemplus.getQuantity() + "");
		label_quantity.setBounds(160, check, 45, 30);
		label_quantity.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_quantity);
		label_quantity.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel label_price = new JLabel("" + itemplus.getPrice());
		label_price.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_price.setBounds(220, check, 100, 30);
		panel.add(label_price);

		JLabel label_total = new JLabel("" + itemplus.getTotal());
		label_total.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_total.setBounds(330, check, 100, 30);
		panel.add(label_total);

		return panel;
	}

	private void display(List<Itemplus> list) {
		int y = 10;
		int distance = 10;
		panel_listdetailorder.removeAll();
		for (Itemplus itemplus : list) {
			JPanel panel = createPanelItemplus(itemplus, y);
			panel_listdetailorder.add(panel);
			y += panel.getHeight() + distance;
		}
		if (y > scrollPane_listdetailorder.getHeight()) {
			scrollPane_listdetailorder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		} else {
			scrollPane_listdetailorder.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		}
		panel_listdetailorder.revalidate();
		panel_listdetailorder.repaint();

	}

	public void Export_Excel_Bill(String orderID) throws FileNotFoundException, IOException {
		// Lấy thông tin order dựa trên ID
		Order order = Order_BLL.getInstance().getOrderByID(orderID).getFirst();

		// Thiết lập định dạng tiền tệ Việt Nam
		Locale locale = new Locale("vi", "VN");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		format.setRoundingMode(RoundingMode.HALF_UP);

		// Tạo workbook và sheet
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet sheet = workBook.createSheet("Hóa đơn");

		XSSFRow row = null;
		Cell cell = null;

		// Merge các ô để tạo tiêu đề
		CellRangeAddress range = new CellRangeAddress(1, 1, 0, 11);
		sheet.addMergedRegion(range);

		// Định dạng kiểu ô cho tiêu đề và nội dung
		CellStyle styleId = createCellStyle(workBook, 16, true, false, false);
		CellStyle style_Bold = createCellStyle(workBook, 13, true, false, false);
		CellStyle style_Strikeout = createCellStyle(workBook, 13, false, true, false);
		CellStyle style_Common = createCellStyle(workBook, 12, false, false, false);

		// Tạo tiêu đề cho hóa đơn
		row = sheet.createRow(1);
		cell = row.createCell(0, org.apache.poi.ss.usermodel.CellType.STRING);
		cell.setCellValue("HÓA ĐƠN");
		cell.setCellStyle(styleId);

		// Thông tin hóa đơn
		createCell(sheet, 3, 0, "Mã:", style_Bold);
		createMergedCell(sheet, 3, 1, 4, orderID, style_Common);
		createCell(sheet, 3, 7, "Ngày:", style_Bold);
		createMergedCell(sheet, 3, 8, 11, order.getDatetime(), style_Common);

		// Thông tin nhân viên
		createCell(sheet, 4, 0, "Nhân viên:", style_Bold);
		createMergedCell(sheet, 4, 1, 4, "Nhân viên", style_Common);

		// Thông tin khách hàng
		createCell(sheet, 4, 7, "Khách hàng:", style_Bold);
		createMergedCell(sheet, 4, 8, 11, order.getCustomerName(), style_Common);

		// Tạo dòng phân cách
		createSeparatorRow(sheet, 6, 1, 10, style_Bold);

		// Tiêu đề cột cho chi tiết hóa đơn
		createHeaderRow(sheet, 8, style_Bold);

		// Thêm chi tiết hóa đơn vào bảng
		List<Itemplus> items = Orderdetail_BLL.getInstance().GetItemplusByOrderID(orderID);
		int i = 0; // Khởi tạo biến i
		if (items != null) {
			addOrderDetails(sheet, items, style_Common, style_Strikeout, format, i);
		}

		// Tổng kết hóa đơn (tổng tiền, tiền nhận, tiền thối)
		addFooter(sheet, order, style_Bold, style_Common, format, items.size());

		// Định dạng border cho toàn bộ bảng
		CellRangeAddress rangeBig = new CellRangeAddress(0, 16 + items.size(), 0, 11);
		RegionUtil.setBorderRight(BorderStyle.THIN, rangeBig, sheet);
		RegionUtil.setBorderLeft(BorderStyle.DOUBLE, rangeBig, sheet);
		RegionUtil.setBorderTop(BorderStyle.DOUBLE, rangeBig, sheet);
		RegionUtil.setBorderBottom(BorderStyle.THIN, rangeBig, sheet);

		// Lưu file Excel
		saveExcelFile(workBook);
	}

	private CellStyle createCellStyle(XSSFWorkbook workBook, int fontSize, boolean isBold, boolean isStrikeout,
			boolean isItalic) {
		CellStyle style = workBook.createCellStyle();
		XSSFFont font = workBook.createFont();
		font.setFontHeight(fontSize);
		font.setBold(isBold);
		font.setStrikeout(isStrikeout);
		font.setItalic(isItalic);
		font.setColor(IndexedColors.BLACK1.getIndex());
		style.setFont(font);

		return style;
	}

	private void createCell(XSSFSheet sheet, int rowNum, int colNum, String value, CellStyle style) {
		XSSFRow row = sheet.getRow(rowNum) != null ? sheet.getRow(rowNum) : sheet.createRow(rowNum);
		Cell cell = row.createCell(colNum, org.apache.poi.ss.usermodel.CellType.STRING);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}

	private void createMergedCell(XSSFSheet sheet, int rowNum, int colStart, int colEnd, String value,
			CellStyle style) {
		CellRangeAddress range = new CellRangeAddress(rowNum, rowNum, colStart, colEnd);
		sheet.addMergedRegion(range);
		createCell(sheet, rowNum, colStart, value, style);
	}

	private void createSeparatorRow(XSSFSheet sheet, int rowNum, int colStart, int colEnd, CellStyle style) {
		// Xóa các vùng hợp nhất đã tồn tại ở hàng hiện tại
		for (int i = sheet.getNumMergedRegions() - 1; i >= 0; i--) {
			CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
			if (mergedRegion.getFirstRow() == rowNum && mergedRegion.getLastRow() == rowNum) {
				sheet.removeMergedRegion(i);
			}
		}

		// Thêm vùng hợp nhất mới
		CellRangeAddress range = new CellRangeAddress(rowNum, rowNum, colStart, colEnd);
		sheet.addMergedRegion(range);
		createCell(sheet, rowNum, colStart,
				"------------------------------------------------------------------------------------------------------------------------------------------------",
				style);
	}

	private void createHeaderRow(XSSFSheet sheet, int rowNum, CellStyle style) {
		createCell(sheet, rowNum, 0, "STT", style);
		createMergedCell(sheet, rowNum, 1, 4, "Sản phẩm", style);
		createCell(sheet, rowNum, 5, "SL", style);
		createMergedCell(sheet, rowNum, 6, 7, "Giá", style);
		createMergedCell(sheet, rowNum, 8, 9, "Giá giảm", style);
		createMergedCell(sheet, rowNum, 10, 11, "Thành tiền", style);
	}

	private void addOrderDetails(XSSFSheet sheet, List<Itemplus> items, CellStyle commonStyle, CellStyle strikeoutStyle,
			NumberFormat format, int i) {
		for (i = 0; i < items.size(); i++) {
			Itemplus item = items.get(i);
			XSSFRow row = sheet.createRow(10 + i);
			createCell(sheet, 10 + i, 0, String.valueOf(i + 1), commonStyle);
			createMergedCell(sheet, 10 + i, 1, 4, item.getItemName(), commonStyle);
			createCell(sheet, 10 + i, 5, String.valueOf(item.getQuantity()), commonStyle);

			double price = item.getPrice();
			createMergedCell(sheet, 10 + i, 6, 7, format.format(price), commonStyle);
			createMergedCell(sheet, 10 + i, 8, 9, format.format(0), commonStyle);
			createMergedCell(sheet, 10 + i, 10, 11, format.format(item.getTotal()), commonStyle);
		}
	}

	private void addFooter(XSSFSheet sheet, Order order, CellStyle boldStyle, CellStyle commonStyle,
			NumberFormat format, int itemCount) {
		int currentRow = 11 + itemCount;

		createSeparatorRow(sheet, currentRow, 1, 10, boldStyle);
		
	    String bonusItemName = order.getBonusitemName();
	    if (bonusItemName == null || bonusItemName.isEmpty()) {
	        bonusItemName = "Không có";
	    }

		currentRow++;
		createMergedCell(sheet, currentRow, 6, 7, "Đổi điểm:", boldStyle);
		createMergedCell(sheet, currentRow, 8, 10, bonusItemName, commonStyle);
		
		currentRow++;
		createMergedCell(sheet, currentRow, 6, 7, "Tổng:", boldStyle);
		createMergedCell(sheet, currentRow, 8, 10, format.format(order.getTotal()), commonStyle);

		currentRow++;
		createMergedCell(sheet, currentRow, 6, 7, "Tiền nhận:", boldStyle);
		createMergedCell(sheet, currentRow, 8, 10, format.format(order.getTake()), commonStyle);

		currentRow++;
		createMergedCell(sheet, currentRow, 6, 7, "Tiền thối:", boldStyle);
		createMergedCell(sheet, currentRow, 8, 10, format.format(order.getReturnmoney()), commonStyle);
	}

	private void saveExcelFile(XSSFWorkbook workBook) throws FileNotFoundException, IOException {
		JFileChooser fs = new JFileChooser(new File("c:\\"));
		fs.setDialogTitle("Save");

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx");
		fs.setFileFilter(filter);

		int returnVal = fs.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fs.getSelectedFile();
			if (!file.getName().toLowerCase().endsWith(".xlsx")) {
				file = new File(file.getAbsolutePath() + ".xlsx");
			}
			FileOutputStream fos = new FileOutputStream(file);
			workBook.write(fos);
			fos.close();
		}
	}
	
	private void turnonStatisticalMode() {
		panel_listorder.setBounds(0, 0, 1190, 730);
		panel_detaiorder.setVisible(false);
		scrollPane.setVisible(false);
		button_excel.setVisible(false);
		label_statistical.setVisible(false);
		panel_statistical.setVisible(true);
	}
	
	private void turnoffStatisticalMode() {
		panel_listorder.setBounds(0, 0, 710, 730);
		panel_detaiorder.setVisible(true);
		scrollPane.setVisible(true);
		button_excel.setVisible(true);
		label_statistical.setVisible(true);
		panel_statistical.setVisible(false);
	}
	
	private void showMonthlySale() {
		List<Integer> salesData = Order_BLL.getInstance().getTotalSalesPerMonth();
		
        
	    // Create a dataset for the chart
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    
	    // Populate the dataset with the sales data
	    for (int month = 1; month <= salesData.size(); month++) {
	        // Use month number as a String ("1", "2", ..., "12")
	        String monthLabel = String.valueOf(month);
	        int totalSales = salesData.get(month - 1);
	        
	        dataset.addValue(totalSales, "Tổng lương", monthLabel);
	    }

	    // Create the bar chart
	    JFreeChart barChart = ChartFactory.createBarChart(
	            "Tổng lương hàng tháng năm 2024", // Chart title
	            "Tháng",                         // Domain axis label
	            "Tổng lương",                    // Range axis label
	            dataset,                         // Data
	            PlotOrientation.VERTICAL,        // Orientation
	            true,                            // Include legend
	            true,                            // Tooltips
	            false                            // URLs
	    );
	    
	    if (panel_chart != null) {
	        panel_statistical.remove(panel_chart);
	    }
        
        panel_chart = new ChartPanel(barChart);
        panel_chart.setBounds(145, 80, 800, 500);
        panel_statistical.add(panel_chart);
        panel_statistical.revalidate();
        panel_statistical.repaint();
	}
	
    public void showItemCounts() {
        // Fetch item counts from the data access layer
        Map<String, Integer> itemCounts = Order_BLL.getInstance().getItemCounts();
        
        // Create a dataset for the chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // Populate the dataset with item counts
        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            
            dataset.addValue(quantity, "Số lượng", itemName);
        }

        // Create the bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Số lượng gọi món của từng sản phẩm", // Chart title
                "Sản phẩm",                         // Domain axis label
                "Số lượng",                         // Range axis label
                dataset,                            // Data
                PlotOrientation.VERTICAL,           // Orientation
                true,                               // Include legend
                true,                               // Tooltips
                false                               // URLs
        );

        // Customize the plot to ensure integer values on the y-axis
        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Optional: Set custom renderer if needed
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // Create and set the ChartPanel
        if (panel_chart != null) {
            panel_statistical.remove(panel_chart);
        }
        
        panel_chart = new ChartPanel(barChart);
        panel_chart.setBounds(5, 80, 1180, 500);
        panel_statistical.add(panel_chart);
        panel_statistical.revalidate();
        panel_statistical.repaint();
    }
};
