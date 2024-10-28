package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.Itemplus;

public class Detail_VIEW extends JFrame implements ActionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lable_soluong;
	private JLabel lable_cong;
	private JLabel lable_name;
	private JButton button_huy;
	private JButton button_them;
	
	private static Menu_VIEW menu;
	private static Itemplus itemplus;
	
	public void GUI(Itemplus itemplus) {
		setBounds(100, 100, 310, 260);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// label image
		JLabel lable_image = new JLabel("");
		lable_image.setBounds(0, 0, 300, 120);
        ImageIcon icon = new ImageIcon(getClass().getResource(itemplus.getImagePath()));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(300, 120, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lable_image.setIcon(scaledIcon);
		contentPane.add(lable_image);
		
		// label name
		lable_name = new JLabel(itemplus.getItemName());
		lable_name.setFont(new Font("Tahoma", Font.BOLD, 15));
		lable_name.setBounds(0, 130, 120, 20);
		contentPane.add(lable_name);
		
		JLabel lblNewLabel = new JLabel("Số lượng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(160, 130, 80, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lable_tru = new JLabel("-");
		lable_tru.setFont(new Font("Tahoma", Font.BOLD, 30));
		lable_tru.setForeground(new Color(255, 0, 0));
		lable_tru.setBounds(155, 160, 30, 20);
		lable_tru.addMouseListener(this);
//		icon = new ImageIcon("C:\\Users\\LENOVO\\Downloads\\tru3.jpeg");
//        image = icon.getImage();
//        scaledImage = image.getScaledInstance(15, 10, Image.SCALE_SMOOTH);
//        scaledIcon = new ImageIcon(scaledImage);
//        lable_tru.setIcon(scaledIcon);
		contentPane.add(lable_tru);
		
		lable_cong = new JLabel("+");
		lable_cong.setFont(new Font("Tahoma", Font.BOLD, 25));
		lable_cong.setForeground(new Color(255, 0, 0));
		lable_cong.setBounds(220, 160, 30, 20);
		lable_cong.addMouseListener(this);
		contentPane.add(lable_cong);
		
		lable_soluong = new JLabel("1");
		lable_soluong.setFont(new Font("Tahoma", Font.BOLD, 18));
		lable_soluong.setForeground(new Color(255, 0, 0));
		lable_soluong.setBounds(190, 165, 30, 13);
		
		contentPane.add(lable_soluong);
		
		button_huy = new JButton("Hủy");
		button_huy.setBackground(new Color(255, 0, 0));
		button_huy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_huy.setBounds(50, 190, 60, 25);
		button_huy.addActionListener(this);
		contentPane.add(button_huy);
		
		button_them = new JButton("Thêm");
		button_them.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_them.setBackground(new Color(0, 255, 0));
		button_them.setBounds(160, 190, 65, 25);
		button_them.addActionListener(this);
		contentPane.add(button_them);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button_huy) {
			this.dispose();
		}
		
		else {
			int quantity = Integer.parseInt(lable_soluong.getText());
			Detail_VIEW.itemplus.setQuantity(quantity);
			Detail_VIEW.menu.detailtomenu(itemplus);
//			Test.receiveData(lable_name.getText(), quantity);
			this.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		JOptionPane.showMessageDialog(null, "Bạn đã chọn Add");
		int soluong = Integer.parseInt(lable_soluong.getText());
		if(e.getSource() == lable_cong) {
			++soluong;
			lable_soluong.setText("" + soluong);
		}
		else {
			if(soluong != 1) {
				--soluong;
				lable_soluong.setText(""+soluong);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public Detail_VIEW(Menu_VIEW menu, Itemplus itemplus) {
		Detail_VIEW.menu = menu;
		Detail_VIEW.itemplus = itemplus;
		
		GUI(itemplus);
	}
	
	public static void main(String args[]) {
		new Detail_VIEW(menu, itemplus);
	}
}
