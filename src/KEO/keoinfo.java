package KEO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.components.JSpinField;

import EDIT.RoundTextField;
import EDIT.RoundedIconTextField;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class keoinfo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RoundedIconTextField tf_oldpass;
	private RoundTextField tf_newpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keoinfo frame = new keoinfo();
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
	public keoinfo() {
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
        
        JLabel label_info = new JLabel("Thông tin");
        label_info.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        label_info.setBackground(new Color(254, 215, 124));
        label_info.setOpaque(false);
		label_info.setHorizontalAlignment(SwingConstants.CENTER);
		label_info.setVerticalAlignment(SwingConstants.CENTER);
        label_info.setBounds(0, 0, 120, 45);
        label_info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                panel_info.setVisible(true);
                label_info.setOpaque(true);
                label_info.setBackground(new Color(254,39,60));
//                label_info.setBackground(Color.red);
            }
        });
        label_info.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(label_info);
        
        JLabel label_schedule = new JLabel("Lịch làm");
        label_schedule.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_schedule.setHorizontalAlignment(SwingConstants.CENTER);
		label_schedule.setVerticalAlignment(SwingConstants.CENTER);
        label_schedule.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label_schedule.setBounds(120, 0, 120, 45);
        panel.add(label_schedule);
        
        JLabel label_workhour = new JLabel("Số giờ làm");
        label_workhour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_workhour.setHorizontalAlignment(SwingConstants.CENTER);
		label_workhour.setVerticalAlignment(SwingConstants.CENTER);
        label_workhour.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label_workhour.setBounds(240, 0, 120, 45);
        panel.add(label_workhour);
        
        JLabel label_password = new JLabel("Thông tin");
        label_password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_password.setHorizontalAlignment(SwingConstants.CENTER);
		label_password.setVerticalAlignment(SwingConstants.CENTER);
        label_password.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label_password.setBounds(360, 0, 120, 45);
        panel.add(label_password);
        
        JPanel panel_password = new JPanel();
        panel_password.setBackground(new Color(254, 214, 126));
        panel_password.setBounds(250, 200, 800, 400);
        contentPane.add(panel_password);
        panel_password.setLayout(null);
        
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
        tf_oldpass.setText("12345678");
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
        
        JButton button_OK = new JButton("Lưu thay đổi");
        button_OK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        button_OK.setBackground(new Color(0, 128, 255));
        button_OK.setBounds(270, 230, 300, 20);
        panel_password.add(button_OK);
        
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		
		comboBoxModel.addElement("Nam");
		comboBoxModel.addElement("Nữ");
		comboBoxModel.addElement("Khác");
        
	}
}
