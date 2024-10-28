package KEO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JYearChooser;

import BLL.Employee_BLL;
import BLL.Timekeeping_BLL;
import DTO.Employee;
import EDIT.RoundedPanel;

public class keotimekeeping extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_menubar;
	
	private ChartPanel panel_chart;
	
	private JLabel[] list_labeltitle = new JLabel[7];
	private String[] list_dayinweek = new String[7];
	private JComboBox<Integer> comboBox_chooseweek;
	private JYearChooser yearChooser;
	private JComboBox<Employee> comboBox_employee;
	private JPanel panel_showchart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					keotimekeeping frame = new keotimekeeping();
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
	public keotimekeeping() {
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
		
		RoundedPanel panel_content = new RoundedPanel(50, new Color(249, 230, 186));
		panel_content.setBackground(new Color(249, 230, 186));
		panel_content.setBounds(310, 30,1190,730);
		panel_content.setLayout(null);
		contentPane.add(panel_content);
		
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
		
//		JLabel lblNewLabel_2_2 = new JLabel("Thứ hai 22/12");
//		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblNewLabel_2_2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		lblNewLabel_2_2.setBounds(160, 0, 140, 55);
//		panel_2_1.add(lblNewLabel_2_2);
//		
//		JLabel lblNewLabel_2_1_6 = new JLabel("Thứ ba 23/12");
//		lblNewLabel_2_1_6.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_2_1_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblNewLabel_2_1_6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		lblNewLabel_2_1_6.setBounds(300, 0, 140, 55);
//		panel_2_1.add(lblNewLabel_2_1_6);
//		
//		JLabel lblNewLabel_2_1_1_1 = new JLabel("Thứ tư 24/12");
//		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblNewLabel_2_1_1_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		lblNewLabel_2_1_1_1.setBounds(440, 0, 140, 55);
//		panel_2_1.add(lblNewLabel_2_1_1_1);
//		
//		JLabel lblNewLabel_2_1_2_1 = new JLabel("Thứ năm 25/12");
//		lblNewLabel_2_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_2_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblNewLabel_2_1_2_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		lblNewLabel_2_1_2_1.setBounds(580, 0, 140, 55);
//		panel_2_1.add(lblNewLabel_2_1_2_1);
//		
//		JLabel lblNewLabel_2_1_3_1 = new JLabel("Thứ sáu 26/12");
//		lblNewLabel_2_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_2_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblNewLabel_2_1_3_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		lblNewLabel_2_1_3_1.setBounds(720, 0, 140, 55);
//		panel_2_1.add(lblNewLabel_2_1_3_1);
//		
//		JLabel lblNewLabel_2_1_4_1 = new JLabel("Thứ bảy 27/12");
//		lblNewLabel_2_1_4_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_2_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblNewLabel_2_1_4_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		lblNewLabel_2_1_4_1.setBounds(860, 0, 140, 55);
//		panel_2_1.add(lblNewLabel_2_1_4_1);
//		
//		JLabel lblNewLabel_2_1_5_1 = new JLabel("Chủ nhật 28/12");
//		lblNewLabel_2_1_5_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_2_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		lblNewLabel_2_1_5_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
//		lblNewLabel_2_1_5_1.setBounds(1000, 0, 140, 55);
//		panel_2_1.add(lblNewLabel_2_1_5_1);
		
		comboBox_chooseweek = new JComboBox<Integer>();
		comboBox_chooseweek.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_chooseweek.setBounds(100, 30, 60, 30);
		panel_content.add(comboBox_chooseweek);
		
		for(int i=0; i<7; ++i) {
			list_labeltitle[i] = new JLabel("", SwingConstants.CENTER);
			list_labeltitle[i].setFont(new Font("Tahoma", Font.PLAIN, 18));
			list_labeltitle[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			list_labeltitle[i].setBounds(160 + 140 * i, 0, 140, 55);
//			panel_title.add(list_labeltitle[i]);
		}
		
		Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        for(int i=1; i<= currentWeek; ++i) {
        	comboBox_chooseweek.addItem(i);
        }
        comboBox_chooseweek.setSelectedItem(currentWeek);
        updateWeekLabels(currentWeek);
		
		
		
		JLabel label_showtimekeeping = new JLabel("Xem bảng chấm công");
		label_showtimekeeping.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int week = comboBox_chooseweek.getSelectedIndex() + 1;
				updateWeekLabels(week);
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
		
		JLabel label_statistical = new JLabel("Thống kê");
		label_statistical.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				panel_title.setVisible(false);
//				scrollPane.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_statistical.setHorizontalAlignment(SwingConstants.CENTER);
		label_statistical.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_statistical.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		label_statistical.setBounds(600, 30, 180, 35);
		panel_content.add(label_statistical);
		
		panel_showchart = new JPanel();
		panel_showchart.setBackground(new Color(249, 230, 186));
		panel_showchart.setBounds(10, 80, 1150, 620);
		panel_content.add(panel_showchart);
		panel_showchart.setLayout(null);
		
		comboBox_employee = new JComboBox<Employee>();
		comboBox_employee = new JComboBox<>(new DefaultComboBoxModel<>(Employee_BLL.getInstance().getAllEmployee().toArray(new Employee[0])));
		comboBox_employee.setBackground(new Color(249, 230, 186));
		comboBox_employee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_employee.setBounds(160, 20, 250, 30);
		panel_showchart.add(comboBox_employee);
		comboBox_employee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                
            }
        });
		
		JLabel lblNewLabel_2 = new JLabel("Nhân viên");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(40, 20, 120, 25);
		panel_showchart.add(lblNewLabel_2);
		
		JLabel label_showchart = new JLabel("Xem bảng thống kê");
		label_showchart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Employee selectedEmployee = (Employee) comboBox_employee.getSelectedItem();
				int week = Integer.parseInt(comboBox_chooseweek.getSelectedItem().toString());
                showChart(selectedEmployee.getEmployeeID(), week, selectedEmployee.getFullname());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_showchart.setHorizontalAlignment(SwingConstants.CENTER);
		label_showchart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_showchart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		label_showchart.setBounds(468, 15, 250, 35);
		panel_showchart.add(label_showchart);
		
		
		
	}
	
    private void updateWeekLabels(int weekNumber) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        
        int year = yearChooser.getYear();

        String[] dayNames = {"Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy", "Chủ nhật"};

        for (int i = 0; i < 7; i++) {
            Date date = calendar.getTime();
            String dayText = dayNames[i] + " " + dateFormat.format(date);
            list_dayinweek[i] = dateFormat.format(date) + "/" + year;
            list_labeltitle[i].setText(dayText);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
    
    private void showChart(String employeeid, int weekNumber, String employeename) {
    	if(panel_chart != null) {
    		panel_showchart.remove(panel_chart);
    	}
    	 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	 
    	 float totalWorkingHours = Timekeeping_BLL.getInstance().getTotalWorkingHoursByEmployeeAndWeek(employeeid, weekNumber);
    	 float totalLateHours = Timekeeping_BLL.getInstance().getTotalLateByEmployeeAndWeek(employeeid, weekNumber);
         
         dataset.addValue(totalWorkingHours, "Total Working Hours", "Week " + weekNumber);
         dataset.addValue(totalLateHours, "Total Late Hours", "Week " + weekNumber);

         JFreeChart barChart = ChartFactory.createBarChart(
                 "Work Hours vs Late Hours for Employee: " + employeename,
                 "Category",
                 "Hours",
                 dataset,
                 PlotOrientation.VERTICAL,
                 true, true, false);

         panel_chart = new ChartPanel(barChart);
         panel_chart.setBounds(160, 100, 750, 420);
         panel_showchart.add(panel_chart);
         
         panel_showchart.revalidate();
         panel_showchart.repaint();
    }
}
