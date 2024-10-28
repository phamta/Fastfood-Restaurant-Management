package KEO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import EDIT.RoundedPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import com.toedter.calendar.JCalendar;

public class keocustomer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_menubar;
	private JTextField tf_searchcustomer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keocustomer frame = new keocustomer();
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
	public keocustomer() {
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
		
		RoundedPanel panel_listcustomer = new RoundedPanel(50, new Color(249, 230, 186));
		panel_listcustomer.setBounds(1160, 30, 330, 730);
		contentPane.add(panel_listcustomer);
		panel_listcustomer.setLayout(null);
		
		JLabel label_searchcustomer = new JLabel("Tìm kiếm");
		label_searchcustomer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_searchcustomer.setBounds(10, 20, 90, 25);
		panel_listcustomer.add(label_searchcustomer);
		
		tf_searchcustomer = new JTextField();
		tf_searchcustomer.setBounds(110, 20, 200, 25);
		panel_listcustomer.add(tf_searchcustomer);
		tf_searchcustomer.setColumns(10);
		
		JScrollPane scrollPane_listcustomer = new JScrollPane();
		scrollPane_listcustomer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_listcustomer.setBounds(0, 55, 330, 650);
		panel_listcustomer.add(scrollPane_listcustomer);
		
		JPanel panel_listinfocustomer = new JPanel();
		scrollPane_listcustomer.setViewportView(panel_listinfocustomer);
		panel_listinfocustomer.setBackground(new Color(249, 230, 186));
		panel_listinfocustomer.setLayout(null);
		
		RoundedPanel panel_listcustomer_1_1 = new RoundedPanel(50, new Color(254, 215, 124));
		panel_listcustomer_1_1.addMouseListener(new MouseAdapter() {
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
		panel_listcustomer_1_1.setBounds(0, 0, 310, 80);
		panel_listinfocustomer.add(panel_listcustomer_1_1);
		panel_listcustomer_1_1.setLayout(null);
		
		JLabel lblNewLabel_18 = new JLabel("Văn Bá Phạm Tấn");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_18.setBounds(10, 10, 300, 25);
		panel_listcustomer_1_1.add(lblNewLabel_18);
		
		JLabel lblNewLabel_1 = new JLabel("0862711667");
		lblNewLabel_1.setBounds(150, 45, 150, 25);
		panel_listcustomer_1_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel = new JLabel("Số điện thoại:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 45, 125, 25);
		panel_listcustomer_1_1.add(lblNewLabel);
		
		RoundedPanel panel_buycustomer = new RoundedPanel(50, new Color(249, 230, 186));
		panel_buycustomer.setLayout(null);
		panel_buycustomer.setBounds(320, 30, 800, 730);
		contentPane.add(panel_buycustomer);
		
		JLabel lblSLnMua = new JLabel("Số lần mua:");
		lblSLnMua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSLnMua.setBounds(50, 30, 120, 25);
		panel_buycustomer.add(lblSLnMua);
		
		JLabel label_countbuy = new JLabel("9999");
		label_countbuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_countbuy.setBounds(185, 30, 50, 25);
		panel_buycustomer.add(label_countbuy);
		
		JLabel label_showhistorysale = new JLabel("Lịch sử mua hàng");
		label_showhistorysale.addMouseListener(new MouseAdapter() {
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
		label_showhistorysale.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_showhistorysale.setBounds(345, 30, 200, 35);
		label_showhistorysale.setHorizontalAlignment(SwingConstants.CENTER);
		label_showhistorysale.setBorder(new LineBorder(Color.black,1));
		panel_buycustomer.add(label_showhistorysale);
		
		JLabel label_statistical = new JLabel("Thống kê");
		label_statistical.addMouseListener(new MouseAdapter() {
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
		label_statistical.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_statistical.setBounds(576, 30, 200, 35);
		label_statistical.setHorizontalAlignment(SwingConstants.CENTER);
		label_statistical.setBorder(new LineBorder(Color.black,1));
		panel_buycustomer.add(label_statistical);
		
//		JScrollPane scrollPane_historybuy = new JScrollPane();
//		scrollPane_historybuy.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane_historybuy.setBounds(0, 100, 800, 630);
//		panel_buycustomer.add(scrollPane_historybuy);
		
		JPanel panel_statictical = new JPanel();
		panel_buycustomer.add(panel_statictical);
		panel_statictical.setBounds(0, 100, 800, 610);
		panel_statictical.setBackground(new Color(249, 230, 186));
		panel_statictical.setLayout(null);
		
		JComboBox<String> comboBox_choice = new JComboBox<String>();
		comboBox_choice.setBounds(30, 20, 200, 25);
		comboBox_choice.addItem("Mua hàng trong năm");
		comboBox_choice.addItem("Sản phấm ưa chuộng");
		panel_statictical.add(comboBox_choice);
		
		JLabel label_showchart = new JLabel("Hiển thị biểu đồ");
		label_showchart.addMouseListener(new MouseAdapter() {
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
		label_showchart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_showchart.setBounds(285, 20, 200, 30);
        label_showchart.setBorder(new LineBorder(Color.BLACK, 1));
        label_showchart.setHorizontalAlignment(SwingConstants.CENTER);
		panel_statictical.add(label_showchart);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(30, 65, 740, 470);
		panel_statictical.add(panel_1);
	}
}
