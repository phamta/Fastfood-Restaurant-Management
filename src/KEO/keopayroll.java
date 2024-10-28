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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import BLL.Payroll_BLL;
import DTO.Payroll;
import EDIT.PayrollTableModel;
import EDIT.RoundedPanel;

public class keopayroll extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel_menubar;
    private RoundedPanel panel_content;

//    private JTable table;
    private DefaultTableModel tableModel;
    private JTable table_createpayroll;
	private PayrollTableModel model_createpayroll;
	
	
	private ChartPanel chartPanel;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    keopayroll frame = new keopayroll();
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
    public keopayroll() {
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
        panel_content.setBounds(310, 30, 1160, 730);
        contentPane.add(panel_content);
        panel_content.setLayout(null);

        JMonthChooser monthChooser = new JMonthChooser();
        monthChooser.getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 18));
        monthChooser.getSpinner().setBounds(0, 0, 143, 34);
        monthChooser.setBounds(30, 10, 145, 35);
        panel_content.add(monthChooser);
        monthChooser.setLayout(null);

        JYearChooser yearChooser = new JYearChooser();
        yearChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 40));
        yearChooser.setBounds(190, 10, 86, 35);
        panel_content.add(yearChooser);

//        // Change font for the spinner and its components
//        Font font = new Font("Tahoma", Font.PLAIN, 40);
//        yearChooser.getSpinner().setFont(font);
//        JComponent editor = ((JSpinner) yearChooser.getSpinner()).getEditor();
//        if (editor instanceof JSpinner.DefaultEditor) {
//            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
//            spinnerEditor.getTextField().setFont(font);
//        }
//
//        // Update the UI to apply font changes
//        SwingUtilities.updateComponentTreeUI(yearChooser);

        JLabel label_showpayroll = new JLabel("Xem bảng lương");
        label_showpayroll.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_showpayroll.setBounds(295, 10, 175, 35);
        label_showpayroll.setHorizontalAlignment(SwingConstants.CENTER);
        label_showpayroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel_content.add(label_showpayroll);

        JLabel label_createpayroll = new JLabel("Tạo bảng lương");
        label_createpayroll.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		createPayrollTable(7, 2024);
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        	}
        });
        label_createpayroll.setHorizontalAlignment(SwingConstants.CENTER);
        label_createpayroll.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_createpayroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        label_createpayroll.setBounds(510, 10, 175, 35);
        panel_content.add(label_createpayroll);
        
//         panel create payroll
        JPanel panel_createpayroll = new JPanel();
        panel_createpayroll.setBackground(new Color(249, 230, 186));
        panel_createpayroll.setBounds(0, 60, 900, 630);
        panel_content.add(panel_createpayroll);
        panel_createpayroll.setLayout(null);
        
        JLabel label_save = new JLabel("Save");
        label_save.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		getPayrollList();
        	}
        });
        label_save.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_save.setBounds(710, 575, 100, 30);
        
        label_save.setHorizontalAlignment(SwingConstants.CENTER);
        label_save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel_createpayroll.add(label_save);
        
        JScrollPane scrollPane_createpayroll = new JScrollPane();
        scrollPane_createpayroll.setBounds(10, 10, 880, 540);
        panel_createpayroll.add(scrollPane_createpayroll);

        String[] columnName_createpayroll = {
            "Mã nhân viên", 
            "Tên nhân viên", 
            "Lương cơ bản", 
            "Lương thưởng", 
            "Lương tăng ca", 
            "Tiền phạt", 
            "Ghi chú",
            "Tổng tiền"
        };

        // Create the table model
        model_createpayroll = new PayrollTableModel(columnName_createpayroll);
        model_createpayroll.setColumnIdentifiers(columnName_createpayroll);

        // Create the table
        table_createpayroll = new JTable(model_createpayroll);
        scrollPane_createpayroll.setViewportView(table_createpayroll);
        
        JLabel label_statistical = new JLabel("Thống kê");
        label_statistical.addMouseListener(new MouseAdapter() {
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
        label_statistical.setHorizontalAlignment(SwingConstants.CENTER);
        label_statistical.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_statistical.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        label_statistical.setBounds(725, 10, 175, 35);
        panel_content.add(label_statistical);
        
//        JPanel panel_statistical = new JPanel();
//        panel_statistical.setBackground(new Color(249, 230, 186));
//        panel_statistical.setBounds(10, 60, 980, 605);
//        panel_content.add(panel_statistical);
//        panel_statistical.setLayout(null);
        
//        JComboBox<String> comboBox_choice = new JComboBox<String>();
//        comboBox_choice.setFont(new Font("Tahoma", Font.PLAIN, 18));
//        comboBox_choice.setBounds(30, 20, 300, 30);
//        comboBox_choice.addItem("Thống kê tổng lương hằng tháng");
//        comboBox_choice.addItem("Lương tháng nhân viên");
//        panel_statistical.add(comboBox_choice);
        
//        JButton button_showchart = new JButton("Hiển thị biểu đồ");
//        button_showchart.setBounds(350, 20, 200, 30);
//        panel_statistical.add(button_showchart);
//
//        button_showchart.addActionListener(new ActionListener() {
//            
//
//			public void actionPerformed(ActionEvent e) {
////				panel_thongke.remove(chartPanel);
//				
//                if (chartPanel != null) {
//                    panel_statistical.remove(chartPanel);
//                }
//            	if(comboBox_choice.getSelectedIndex() == 0) {
//                    int selectedYear = yearChooser.getYear();
//                    List<Integer> monthlyTotals = Payroll_BLL.getInstance().getMonthlyTotalsByYear(selectedYear);
//
//                    XYSeries series = new XYSeries("Tổng lương hàng tháng"); // explain
//
//                    for (int month = 1; month <= monthlyTotals.size(); month++) {
//                        series.add(month, monthlyTotals.get(month - 1));
//                    }
//
//                    XYSeriesCollection dataset = new XYSeriesCollection(series);
//
//                    JFreeChart lineChart = ChartFactory.createXYLineChart(
//                        "Bảng tổng lương hàng tháng năm " + selectedYear, // title
//                        "Tháng",
//                        "Tổng lương",
//                        dataset,
//                        PlotOrientation.VERTICAL,
//                        true, true, false
//                    );
//
//                    XYPlot plot = lineChart.getXYPlot();
//                    NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
//                    domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//
//                    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//                    renderer.setSeriesShapesVisible(0, true);
//                    plot.setRenderer(renderer);
//
//                    chartPanel = new ChartPanel(lineChart);
//                    chartPanel.setBounds(30, 100, 900, 500);
//                    panel_statistical.add(chartPanel);
//                    panel_statistical.revalidate();
//                    panel_statistical.repaint();
//            	}
//            	else {
//            		panel_statistical.remove(chartPanel);
//                    Map<String, Integer> employeeTotals = Payroll_BLL.getInstance().getEmployeeTotalsByMonthYear(4, 2024);
//
//                    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//                    for (Map.Entry<String, Integer> entry : employeeTotals.entrySet()) {
//                        dataset.addValue(entry.getValue(), "Lương", entry.getKey());
//                    }
//
//                    JFreeChart barChart = ChartFactory.createBarChart(
//                        "Tổng lương theo nhân viên tháng " + (monthChooser.getMonth() + 1) + " năm " + yearChooser.getYear(),
//                        "Nhân viên",
//                        "Tổng lương",
//                        dataset,
//                        PlotOrientation.VERTICAL,
//                        true, true, false
//                    );
//                    
//                    chartPanel = new ChartPanel(barChart);
//                    chartPanel.setBounds(30, 100, 900, 500);
//                    panel_statistical.add(chartPanel);
//                    panel_statistical.revalidate();
//                    panel_statistical.repaint();
//            	}
//            }
//        });

        // Hide the "Mã nhân viên" column
//        TableColumn column = table_createpayroll.getColumnModel().getColumn(0);
//        column.setMinWidth(0);
//        column.setMaxWidth(0);
//        column.setPreferredWidth(0);

//        String[] columnNames = {"Tên nhân viên", "Lương cơ bản", "Lương thưởng", "Lương tăng ca", "Tiền phạt", "Tổng cộng", "Ngày trả lương", "Đã nhận", "Ghi chú"};
//
//        tableModel = new DefaultTableModel(columnNames, 0) {
//            @Override
//            public Class<?> getColumnClass(int column) {
//                if (column == 7) {
//                    return Boolean.class;
//                }
//                return String.class;
//            }
//        };
//
//        table = new JTable(tableModel);
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(10, 75, 1140, 630); // Update the bounds to fit within the panel_content
//        panel_content.add(scrollPane);

        // Add MouseListener to lblNewLabel
        label_showpayroll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedMonth = monthChooser.getMonth() + 1; // Tháng bắt đầu từ 0
                int selectedYear = yearChooser.getYear();
                updatePayrollTable(selectedMonth, selectedYear);
            }
        });
    }

    private void updatePayrollTable(int month, int year) {
        // Clear existing data
        tableModel.setRowCount(0);

        // Fetch payroll data
        List<Payroll> payrollList = Payroll_BLL.getInstance().getPayrollByMonthYear(month, year);

        // Populate the table model with payroll data
        for (Payroll payroll : payrollList) {
            Object[] row = {
                payroll.getEmployeeName(),
                payroll.getWage(),
                payroll.getBonus(),
                payroll.getOvertime(),
                payroll.getFine(),
                payroll.getTotal(),
                payroll.getPaymentDate(),
                payroll.getStatus() == 1,
                payroll.getNote()
            };
            tableModel.addRow(row);
        }
    }
    
    private void createPayrollTable(int month, int year) {
    	model_createpayroll.setRowCount(0);
    	List<Payroll> list = Payroll_BLL.getInstance().createPayrollList(month, year);
    	for(Payroll payroll: list) {
            Object[] row = {
            		payroll.getEmployeeID(),
                    payroll.getEmployeeName(),
                    payroll.getWage(),
                    payroll.getBonus(),
                    payroll.getOvertime(),
                    payroll.getFine(),
//                    payroll.getTotal(),
//                    payroll.getPaymentDate(),
//                    payroll.getStatus() == 1,
                    payroll.getNote(),
                    payroll.getWage() + payroll.getBonus() + payroll.getOvertime() - payroll.getFine()
                };
            model_createpayroll.addRow(row);
    	}
    }
    
    private List<Payroll> getPayrollList(){
    	List<Payroll> list = new ArrayList<Payroll>();
    	for(int row = 0; row< model_createpayroll.getRowCount(); ++row) {
    		String employeeid = (String) model_createpayroll.getValueAt(row, 0);
    		String employeeName = (String) model_createpayroll.getValueAt(row, 1);
            int wage = parseIntSafely(model_createpayroll.getValueAt(row, 2));
            int bonus = parseIntSafely(model_createpayroll.getValueAt(row, 3));
            int overtime = parseIntSafely(model_createpayroll.getValueAt(row, 4));
            int fine = parseIntSafely(model_createpayroll.getValueAt(row, 5));
            int total = parseIntSafely(model_createpayroll.getValueAt(row, 7));
            String note = (String) model_createpayroll.getValueAt(row, 6);
    		
    		Payroll payroll = new Payroll(employeeid, employeeName, wage, bonus, overtime, fine, total, "", 0, note);
    		System.out.println(payroll.toString());
    	}
    	
    	return list;
    }
    
    private int parseIntSafely(Object value) {
        try {
            if (value instanceof String) {
                return Integer.parseInt((String) value);
            } else if (value instanceof Integer) {
                return (Integer) value;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0; // Giá trị mặc định nếu chuyển đổi thất bại
    }
}
