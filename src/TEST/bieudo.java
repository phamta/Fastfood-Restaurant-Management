package TEST;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import BLL.Employee_BLL;
import DTO.Employee;

public class bieudo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bieudo frame = new bieudo();
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
	public bieudo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Employee> list = getAllEmployees();
				Map<String, Integer> birthMonthCount = getBirthMonthCount(list);

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for (Map.Entry<String, Integer> entry : birthMonthCount.entrySet()) {
                    dataset.addValue(entry.getValue(), "Employees", entry.getKey());
                }

                JFreeChart barChart = ChartFactory.createBarChart(
                    "Number of Employees Born in Each Month", 
                    "Month", 
                    "Number of Employees", 
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false
                );

                CategoryPlot plot = barChart.getCategoryPlot();
                NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
                rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());


                ChartPanel chartPanel = new ChartPanel(barChart);
                chartPanel.setBounds(50, 50, 700, 500);
                contentPane.add(chartPanel);
                contentPane.revalidate();
                contentPane.repaint();
			}
		});
		btnNewButton.setBounds(300, 20, 200, 30);
		contentPane.add(btnNewButton);
	}
	
	private List<Employee> getAllEmployees(){
		return Employee_BLL.getInstance().getAllEmployee();
	}
	
	private Map<String, Integer> getBirthMonthCount(List<Employee> list){
        Map<String, Integer> monthCount = new HashMap<>();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        
        for(Employee employee: list) {
            try {
                Date date = inputFormat.parse(employee.getBirthday());
                String month = monthFormat.format(date);
                monthCount.put(month, monthCount.getOrDefault(month, 0) + 1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return monthCount;
	}

}
