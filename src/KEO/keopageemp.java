package KEO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import BLL.Workschedule_BLL;
import DAL.Workschedule_DAL;

public class keopageemp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_info;
	private JLabel label_info;
	private JLabel label_image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keopageemp frame = new keopageemp();
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
	public keopageemp() {
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

		JPanel panel_choice = new JPanel();
		panel_choice.setBackground(new Color(252, 166, 100));
		panel_choice.setBounds(0, 50, screenSize.width, 45);
		contentPane.add(panel_choice);
		panel_choice.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Lịch làm");
		lblNewLabel_3.setBounds(120, 0, 120, 45);
		panel_choice.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel panel_schedule = new JPanel();
		panel_schedule.setBackground(new Color(254, 215, 124));
		panel_schedule.setBounds(110, 120, 450, 100);
		contentPane.add(panel_schedule);
		panel_schedule.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Ngày làm:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 10, 100, 25);
		panel_schedule.add(lblNewLabel_2);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateChooser.setDateFormatString(" dd/MM/yyyy");
		dateChooser.setBounds(120, 10, 140, 25);
		panel_schedule.add(dateChooser);
		
		
		JButton button_showschedule = new JButton("Xem lịch làm");
		button_showschedule.setBackground(new Color(0, 120, 255));
		button_showschedule.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_showschedule.setBounds(285, 10, 150, 25);
		panel_schedule.add(button_showschedule);
		
		JLabel lblNewLabel_4 = new JLabel("Kết quả");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(10, 50, 80, 25);
		panel_schedule.add(lblNewLabel_4);
		
		JLabel label_displayresult = new JLabel("07:30 - 10:30");
		label_displayresult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_displayresult.setBounds(120, 50, 300, 25);
		panel_schedule.add(label_displayresult);
		
		button_showschedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		        String selectedDate = sdf.format(dateChooser.getDate());
		        String result = Workschedule_BLL.getInstance().getWorkScheduleByEmployeeAndDate(selectedDate, "EMP001");
		        label_displayresult.setText(result);
			}
		});

		// Panel show information of employee
//		panel_info = new JPanel();
//		panel_info.setBackground(new Color(254, 215, 124));
//		panel_info.setBounds(145, 140, 1375, 500);
//		contentPane.add(panel_info);
//		panel_info.setLayout(null);
//		panel_info.setVisible(false);
//		
//		// Click label to show label_info
//        label_info = new JLabel("Thông tin");
//        label_info.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        label_info.setBounds(0, 0, 120, 45);
//        label_info.setBackground(panel.getBackground());
//        label_info.setOpaque(true);
//        label_info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        label_info.setHorizontalAlignment(SwingConstants.CENTER);
//        label_info.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            	label_info.setBackground(Color.blue);
//            	
//            }
//        });
//        panel.add(label_info);
        
//        GUIpanel_info();
		
		
	}
	
	public void GUIpanel_info() {
		JLabel lblNewLabel = new JLabel("Mã nhân viên ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(60, 60, 150, 25);
		panel_info.add(lblNewLabel);

		JLabel lblTnNhnVin = new JLabel("Tên nhân viên ");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTnNhnVin.setBounds(60, 110, 150, 25);
		panel_info.add(lblTnNhnVin);

		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGiiTnh.setBounds(60, 160, 150, 25);
		panel_info.add(lblGiiTnh);

		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNgySinh.setBounds(400, 160, 110, 25);
		panel_info.add(lblNgySinh);

		JLabel lblCaLmVic = new JLabel("Địa chỉ");
		lblCaLmVic.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCaLmVic.setBounds(60, 260, 150, 25);
		panel_info.add(lblCaLmVic);

		label_image = new JLabel("");
		label_image.setBounds(830, 55, 200, 190);
		panel_info.add(label_image);
		
        JButton button_exit = new JButton("Thoát");
        button_exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        button_exit.setBackground(new Color(254, 39, 60));
        button_exit.setFont(new Font("Tahoma", Font.PLAIN, 20));
        button_exit.setBounds(830, 270, 200, 50);
        panel_info.add(button_exit);
        
        JLabel lblNewLabel_1 = new JLabel("Số điện thoại");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(60, 210, 150, 25);
        panel_info.add(lblNewLabel_1);
        
        JLabel label_employeeid = new JLabel();
        label_employeeid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_employeeid.setBounds(220, 60, 150, 25);
        panel_info.add(label_employeeid);
        
        JLabel label_fullname = new JLabel();
        label_fullname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_fullname.setBounds(220, 110, 500, 25);
        panel_info.add(label_fullname);
        
        JLabel label_gender = new JLabel();
        label_gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_gender.setBounds(220, 160, 120, 25);
        panel_info.add(label_gender);
        
        JLabel label_birthday = new JLabel();
        label_birthday.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_birthday.setBounds(520, 160, 200, 25);
        panel_info.add(label_birthday);
        
        JLabel label_phonenumber = new JLabel();
        label_phonenumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_phonenumber.setBounds(220, 210, 140, 25);
        panel_info.add(label_phonenumber);
        
        JLabel lblNewLabel_7 = new JLabel("Lương 1h");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_7.setBounds(400, 210, 100, 25);
        panel_info.add(lblNewLabel_7);
        
        JLabel label_wage = new JLabel();
        label_wage.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_wage.setBounds(520, 210, 200, 25);
        panel_info.add(label_wage);
        
        JLabel label_address = new JLabel();
        label_address.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_address.setBounds(220, 260, 500, 25);
        panel_info.add(label_address);
	}
}
