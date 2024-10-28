package KEO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import EDIT.RoundedPanel;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class keowork extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RoundedPanel panel_content;
	private JPanel panel_menubar;
	private RoundedPanel panel_detail;
	private JLabel lblNewLabel_9;
	private JDateChooser dateChooser_endday;
	private JDateChooser dateChooser_startday;
	private JScrollPane scrollPane_1;
	private JPanel panel_listdate;
	private JPanel panel_listemployeedelete;
	private JPanel panel_2;
	private JPanel panel_listaddemployee;
	private JScrollPane scrollPane_listemployeedelete;
	private JScrollPane scrollPane_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keowork frame = new keowork();
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
	public keowork() {
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
		panel_content.setBounds(310, 30, 700, 730);
		contentPane.add(panel_content);
		panel_content.setLayout(null);

//		JLabel lblNewLabel_8 = new JLabel("Ngày 22/12/2024");
//		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
//		lblNewLabel_8.setBounds(10, 100, 120, 50);
//		lblNewLabel_8.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		panel_content.add(lblNewLabel_8);

		lblNewLabel_9 = new JLabel("Lịch làm việc");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_9.setBounds(10, 50, 660, 50);
		lblNewLabel_9.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_content.add(lblNewLabel_9);

		dateChooser_startday = new JDateChooser();
		dateChooser_startday.setDateFormatString("dd/MM/yyyy");
		dateChooser_startday.setBounds(110, 10, 130, 30);
		dateChooser_startday.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_content.add(dateChooser_startday);

		dateChooser_endday = new JDateChooser();
		dateChooser_endday.setDateFormatString("dd/MM/yyyy");
		dateChooser_endday.setBounds(355, 10, 130, 30);
		dateChooser_endday.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_content.add(dateChooser_endday);
		
		JLabel lblNewLabel = new JLabel("Start date:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 15, 90, 25);
		panel_content.add(lblNewLabel);
		
		JLabel lblEndDate = new JLabel("End date:");
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEndDate.setBounds(260, 15, 80, 25);
		panel_content.add(lblEndDate);
		
		JLabel label_showschedule = new JLabel("Phân công");
		label_showschedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showlistdate();
			}
		});
		label_showschedule.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_showschedule.setBounds(510, 10, 120, 30);
		label_showschedule.setHorizontalAlignment(SwingConstants.CENTER);
		panel_content.add(label_showschedule);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 100, 660, 600);
		panel_content.add(scrollPane_1);
		
		panel_listdate = new JPanel();
		scrollPane_1.setViewportView(panel_listdate);
		panel_listdate.setBackground(new Color(249, 230, 186));
		panel_listdate.setLayout(null);

		panel_detail = new RoundedPanel(50, new Color(249, 230, 186));
		panel_detail.setBounds(1045, 30, 450, 730);
		contentPane.add(panel_detail);
		panel_detail.setLayout(null);
		
		JLabel label_add = new JLabel("Thêm");
		label_add.addMouseListener(new MouseAdapter() {
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
		label_add.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_add.setBounds(20, 15, 100, 30);
		label_add.setHorizontalAlignment(SwingConstants.CENTER);
		label_add.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_detail.add(label_add);
		
		JLabel label_delete = new JLabel("Xóa");
		label_delete.addMouseListener(new MouseAdapter() {
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
		label_delete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_delete.setBounds(330, 15, 100, 30);
		label_delete.setHorizontalAlignment(SwingConstants.CENTER);
		label_delete.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_detail.add(label_delete);
		
		scrollPane_listemployeedelete = new JScrollPane();
		scrollPane_listemployeedelete.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_listemployeedelete.setBounds(0, 100, 450, 200);
		panel_detail.add(scrollPane_listemployeedelete);
		
		panel_listemployeedelete = new JPanel();
		scrollPane_listemployeedelete.setViewportView(panel_listemployeedelete);
		panel_listemployeedelete.setBackground(new Color(249, 230, 186));
		panel_listemployeedelete.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 450, 40);
		panel_listemployeedelete.add(panel_2);
		panel_2.setBackground(new Color(249, 230, 186));
		panel_2.setLayout(null);
		
		JLabel label_nameemployee = new JLabel("Văn Bá Phạm Tấn");
		label_nameemployee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_nameemployee.setBounds(30, 0, 300, 30);
		panel_2.add(label_nameemployee);
		
		JLabel label_startime = new JLabel("07:30");
		label_startime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_startime.setBounds(335, 0, 50, 30);
		panel_2.add(label_startime);
		
		JLabel label_endtime = new JLabel("17:30");
		label_endtime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_endtime.setBounds(395, 0, 50, 30);
		panel_2.add(label_endtime);
		
		JCheckBox checkbox_delete = new JCheckBox("New check box");
		checkbox_delete.setBackground(new Color(249, 230, 186));
		checkbox_delete.setBounds(0, 0, 20, 30);
		panel_2.add(checkbox_delete);
		
		JLabel label_titlename = new JLabel("Nhân viên");
		label_titlename.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_titlename.setBounds(20, 60, 100, 30);
		panel_detail.add(label_titlename);
		
		JLabel label_titlestartime = new JLabel("Bắt đầu");
		label_titlestartime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_titlestartime.setBounds(300, 60, 70, 30);
		panel_detail.add(label_titlestartime);
		
		JLabel label_titleendtime = new JLabel("Kết thúc");
		label_titleendtime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_titleendtime.setBounds(370, 60, 70, 30);
		panel_detail.add(label_titleendtime);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(0, 310, 450, 390);
		panel_detail.add(scrollPane_3);
		
		panel_listaddemployee = new JPanel();
		scrollPane_3.setViewportView(panel_listaddemployee);
		panel_listaddemployee.setBackground(new Color(249, 230, 186));
		panel_listaddemployee.setLayout(null);

//		ImageIcon icon = new ImageIcon(getClass().getResource("/IMAGE/avatar001.jpg"));
//		Image image = icon.getImage();
//		ImageIcon scaledIcon = new ImageIcon(image);
	}
	
	private void showlistdate() {
        Date startDate = dateChooser_startday.getDate();
        Date endDate = dateChooser_endday.getDate();
        
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        panel_listdate.removeAll();
        int x = 0, y =0;
        for(int i=0; i<=diff; ++i) {
            if (diff > 11) {
                panel_listdate.setPreferredSize(new Dimension(660, 50 * (int) (diff + 1))); // +1 for including the last date
                scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            } else {
                panel_listdate.setPreferredSize(null); // Use default size
                scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            }
            Date date = new Date(startDate.getTime() + TimeUnit.DAYS.toMillis(i));
            String formattedDate = dateFormat.format(date);

            // Add date label
            JLabel dateLabel = new JLabel(formattedDate);
            dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
            dateLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            dateLabel.setBounds(x, y, 120, 50);
            panel_listdate.add(dateLabel);
            
            x += 120;
            panel_listdate.add(createEmployeeTextArea(formattedDate, x, y, 540, 50));
            x = 0;
            y += 50;
            System.out.println("check-277 " + diff);
        }
        panel_listdate.revalidate();
        panel_listdate.repaint();
	}

	private JScrollPane createEmployeeTextArea(String date, int x, int y, int width, int height) {
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArea.setBackground(new Color(249, 230, 186));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(x, y, width, height);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Date: " + date);
			}
		});

		return scrollPane;
	}
}
