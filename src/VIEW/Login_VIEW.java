package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.Account_BLL;
import DTO.Account;

public class Login_VIEW extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_username;
	private JPasswordField tf_password;
	private JPanel panel_input;
	private JLabel lblNewLabel;
	private JButton button_login;
	private JPanel panel_icon;
	
	public void GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_input = new JPanel();
		panel_input.setBackground(new Color(254, 215, 124));
		panel_input.setBounds(0, 0, 220, 265);
		contentPane.add(panel_input);
		panel_input.setLayout(null);
		
		lblNewLabel = new JLabel("Đăng nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(65, 49, 100, 30);
		panel_input.add(lblNewLabel);
		
		tf_username = new JTextField();
		tf_username.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tf_username.setText("tanvan");
		tf_username.setBounds(35, 90, 150, 30);
		panel_input.add(tf_username);
		tf_username.setColumns(10);
		
		tf_password = new JPasswordField("12345678");
		tf_password.setEchoChar('*');
		tf_password.setBounds(35, 130, 150, 30);
		tf_password.addActionListener(this);
		panel_input.add(tf_password);
		
		button_login = new JButton("Đăng nhập");
		button_login.setBounds(60, 170, 100, 20);
		button_login.addActionListener(this);
		panel_input.add(button_login);
		
		panel_icon = new JPanel();
		panel_icon.setBounds(220, 0, 220, 265);
		contentPane.add(panel_icon);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button_login || e.getSource() == tf_password) {
			String username = tf_username.getText();
			char[] temp = tf_password.getPassword();
			String password = new String(temp);
			Account account = Account_BLL.getInstance().GetUserByLogin(username, password);
			
			if(account != null) {
//				JOptionPane.showMessageDialog(contentPane, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
				Menu_VIEW menu = new Menu_VIEW(account.getUserID());
				menu.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(contentPane, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public Login_VIEW() {
		GUI();
	}
	
	public static void main(String[] args) {
		new Login_VIEW();
	}

}
