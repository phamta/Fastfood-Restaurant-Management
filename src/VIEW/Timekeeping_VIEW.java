package VIEW;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JYearChooser;

import BLL.Employee_BLL;
import BLL.Timekeeping_BLL;
import BLL.Workschedule_BLL;
import DTO.Employee;
import EDIT.RoundedPanel;

public class Timekeeping_VIEW extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private JYearChooser yearChooser;
	private JComboBox<Integer> comboBox_chooseweek;
	private JLabel[] list_labeltitle = new JLabel[7];
	private String[] list_dayinweek = new String[7];
	private JTextField tf_search;
	private JPanel panel_tabletimekeeping;
	private JScrollPane scrollPane;

	public Timekeeping_VIEW(int radius, Color backgroundColor) {
		super(radius, backgroundColor);
		setBounds(0, 0, 1190, 730);
		setLayout(null);

		RoundedPanel panel_content = new RoundedPanel(50, new Color(249, 230, 186));
		panel_content.setBackground(new Color(249, 230, 186));
		panel_content.setBounds(0, 0, 1190, 730);
		panel_content.setLayout(null);
		this.add(panel_content);

		JLabel lblNewLabel = new JLabel("Tuần");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(40, 30, 50, 30);
		panel_content.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Năm");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(170, 30, 50, 30);
		panel_content.add(lblNewLabel_1);

		yearChooser = new JYearChooser();
		yearChooser.setBounds(230, 30, 60, 30);
		panel_content.add(yearChooser);

		comboBox_chooseweek = new JComboBox<Integer>();
		comboBox_chooseweek.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_chooseweek.setBounds(100, 30, 60, 30);
		panel_content.add(comboBox_chooseweek);

		JPanel panel_title = new JPanel();
		panel_title.setLayout(null);
		panel_title.setBackground(new Color(249, 230, 186));
		panel_title.setBounds(10, 100, 1140, 55);
		panel_content.add(panel_title);

		JPanel panel_search = new JPanel();
		panel_search.setBackground(new Color(249, 230, 186));
		panel_search.setBounds(0, 0, 160, 55);
		panel_search.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_title.add(panel_search);
		panel_search.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Tìm kiếm");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(5, 0, 150, 25);
		panel_search.add(lblNewLabel_3);

		tf_search = new JTextField();
		tf_search.setBackground(new Color(249, 230, 186));
		tf_search.setBounds(5, 25, 150, 25);
		panel_search.add(tf_search);
		tf_search.setColumns(10);
		tf_search.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        onSearchFieldChanged();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        onSearchFieldChanged();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        onSearchFieldChanged();
		    }

		    private void onSearchFieldChanged() {
		    	String search = tf_search.getText();
		    	List<Employee> list = Employee_BLL.getInstance().getListEmployeeBySearch(search);
				displayTableTimekeeping(list);
		    }
		});

		for (int i = 0; i < 7; ++i) {
			list_labeltitle[i] = new JLabel("", SwingConstants.CENTER);
			list_labeltitle[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
			list_labeltitle[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			list_labeltitle[i].setBounds(160 + 140 * i, 0, 140, 55);
			panel_title.add(list_labeltitle[i]);
		}

		Calendar calendar = Calendar.getInstance();
		int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		for (int i = 1; i <= currentWeek; ++i) {
			comboBox_chooseweek.addItem(i);
		}
		comboBox_chooseweek.setSelectedItem(currentWeek);
		updateWeekLabels(currentWeek);

		JLabel label_showtimekeeping = new JLabel("Xem bảng chấm công");
		label_showtimekeeping.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int week = Integer.parseInt(comboBox_chooseweek.getSelectedItem().toString()) ;
				updateWeekLabels(week);
				List<Employee> list = Employee_BLL.getInstance().getAllEmployee();
				displayTableTimekeeping(list);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_showtimekeeping.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_showtimekeeping.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		label_showtimekeeping.setHorizontalAlignment(SwingConstants.CENTER);
		label_showtimekeeping.setBounds(310, 30, 250, 35);
		panel_content.add(label_showtimekeeping);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 155, 1160, 550);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		panel_content.add(scrollPane);
		
		panel_tabletimekeeping = new JPanel();
		scrollPane.setViewportView(panel_tabletimekeeping);
		panel_tabletimekeeping.setBackground(new Color(249, 230, 186));
		panel_tabletimekeeping.setPreferredSize(new Dimension(1150, 1100));
		panel_tabletimekeeping.setLayout(null);
	}

	private void updateWeekLabels(int weekNumber) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		int year = yearChooser.getYear();

		String[] dayNames = { "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật" };

		for (int i = 0; i < 7; i++) {
			Date date = calendar.getTime();
			String dayText = dayNames[i] + " " + dateFormat.format(date);
			list_dayinweek[i] = dateFormat.format(date) + "/" + year;
//			System.out.println(list_dayinweek[i]);
			list_labeltitle[i].setText(dayText);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
	}

	private JPanel createCellPanel(String employeeid, String date, int x) {
		JPanel panel = new JPanel();
		panel.setBounds(x, 0, 140, 100);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.setBackground(new Color(249, 230, 186));

		JLabel lblNewLabel_2_1 = new JLabel("Lịch làm");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(0, 0, 140, 25);
		panel.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_3_1 = new JLabel(Workschedule_BLL.getInstance().getWorkScheduleByEmployeeAndDate(date, employeeid));
		lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_3_1.setBounds(0, 25, 140, 25);
		panel.add(lblNewLabel_2_3_1);

		JLabel lblNewLabel_2_4_1 = new JLabel("Chấm công");
		lblNewLabel_2_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_4_1.setBounds(0, 50, 140, 25);
		panel.add(lblNewLabel_2_4_1);

		JLabel lblNewLabel_2_5_1 = new JLabel(Timekeeping_BLL.getInstance().getTimeInOutByDate(employeeid, date));
		lblNewLabel_2_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_5_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_5_1.setBounds(0, 75, 140, 25);
		panel.add(lblNewLabel_2_5_1);

		return panel;
	}

	private JPanel createPanelRow(Employee employee, int y) {
		JPanel panel_row = new JPanel();
		panel_row.setBounds(0, y, 1140, 100);
		panel_row.setLayout(null);
		panel_row.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_row.setBackground(new Color(249, 230, 186));

		JTextArea te_employeename = new JTextArea();
		te_employeename.setBounds(0, 0, 160, 100);
		panel_row.add(te_employeename);
		te_employeename.setFont(new Font("Tahoma", Font.PLAIN, 18));
		te_employeename.setText(employee.getFullname());
		te_employeename.setBackground(new Color(249, 230, 186));
		te_employeename.setLineWrap(true);
		te_employeename.setWrapStyleWord(true);
		te_employeename.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		for (int i = 0; i < 7; ++i) {
			JPanel panel_cell = createCellPanel(employee.getEmployeeID(), list_dayinweek[i], 160 + 140 * i);
			panel_row.add(panel_cell);
		}

		return panel_row;
	}

	private void displayTableTimekeeping(List<Employee> list) {
		int y = 0;
		panel_tabletimekeeping.removeAll();
		for(Employee employee: list) {
			JPanel panel = createPanelRow(employee, y);
			panel_tabletimekeeping.add(panel);
			y += 100;
		}
		if (y > scrollPane.getHeight()) {
		    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		} 
		else {
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		}
		panel_tabletimekeeping.revalidate();
		panel_tabletimekeeping.repaint();
	}
	
}
