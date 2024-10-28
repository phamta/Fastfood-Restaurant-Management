package VIEW;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import BLL.Employee_BLL;
import BLL.Workschedule_BLL;
import DTO.Employee;
import DTO.Workschedule;
import EDIT.RoundedPanel;

public class Workschedule_VIEW extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private RoundedPanel panel_content;
	private JDateChooser dateChooser_startday;
	private JLabel label_title;
	private JDateChooser dateChooser_endday;
	private JScrollPane scrollPane_1;
	private JPanel panel_listdate;
	private RoundedPanel panel_detail;

	private String date;
	private JScrollPane scrollPane_2;
	private JPanel panel_listemployee;
	private JScrollPane scrollPane_3;
	private JPanel panel_listaddemployee;
	private RoundedPanel panel_delete;
	private JScrollPane scrollPane_listemployeedelete;
	private JPanel panel_listemployeedelete;
	
    private Map<JCheckBox, Workschedule> checkboxWorkscheduleMap = new HashMap<>();

	public Workschedule_VIEW(int radius, Color backgroundColor) {
		super(radius, backgroundColor);
		setBounds(0, 0, 1190, 730);
		setLayout(null);

		panel_content = new RoundedPanel(50, new Color(249, 230, 186));
		panel_content.setBounds(0, 0, 700, 730);
		this.add(panel_content);
		panel_content.setLayout(null);

		label_title = new JLabel("Lịch làm việc");
		label_title.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_title.setBounds(10, 50, 660, 50);
		label_title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		label_title.setHorizontalAlignment(SwingConstants.CENTER);
		panel_content.add(label_title);

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

//		// Get day month year right now
//		Calendar calendar = Calendar.getInstance();
//		Date currentDate = calendar.getTime();
//
//		// set value day month year right now for component
//		dateChooser_startday.setDate(currentDate);
//		dateChooser_endday.setDate(currentDate);

		// Parse the date string to a Date object

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = dateFormat.parse("31/07/2024");
			// Set the date for both date choosers
			dateChooser_endday.setDate(date);
			dateChooser_startday.setDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


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
		panel_detail.setBounds(730, 0, 450, 730);
		this.add(panel_detail);
		panel_detail.setLayout(null);

		showlistdate();
		GUI_paneldelete();
	}

	private void GUI_detail() {
		JLabel label_add = new JLabel("Thêm");
		label_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane_2.setBounds(0, 100, 450, 200);
				scrollPane_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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
				panel_detail.removeAll();
				panel_detail.add(panel_delete);
				panel_delete.setVisible(true);
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
		label_delete.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_detail.add(label_delete);

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

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 100, 450, 600);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_detail.add(scrollPane_2);

		panel_listemployee = new JPanel();
		scrollPane_2.setViewportView(panel_listemployee);
		panel_listemployee.setBackground(new Color(249, 230, 186));
//		panel_listemployee.setBackground(Color.red);
//		panel_listemployee.setBounds(0, 100, 450, 200);
		panel_listemployee.setLayout(null);
//		panel_detail.add(panel_listemployee);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(0, 310, 450, 390);
		panel_detail.add(scrollPane_3);
		
		panel_listaddemployee = new JPanel();
		scrollPane_3.setViewportView(panel_listaddemployee);
		panel_listaddemployee.setBackground(new Color(249, 230, 186));
		panel_listaddemployee.setLayout(null);
		
		GUI_paneldelete();

		panel_detail.revalidate();
		panel_detail.repaint();
	}
	
	private void GUI_detailempty() {
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(0, 0, 450, 700);
		panel_detail.add(scrollPane_3);
		
		panel_listaddemployee = new JPanel();
		scrollPane_3.setViewportView(panel_listaddemployee);
		panel_listaddemployee.setBackground(new Color(249, 230, 186));
		panel_listaddemployee.setLayout(null);

		panel_detail.revalidate();
		panel_detail.repaint();
	}
	
	private void GUI_paneldelete() {
		panel_delete = new RoundedPanel(50, new Color(249, 230, 186));
		panel_delete.setBounds(0, 0, 450, 730);
		panel_delete.setLayout(null);
		panel_detail.add(panel_delete);
		panel_delete.setVisible(false);
		
		JLabel label_delete = new JLabel("Xóa");
		label_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Workschedule> list = getSelectedWorkschedules();
				Workschedule_BLL.getInstance().delete(list);
				panel_delete.setVisible(false);
				showlistdate();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_delete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_delete.setBounds(20, 15, 100, 30);
		label_delete.setHorizontalAlignment(SwingConstants.CENTER);
		label_delete.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_delete.add(label_delete);
		
		JLabel label_cancel = new JLabel("Hủy");
		label_cancel.addMouseListener(new MouseAdapter() {
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
		label_cancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_cancel.setBounds(330, 15, 100, 30);
		label_cancel.setHorizontalAlignment(SwingConstants.CENTER);
		label_cancel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_delete.add(label_cancel);
		
		scrollPane_listemployeedelete = new JScrollPane();
		scrollPane_listemployeedelete.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_listemployeedelete.setBounds(0, 100, 450, 600);
		panel_delete.add(scrollPane_listemployeedelete);
		
		panel_listemployeedelete = new JPanel();
		scrollPane_listemployeedelete.setViewportView(panel_listemployeedelete);
		panel_listemployeedelete.setBackground(new Color(249, 230, 186));
		panel_listemployeedelete.setLayout(null);
		
	}

	private void showlistdate() {
		Date startDate = dateChooser_startday.getDate();
		Date endDate = dateChooser_endday.getDate();

		long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		panel_listdate.removeAll();
		int x = 0, y = 0;
		for (int i = 0; i <= diff; ++i) {
			if (diff > 11) {
				panel_listdate.setPreferredSize(new Dimension(660, 50 * (int) (diff + 1))); // +1 for including the last
																							// date
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
//			System.out.println("check-277 " + diff);
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
		textArea.setText(Workschedule_BLL.getInstance().getWorkSchedulesByDate(date));
//		textArea.s
		textArea.putClientProperty("date", date);
		List<Workschedule> workschedules = Workschedule_BLL.getInstance().getWorkschedulelist(date);
		List<String> list_available = Workschedule_BLL.getInstance().getEmployeeIDsByDate(date);
		List<Employee> list = Employee_BLL.getInstance().getEmployeesNotInList(list_available);
		textArea.putClientProperty("list", list);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(x, y, width, height);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list_available.isEmpty()) {
					GUI_detailempty();
					displayEmployee(list);
				} else {
					GUI_detail();
					displayEmployeeavailable(workschedules);
					displayEmployee(list);
					displayPanelDelete(workschedules);
				}
				Workschedule_VIEW.this.date = date;
			}
		});

		return scrollPane;
	}

	public RoundedPanel createEmployeePanel(Employee employee, int x, int y) {
		RoundedPanel roundedpanel = new RoundedPanel(30, new Color(249, 230, 186));
		roundedpanel.setBounds(x, y, 450, 80);
		panel_detail.add(roundedpanel);
		roundedpanel.setLayout(null);

		JLabel label_avatar = new JLabel("");
		label_avatar.setBounds(10, 10, 80, 60);
		label_avatar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		roundedpanel.add(label_avatar);

		ImageIcon icon = new ImageIcon(getClass().getResource(employee.getImage()));
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(label_avatar.getWidth(), label_avatar.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label_avatar.setIcon(scaledIcon);

		JLabel lblNewLabel_1 = new JLabel(employee.getFullname());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(120, 0, 225, 30);
		roundedpanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("In");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(130, 40, 20, 30);
		roundedpanel.add(lblNewLabel_2);

		JComboBox<String> comboBox_hourin = new JComboBox<>();
		comboBox_hourin.setBounds(155, 40, 50, 30);
		// Add items from "00" to "23"
		for (int i = 0; i < 24; i++) {
			comboBox_hourin.addItem(String.format("%02d", i));
		}
		comboBox_hourin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		roundedpanel.add(comboBox_hourin);

		JLabel lblNewLabel_3 = new JLabel(":");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(210, 40, 10, 30);
		roundedpanel.add(lblNewLabel_3);

		JComboBox<String> comboBox_minutein = new JComboBox<String>();
		comboBox_minutein.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_minutein.setBounds(219, 40, 50, 30);
		comboBox_minutein.addItem("00");
		comboBox_minutein.addItem("15");
		comboBox_minutein.addItem("30");
		comboBox_minutein.addItem("45");
		roundedpanel.add(comboBox_minutein);

		JLabel lblNewLabel_4 = new JLabel("Out");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(275, 40, 40, 30);
		roundedpanel.add(lblNewLabel_4);

		JComboBox<String> comboBox_hourout = new JComboBox<String>();
		comboBox_hourout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_hourout.setBounds(315, 40, 50, 30);
		// Add items from "00" to "23"
		for (int i = 0; i < 24; i++) {
			comboBox_hourout.addItem(String.format("%02d", i));
		}
		roundedpanel.add(comboBox_hourout);

		JLabel lblNewLabel_5 = new JLabel(":");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(370, 40, 10, 30);
		roundedpanel.add(lblNewLabel_5);

		JComboBox<String> comboBox_minuteout = new JComboBox<String>();
		comboBox_minuteout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_minuteout.setBounds(380, 40, 50, 30);
		comboBox_minuteout.addItem("00");
		comboBox_minuteout.addItem("15");
		comboBox_minuteout.addItem("30");
		comboBox_minuteout.addItem("45");
		roundedpanel.add(comboBox_minuteout);

		roundedpanel.putClientProperty("employeeid", employee.getEmployeeID());

		roundedpanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Workschedule workschedule = new Workschedule();
				workschedule.setDate(date);
				String start_time = comboBox_hourin.getSelectedItem().toString() + ":"
						+ comboBox_minutein.getSelectedItem().toString();
				workschedule.setStart_time(start_time);
				String end_time = comboBox_hourout.getSelectedItem().toString() + ":"
						+ comboBox_minuteout.getSelectedItem().toString();
				workschedule.setEnd_time(end_time);
				workschedule.setEmployeeID(employee.getEmployeeID());
				
				Workschedule_BLL.getInstance().insert(workschedule);
				JOptionPane.showMessageDialog(null, workschedule.toString());
				showlistdate();
				panel_detail.removeAll();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				roundedpanel.setCursor(cursor);
				roundedpanel.setBackground(new Color(255, 233, 181));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				roundedpanel.setBackground(new Color(249, 230, 186));
			}
		});

		return roundedpanel;
	}

	public void displayEmployee(List<Employee> list) {
		int x = 0, y = 30;
		for (Employee employee : list) {
			RoundedPanel roundedPanel = createEmployeePanel(employee, x, y);
			panel_listaddemployee.add(roundedPanel);
			y += 90;
		}
		panel_listaddemployee.revalidate();
		panel_listaddemployee.repaint();
	}

	private JPanel createEmployeePanel(Workschedule workschedule, int y) {
		JPanel panel = new JPanel();
		panel.setBounds(0, y, 450, 40);
//		panel_detail.add(panel);
		panel.setBackground(new Color(249, 230, 186));
		panel.setLayout(null);

		JLabel label_nameemployee = new JLabel(workschedule.getEmployeeName());
		label_nameemployee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_nameemployee.setBounds(5, 0, 300, 30);
		panel.add(label_nameemployee);

		JLabel label_startime = new JLabel(workschedule.getStart_time());
		label_startime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_startime.setBounds(310, 0, 50, 30);
		panel.add(label_startime);

		JLabel label_endtime = new JLabel(workschedule.getEnd_time());
		label_endtime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_endtime.setBounds(380, 0, 50, 30);
		panel.add(label_endtime);

		return panel;
	}

	private void displayEmployeeavailable(List<Workschedule> list) {
		int y = 0;
		panel_listemployee.removeAll();
//		panel_listemployee.setPreferredSize(new Dimension(450,600));
		if(list.size() < 15) {
			scrollPane_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		}
		else {
			scrollPane_2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		for (Workschedule workschedule : list) {
			JPanel panel = createEmployeePanel(workschedule, y);
			panel_listemployee.add(panel);
			y += 40;
		}

		panel_listemployee.revalidate();
		panel_listemployee.repaint();
	}
	
	private JPanel createPanelDelete(Workschedule workschedule, int y) {
		JPanel panel = new JPanel();
		panel.setBounds(0, y, 450, 40);
		panel.setBackground(new Color(249, 230, 186));
		panel.setLayout(null);
		
		JCheckBox checkbox_delete = new JCheckBox();
		checkbox_delete.setBackground(new Color(249, 230, 186));
		checkbox_delete.setBounds(0, 0, 20, 30);
		panel.add(checkbox_delete);
		

        // Store the check box and work schedule in the map
        checkboxWorkscheduleMap.put(checkbox_delete, workschedule);
		
		JLabel label_nameemployee = new JLabel(workschedule.getEmployeeName());
		label_nameemployee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_nameemployee.setBounds(30, 0, 300, 30);
		panel.add(label_nameemployee);
		
		JLabel label_startime = new JLabel(workschedule.getStart_time());
		label_startime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_startime.setBounds(335, 0, 50, 30);
		panel.add(label_startime);
		
		JLabel label_endtime = new JLabel(workschedule.getEnd_time());
		label_endtime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_endtime.setBounds(395, 0, 50, 30);
		panel.add(label_endtime);
		
		return panel;
	}
	
	private void displayPanelDelete(List<Workschedule> list) {
		int y = 0;
		panel_listemployeedelete.removeAll();
		for(Workschedule workschedule: list) {
			JPanel panel = createPanelDelete(workschedule, y);
			panel_listemployeedelete.add(panel);
			y += 40;
		}
		panel_listemployeedelete.revalidate();
		panel_listemployeedelete.repaint();
	}
	
    // Method to retrieve the selected workschedules
    public List<Workschedule> getSelectedWorkschedules() {
        List<Workschedule> selectedWorkschedules = new ArrayList<Workschedule>();
        for (Map.Entry<JCheckBox, Workschedule> entry : checkboxWorkscheduleMap.entrySet()) {
            if (entry.getKey().isSelected()) {
                selectedWorkschedules.add(entry.getValue());
            }
        }
        return selectedWorkschedules;
    }
}
