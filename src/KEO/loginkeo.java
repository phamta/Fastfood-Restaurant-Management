package KEO;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;

public class loginkeo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_username;
	private JPasswordField tf_password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginkeo frame = new loginkeo();
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
	public loginkeo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_input = new JPanel();
		panel_input.setBackground(new Color(255, 0, 0));
		panel_input.setBounds(0, 0, 220, 265);
		contentPane.add(panel_input);
		panel_input.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(65, 49, 100, 30);
		panel_input.add(lblNewLabel);
		
		tf_username = new JTextField();
		tf_username.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tf_username.setText("Tên đăng nhập");
		tf_username.setBounds(35, 90, 150, 30);
		panel_input.add(tf_username);
		tf_username.setColumns(10);
		
		tf_password = new JPasswordField();
		tf_password.setEchoChar('*');
		tf_password.setBounds(35, 130, 150, 30);
		panel_input.add(tf_password);
		
		JButton button_login = new JButton("Đăng nhập");
		button_login.setBounds(60, 170, 100, 20);
		panel_input.add(button_login);
		
		JPanel panel_icon = new JPanel();
		panel_icon.setBounds(220, 0, 220, 265);
		contentPane.add(panel_icon);
	}
}
