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

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import BLL.Employee_BLL;
import BLL.Workschedule_BLL;
import BLL.Account_BLL;
import DTO.Employee;
import DTO.Account;
import EDIT.RoundTextField;
import EDIT.RoundedIconTextField;

public class Employee_VIEW extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_info;
	
	private Employee employee;
	private JPanel panel_password;
	private RoundedIconTextField tf_oldpass;
	private RoundTextField tf_newpass;
	private JButton button_OK;
	private JLabel label_info;
	private JPanel panel_schedule;
	private JLabel label_password;
	private JLabel label_workhour;
	private JLabel label_schedule;
	
	private JLabel label_employeeid;
	private JLabel label_fullname;
	private JLabel label_gender;
	private JLabel label_birthday;
	private JLabel label_phonenumber;
	private JLabel label_wage;
	private JLabel label_address;
	private JLabel label_image;

	public static void main(String args[]) {
		new Employee_VIEW(null);
	}

	public Employee_VIEW(String employeeid) {
		GUI();
		employee = Employee_BLL.getInstance().getEmployeeByID(employeeid);
		 // Automatically trigger the click event for label_info
		label_info.dispatchEvent(new MouseEvent(
		        label_info, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 
		        0, 0, 1, false
		    ));
	}

	public void GUI() {
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
		panel.setBackground(new Color(252, 166, 100));
		panel.setBounds(0, 50, screenSize.width, 45);
		contentPane.add(panel);
		panel.setLayout(null);

		// Panel show information of employee
		panel_info = new JPanel();
		panel_info.setBackground(new Color(254, 215, 124));
		panel_info.setBounds(145, 140, 1375, 500);
		contentPane.add(panel_info);
		panel_info.setLayout(null);
		panel_info.setVisible(false);
		
		// Click label to show label_info
        label_info = new JLabel("Thông tin");
        label_info.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_info.setBounds(0, 0, 120, 45);
        label_info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label_info.setHorizontalAlignment(SwingConstants.CENTER);
        label_info.setOpaque(true);
        label_info.setBackground(new Color(252, 166, 100));
        label_info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	displayChoice(label_info, panel_info);
        		ShowInfo(employee);
            }
        });
        panel.add(label_info);
        
        // GUI of label_info
        GUIpanel_info();
        
        panel_password = new JPanel();
        panel_password.setBackground(new Color(254, 214, 126));
        panel_password.setBounds(250, 200, 800, 400);
        contentPane.add(panel_password);
        panel_password.setLayout(null);
        panel_password.setVisible(false);
        
        label_schedule = new JLabel("Lịch làm");
        label_schedule.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_schedule.setHorizontalAlignment(SwingConstants.CENTER);
		label_schedule.setVerticalAlignment(SwingConstants.CENTER);
        label_schedule.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label_schedule.setOpaque(true);
        label_schedule.setBackground(new Color(252, 166, 100));
        label_schedule.setBounds(120, 0, 120, 45);
        label_schedule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	displayChoice(label_schedule, panel_schedule);
            }
        });
        panel.add(label_schedule);
        
        panel_schedule = new JPanel();
		panel_schedule.setBackground(new Color(254, 215, 124));
		panel_schedule.setBounds(110, 120, 450, 100);
		contentPane.add(panel_schedule);
		panel_schedule.setLayout(null);
		
		GUI_panelschedule();
        
        label_workhour = new JLabel("Số giờ làm");
        label_workhour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_workhour.setHorizontalAlignment(SwingConstants.CENTER);
		label_workhour.setVerticalAlignment(SwingConstants.CENTER);
        label_workhour.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label_workhour.setBounds(240, 0, 120, 45);
        panel.add(label_workhour);
        
        label_password = new JLabel("Mật khẩu");
        label_password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_password.setHorizontalAlignment(SwingConstants.CENTER);
		label_password.setVerticalAlignment(SwingConstants.CENTER);
        label_password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label_password.setOpaque(true);
        label_password.setBackground(new Color(252, 166, 100));
        label_password.setBounds(360, 0, 120, 45);
        label_password.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	displayChoice(label_password, panel_password);
        		ShowPassword(employee.getEmployeeID());
            }
        });
        panel.add(label_password);
        
        GUIpanel_password();
        
       
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
        
        label_employeeid = new JLabel();
        label_employeeid.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_employeeid.setBounds(220, 60, 150, 25);
        panel_info.add(label_employeeid);
        
        label_fullname = new JLabel();
        label_fullname.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_fullname.setBounds(220, 110, 500, 25);
        panel_info.add(label_fullname);
        
        label_gender = new JLabel();
        label_gender.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_gender.setBounds(220, 160, 120, 25);
        panel_info.add(label_gender);
        
        label_birthday = new JLabel();
        label_birthday.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_birthday.setBounds(520, 160, 200, 25);
        panel_info.add(label_birthday);
        
        label_phonenumber = new JLabel();
        label_phonenumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_phonenumber.setBounds(220, 210, 140, 25);
        panel_info.add(label_phonenumber);
        
        JLabel lblNewLabel_7 = new JLabel("Lương 1h");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_7.setBounds(400, 210, 100, 25);
        panel_info.add(lblNewLabel_7);
        
        label_wage = new JLabel();
        label_wage.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_wage.setBounds(520, 210, 200, 25);
        panel_info.add(label_wage);
        
        label_address = new JLabel();
        label_address.setFont(new Font("Tahoma", Font.PLAIN, 20));
        label_address.setBounds(220, 260, 500, 25);
        panel_info.add(label_address);
	}

	public void ShowInfo(Employee employee) {
		label_employeeid.setText(employee.getEmployeeID());
        label_fullname.setText(employee.getFullname());
        label_gender.setText(employee.getGender());
        label_birthday.setText(employee.getBirthday());
        label_phonenumber.setText(employee.getPhone_number());
        label_wage.setText(employee.getWage_coefficient() + " đồng");
        label_address.setText(employee.getAddress());
        String s = employee.getImage();
        ImageIcon icon = new ImageIcon(getClass().getResource(s));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(label_image.getWidth(), label_image.getHeight(),
        		Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        label_image.setIcon(scaledIcon);

	}
	
	public void GUIpanel_password() {
        JLabel lblNewLabel = new JLabel("Mật khẩu cũ:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setBounds(80, 50, 135, 25);
        panel_password.add(lblNewLabel);
        
        tf_oldpass = new RoundedIconTextField();
        tf_oldpass.setFont(new Font("Tahoma", Font.PLAIN, 20));

        tf_oldpass.setSuffixIcon("/IMAGE/iconcloseye.png");
        tf_oldpass.setBounds(270, 45, 300, 40);
        panel_password.add(tf_oldpass);
        tf_oldpass.setColumns(10);
//        tf_oldpass.setText("12345678");
        tf_oldpass.setEditable(false);
        
        JLabel lblMtKhuMi = new JLabel("Mật khẩu mới:");
        lblMtKhuMi.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblMtKhuMi.setHorizontalAlignment(SwingConstants.RIGHT);
//        lblMtKhuMi.setVerticalAlignment(SwingConstants.RIGHT);
        lblMtKhuMi.setBounds(65, 115, 150, 25);
        panel_password.add(lblMtKhuMi);
        
        tf_newpass = new RoundTextField();
        tf_newpass.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tf_newpass.setBounds(270, 110, 300, 40);
        panel_password.add(tf_newpass);
        
        JLabel lblNhpLiMt = new JLabel("Nhập lại mật khẩu:");
        lblNhpLiMt.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNhpLiMt.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNhpLiMt.setBounds(15, 180, 200, 25);
        panel_password.add(lblNhpLiMt);
        
        RoundTextField tf_checkpass = new RoundTextField();
        tf_checkpass.setFont(new Font("Tahoma", Font.PLAIN, 20));
        tf_checkpass.setBounds(270, 175, 300, 40);
        panel_password.add(tf_checkpass);
        
        button_OK = new JButton("Lưu thay đổi");
        button_OK.setBackground(new Color(0, 128, 255));
        button_OK.setBounds(270, 230, 300, 20);
        button_OK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(tf_checkpass.getText().compareTo(tf_newpass.getText()) == 0) {
        			Account_BLL.getInstance().UpdatePassword(employee.getEmployeeID(), tf_newpass.getText());
        			JOptionPane.showMessageDialog(null, "Da cap nhat mat khau thanh cong");
        			
        			// clear text field
        			tf_checkpass.setText("");
        			tf_newpass.setText("");
        			
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Mat khau vua xac nhan khong trung khop");
        		}
        	}
        });
        panel_password.add(button_OK);
	}
	
	public void ShowPassword(String employeeid) {
		Account user = Account_BLL.getInstance().GetUserByEmployeeID(employeeid);
//		tf_oldpass = new RoundedIconTextField();
		tf_oldpass.setText(user.getPassword());
	}
	
	private void GUI_panelschedule() {
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
		
		JLabel label_displayresult = new JLabel();
		label_displayresult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_displayresult.setBounds(120, 50, 300, 25);
		panel_schedule.add(label_displayresult);
		
		button_showschedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		        String selectedDate = sdf.format(dateChooser.getDate());
		        String result = Workschedule_BLL.getInstance().getWorkScheduleByEmployeeAndDate(selectedDate, employee.getEmployeeID());
		        label_displayresult.setText(result);
			}
		});
	}
	
	private void displayChoice(JLabel label, JPanel panel) {
		label_info.setBackground(new Color(252, 166, 100));
		label_schedule.setBackground(new Color(252, 166, 100));
		label_workhour.setBackground(new Color(252, 166, 100));
		label_password.setBackground(new Color(252, 166, 100));
		
		label.setBackground(new Color(249, 230, 186));
		
		panel_info.setVisible(false);
		panel_schedule.setVisible(false);
		panel_password.setVisible(false);
		
		panel.setVisible(true);
	}
}
