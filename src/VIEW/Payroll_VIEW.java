package VIEW;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

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

public class Payroll_VIEW extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private RoundedPanel panel_content;
	private JPanel panel_statistical;

	private ChartPanel chartPanel;
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;
	private JPanel panel_createpayroll;
	private PayrollTableModel model_createpayroll;
	private JTable table_createpayroll;
	private DefaultTableModel tableModel;
	private JTable table;
	private JPanel panel_showpayroll;

	public Payroll_VIEW(int radius, Color backgroundColor) {
		super(radius, backgroundColor);
		// TODO Auto-generated constructor stub

		setBounds(0, 0, 1190, 730);
		setLayout(null);

		panel_content = new RoundedPanel(50, new Color(249, 230, 186));
		panel_content.setBounds(0, 0, 1160, 730);
		this.add(panel_content);
		panel_content.setLayout(null);

		monthChooser = new JMonthChooser();
		monthChooser.getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 18));
		monthChooser.getSpinner().setBounds(0, 0, 143, 34);
		monthChooser.setBounds(30, 10, 145, 35);
		panel_content.add(monthChooser);
		monthChooser.setLayout(null);

		yearChooser = new JYearChooser();
		yearChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 40));
		yearChooser.setBounds(190, 10, 86, 35);
		panel_content.add(yearChooser);

		JLabel label_showpayroll = new JLabel("Xem bảng lương");
		label_showpayroll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedMonth = monthChooser.getMonth() + 1; // Tháng bắt đầu từ 0
				int selectedYear = yearChooser.getYear();
				displayPanel(panel_showpayroll);
				updatePayrollTable(selectedMonth, selectedYear);
			}
		});
		label_showpayroll.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_showpayroll.setBounds(295, 10, 175, 35);
		label_showpayroll.setHorizontalAlignment(SwingConstants.CENTER);
		label_showpayroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_content.add(label_showpayroll);

		JLabel label_createpayroll = new JLabel("Tạo bảng lương");
		label_createpayroll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayPanel(panel_createpayroll);
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

		JLabel label_statistical = new JLabel("Thống kê");
		label_statistical.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				displayPanel(panel_statistical);
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

		panel_showpayroll = new JPanel();
		panel_showpayroll.setBackground(new Color(249, 230, 186));
		panel_showpayroll.setBounds(0, 60, 900, 630);
		panel_content.add(panel_showpayroll);
		panel_showpayroll.setLayout(null);
		panel_showpayroll.setVisible(false);
		GUI_panelshowpayroll();

		panel_createpayroll = new JPanel();
		panel_createpayroll.setBackground(new Color(249, 230, 186));
		panel_createpayroll.setBounds(0, 60, 900, 630);
		panel_content.add(panel_createpayroll);
		panel_createpayroll.setLayout(null);
		panel_createpayroll.setVisible(false);
		GUI_panelcreatepayroll();

		panel_statistical = new JPanel();
		panel_statistical.setBackground(new Color(249, 230, 186));
		panel_statistical.setBounds(10, 60, 980, 605);
		panel_content.add(panel_statistical);
		panel_statistical.setLayout(null);
		panel_statistical.setVisible(false);
		GUI_panelstatistical();

	}

    private void GUI_panelshowpayroll() {
        String[] columnNames = { "Mã nhân viên", "Tên nhân viên", "Lương cơ bản", "Lương thưởng", "Lương tăng ca",
                "Tiền phạt", "Tổng cộng", "Ngày trả lương", "Đã nhận", "Ghi chú" };

        tableModel = new DefaultTableModel(columnNames, 0) {
			private static final long serialVersionUID = 1L;

			@Override
            public Class<?> getColumnClass(int column) {
                if (column == 8) {
                    return Boolean.class;
                }
                return String.class;
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 5, 880, 580);
        panel_showpayroll.add(scrollPane);

        // Custom editor for "Đã nhận" column
        TableColumn receivedColumn = table.getColumnModel().getColumn(8);
        receivedColumn.setCellEditor(new ReceivedColumnEditor());

        TableColumn column = table.getColumnModel().getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);

        TableColumn tenNhanVienColumn = table.getColumnModel().getColumn(1);
        tenNhanVienColumn.setPreferredWidth(175);
        
		JLabel label_save = new JLabel("Save");
		label_save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				getPayrollListCreate();
				getPayrollListFromTable();
				JOptionPane.showMessageDialog(null, "Đã lưu thay đổi");
				updatePayrollTable(monthChooser.getMonth() + 1, yearChooser.getYear());
			}
		});
		label_save.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_save.setBounds(710, 595, 100, 30);

		label_save.setHorizontalAlignment(SwingConstants.CENTER);
		label_save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel_showpayroll.add(label_save);
    }
	
    private class ReceivedColumnEditor extends AbstractCellEditor implements TableCellEditor {
		private static final long serialVersionUID = 1L;
		private final JCheckBox checkBox;

        public ReceivedColumnEditor() {
            checkBox = new JCheckBox();
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.getEditingRow();
                    if (checkBox.isSelected()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        table.setValueAt(sdf.format(new Date()), row, 7);
                    } else {
                        table.setValueAt("", row, 7);
                    }
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return checkBox.isSelected();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            checkBox.setSelected((Boolean) value);
            return checkBox;
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            return true;
        }
    }

	private void GUI_panelcreatepayroll() {
		JLabel label_save = new JLabel("Save");
		label_save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getPayrollListCreate();
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

		String[] columnName_createpayroll = { "Mã nhân viên", "Tên nhân viên", "Lương cơ bản", "Lương thưởng",
				"Lương tăng ca", "Tiền phạt", "Ghi chú", "Tổng tiền" };

		// Create the table model
		model_createpayroll = new PayrollTableModel(columnName_createpayroll);
		model_createpayroll.setColumnIdentifiers(columnName_createpayroll);

		// Create the table
		table_createpayroll = new JTable(model_createpayroll);
		scrollPane_createpayroll.setViewportView(table_createpayroll);
	}

	private void GUI_panelstatistical() {
		JComboBox<String> comboBox_choice = new JComboBox<String>();
		comboBox_choice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_choice.setBounds(30, 20, 300, 30);
		comboBox_choice.addItem("Thống kê tổng lương hằng tháng");
		comboBox_choice.addItem("Lương tháng nhân viên");
		panel_statistical.add(comboBox_choice);

		JButton button_showchart = new JButton("Hiển thị biểu đồ");
		button_showchart.setBounds(350, 20, 200, 30);
		panel_statistical.add(button_showchart);

		button_showchart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
//				panel_thongke.remove(chartPanel);

				if (chartPanel != null) {
					panel_statistical.remove(chartPanel);
				}
				if (comboBox_choice.getSelectedIndex() == 0) {
					int selectedYear = yearChooser.getYear();
					List<Integer> monthlyTotals = Payroll_BLL.getInstance().getMonthlyTotalsByYear(selectedYear);

					XYSeries series = new XYSeries("Tổng lương hàng tháng"); // explain

					for (int month = 1; month <= monthlyTotals.size(); month++) {
						series.add(month, monthlyTotals.get(month - 1));
					}

					XYSeriesCollection dataset = new XYSeriesCollection(series);

					JFreeChart lineChart = ChartFactory.createXYLineChart(
							"Bảng tổng lương hàng tháng năm " + selectedYear, // title
							"Tháng", "Tổng lương", dataset, PlotOrientation.VERTICAL, true, true, false);

					XYPlot plot = lineChart.getXYPlot();
					NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
					domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

					XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
					renderer.setSeriesShapesVisible(0, true);
					plot.setRenderer(renderer);

					chartPanel = new ChartPanel(lineChart);
					chartPanel.setBounds(30, 100, 900, 500);
					panel_statistical.add(chartPanel);
					panel_statistical.revalidate();
					panel_statistical.repaint();
				} else {
//					panel_statistical.remove(chartPanel);
					Map<String, Integer> employeeTotals = Payroll_BLL.getInstance().getEmployeeTotalsByMonthYear(4,
							2024);

					DefaultCategoryDataset dataset = new DefaultCategoryDataset();

					for (Map.Entry<String, Integer> entry : employeeTotals.entrySet()) {
						dataset.addValue(entry.getValue(), "Lương", entry.getKey());
					}

					JFreeChart barChart = ChartFactory.createBarChart(
							"Tổng lương theo nhân viên tháng " + (monthChooser.getMonth() + 1) + " năm "
									+ yearChooser.getYear(),
							"Nhân viên", "Tổng lương", dataset, PlotOrientation.VERTICAL, true, true, false);

					chartPanel = new ChartPanel(barChart);
					chartPanel.setBounds(30, 100, 900, 500);
					panel_statistical.add(chartPanel);
					panel_statistical.revalidate();
					panel_statistical.repaint();
				}
			}
		});
	}

	private void displayPanel(JPanel panelToShow) {
		panel_showpayroll.setVisible(false);
		panel_createpayroll.setVisible(false);
		panel_statistical.setVisible(false);
		panelToShow.setVisible(true);
	}

	private void updatePayrollTable(int month, int year) {
		// Clear existing data
		tableModel.setRowCount(0);

		// Fetch payroll data
		List<Payroll> payrollList = Payroll_BLL.getInstance().getPayrollByMonthYear(month, year);

		// Populate the table model with payroll data
		for (Payroll payroll : payrollList) {
			Object[] row = { payroll.getEmployeeID(), payroll.getEmployeeName(), payroll.getWage(), payroll.getBonus(),
					payroll.getOvertime(), payroll.getFine(), payroll.getTotal(), payroll.getPaymentDate(),
					payroll.getStatus() == 1, payroll.getNote() };
			tableModel.addRow(row);
		}
	}
	
    private void getPayrollListFromTable() {
        List<Payroll> payrollList = new ArrayList<>();
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        for (int row = 0; row < model.getRowCount(); row++) {
            String employeeID = (String) model.getValueAt(row, 0);
            String employeeName = (String) model.getValueAt(row, 1);
            int wage = (Integer) model.getValueAt(row, 2);
            int bonus = (Integer) model.getValueAt(row, 3);
            int overtime = (Integer) model.getValueAt(row, 4);
            int fine = (Integer) model.getValueAt(row, 5);
            int total = (Integer) model.getValueAt(row, 6);
            String paymentDate = (String) model.getValueAt(row, 7);
            int status = (Boolean) model.getValueAt(row, 8) ? 1 : 0; // Convert boolean to int
            String note = (String) model.getValueAt(row, 9);

            Payroll payroll = new Payroll(employeeID, employeeName, wage, bonus, overtime, fine, total, paymentDate, status, note);
            payrollList.add(payroll);
        }
        
        Payroll_BLL.getInstance().updatePayroll(4, 2024, payrollList);
    }

	private void createPayrollTable(int month, int year) {
		model_createpayroll.setRowCount(0);
		List<Payroll> list = Payroll_BLL.getInstance().createPayrollList(month, year);
		for (Payroll payroll : list) {
			Object[] row = { payroll.getEmployeeID(), payroll.getEmployeeName(), payroll.getWage(), payroll.getBonus(),
					payroll.getOvertime(), payroll.getFine(),
//                    payroll.getTotal(),
//                    payroll.getPaymentDate(),
//                    payroll.getStatus() == 1,
					payroll.getNote(),
					payroll.getWage() + payroll.getBonus() + payroll.getOvertime() - payroll.getFine() };
			model_createpayroll.addRow(row);
		}
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

	private List<Payroll> getPayrollListCreate() {
		List<Payroll> list = new ArrayList<Payroll>();
		for (int row = 0; row < model_createpayroll.getRowCount(); ++row) {
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
}
