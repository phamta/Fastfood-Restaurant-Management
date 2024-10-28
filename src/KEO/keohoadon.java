package KEO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import EDIT.RoundedPanel;

public class keohoadon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_menubar;
	private RoundedPanel panel_content;
	private RoundedPanel panel_detailitem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keohoadon frame = new keohoadon();
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
	public keohoadon() {
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
		
		panel_content = new RoundedPanel(50, new Color(249, 230, 186));
//		panel_content.setBackground(new Color(255, 255, 255));
		panel_content.setBounds(310, 30, 730, 730);
		contentPane.add(panel_content);
		panel_content.setLayout(null);
		
		JDateChooser dateChooser_start = new JDateChooser();
		dateChooser_start.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 19));
		dateChooser_start.setDateFormatString("dd/MM/yyyy");
		dateChooser_start.setBounds(60, 20, 140, 30);
		dateChooser_start.setFont(new Font("Tahoma", Font.PLAIN, 19));
		panel_content.add(dateChooser_start);
		
		JLabel lblNewLabel = new JLabel("Từ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(20, 20, 30, 30);
		panel_content.add(lblNewLabel);
		
		JLabel lbln = new JLabel("Đến");
		lbln.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbln.setBounds(240, 20, 45, 30);
		panel_content.add(lbln);
		
		JDateChooser dateChooser_end = new JDateChooser();
		dateChooser_end.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 19));
		dateChooser_end.setFont(new Font("Tahoma", Font.PLAIN, 19));
		dateChooser_end.setDateFormatString("dd/MM/yyyy");
		dateChooser_end.setBounds(285, 20, 140, 30);
		panel_content.add(dateChooser_end);
		
		JLabel lblThngK = new JLabel("Thống kê");
		lblThngK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblThngK.setBounds(500, 20, 155, 30);
        lblThngK.setBorder(new LineBorder(Color.BLACK, 1));
        lblThngK.setHorizontalAlignment(SwingConstants.CENTER);
        panel_content.add(lblThngK);
        
		panel_detailitem= new RoundedPanel(50, new Color(249, 230, 186));
		panel_detailitem.setBounds(1060, 30, 450, 730);
		contentPane.add(panel_detailitem);
		panel_detailitem.setLayout(null);
	}
}
