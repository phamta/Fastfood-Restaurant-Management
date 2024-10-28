package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import BLL.Customer_BLL;
import BLL.Order_BLL;
import BLL.Orderdetail_BLL;
import DAL.Customer_DAL;
import DAL.Orderdetail_DAL;
import DTO.Customer;
import DTO.Itemplus;
import DTO.Order;
import EDIT.RoundedPanel;

public class Customer_VIEW extends RoundedPanel {
	private static final long serialVersionUID = 1L;
	private RoundedPanel panel_buycustomer;
	private JScrollPane scrollPane_historybuy;
	private JPanel panel_listhistory;

	private RoundedPanel panel_listcustomer;
	private JTextField tf_searchcustomer;
	private JScrollPane scrollPane_listcustomer;
	private JPanel panel_listinfocustomer;

	private JScrollPane scrollPane_listitem;
	private JPanel panel_listitem;
	
	private JLabel label_countbuy;
	private JPanel panel_statictical;
	private JComboBox<String> comboBox_choice;
	
	private String customerid;
	private ChartPanel panel_chart;
	private JLabel label_showchart;

	public Customer_VIEW(int radius, Color backgroundColor) {
		super(radius, backgroundColor);
		// TODO Auto-generated constructor stub
		setBounds(0, 0, 1190, 730);
		setLayout(null);

		panel_buycustomer = new RoundedPanel(50, new Color(249, 230, 186));
		panel_buycustomer.setLayout(null);
		panel_buycustomer.setBounds(0, 0, 800, 730);
		this.add(panel_buycustomer);
		GUI_panelbuycustomer();

		panel_listcustomer = new RoundedPanel(50, new Color(249, 230, 186));
		panel_listcustomer.setBounds(830, 0, 330, 730);
		this.add(panel_listcustomer);
		panel_listcustomer.setLayout(null);
		GUI_panellistcustomer();

		displayListInfoCustomer(Customer_DAL.getInstance().GetAllCustomer());
	}

	private void GUI_panelbuycustomer() {
		JLabel lblSLnMua = new JLabel("Số lần mua:");
		lblSLnMua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSLnMua.setBounds(50, 30, 120, 25);
		panel_buycustomer.add(lblSLnMua);

		label_countbuy = new JLabel();
		label_countbuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_countbuy.setBounds(185, 30, 50, 25);
		panel_buycustomer.add(label_countbuy);

		JLabel label_showhistorysale = new JLabel("Lịch sử mua hàng");
		label_showhistorysale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(panel_statictical != null) {
					panel_statictical.setVisible(false);
				}
				scrollPane_historybuy.setVisible(true);
				displayListHistoryBuy(Order_BLL.getInstance().getOrdersByCustomerId(customerid));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_showhistorysale.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_showhistorysale.setBounds(345, 30, 200, 35);
		label_showhistorysale.setHorizontalAlignment(SwingConstants.CENTER);
		label_showhistorysale.setBorder(new LineBorder(Color.black, 1));
		panel_buycustomer.add(label_showhistorysale);

		JLabel label_statistical = new JLabel("Thống kê");
		label_statistical.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(scrollPane_historybuy != null) {
					scrollPane_historybuy.setVisible(false);
				}
				panel_statictical.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_statistical.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_statistical.setBounds(576, 30, 200, 35);
		label_statistical.setHorizontalAlignment(SwingConstants.CENTER);
		label_statistical.setBorder(new LineBorder(Color.black, 1));
		panel_buycustomer.add(label_statistical);

		scrollPane_historybuy = new JScrollPane();
		scrollPane_historybuy.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_historybuy.setBounds(0, 100, 800, 630);
		panel_buycustomer.add(scrollPane_historybuy);
		scrollPane_historybuy.setVisible(false);

		panel_listhistory = new JPanel();
		scrollPane_historybuy.setViewportView(panel_listhistory);
		panel_listhistory.setBackground(new Color(249, 230, 186));
		panel_listhistory.setLayout(null);
		
		panel_statictical = new JPanel();
		panel_buycustomer.add(panel_statictical);
		panel_statictical.setBounds(0, 100, 800, 610);
		panel_statictical.setBackground(new Color(249, 230, 186));
		panel_statictical.setLayout(null);
		panel_statictical.setVisible(false);
		
		comboBox_choice = new JComboBox<String>();
		comboBox_choice.setBounds(30, 20, 200, 25);
		comboBox_choice.addItem("Mua hàng trong năm");
		comboBox_choice.addItem("Sản phấm ưa chuộng");
		panel_statictical.add(comboBox_choice);
		
		label_showchart = new JLabel("Hiển thị biểu đồ");
		label_showchart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				showChart();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		label_showchart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_showchart.setBounds(285, 20, 200, 30);
        label_showchart.setBorder(new LineBorder(Color.BLACK, 1));
        label_showchart.setHorizontalAlignment(SwingConstants.CENTER);
		panel_statictical.add(label_showchart);
	}

	private void GUI_panellistcustomer() {
		JLabel label_searchcustomer = new JLabel("Tìm kiếm");
		label_searchcustomer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_searchcustomer.setBounds(10, 20, 90, 25);
		panel_listcustomer.add(label_searchcustomer);

		tf_searchcustomer = new JTextField();
		tf_searchcustomer.setBounds(110, 20, 200, 25);
		panel_listcustomer.add(tf_searchcustomer);
		tf_searchcustomer.setColumns(10);
		tf_searchcustomer.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				handleTextChange();
			}

			public void removeUpdate(DocumentEvent e) {
				handleTextChange();
			}

			public void insertUpdate(DocumentEvent e) {
				handleTextChange();
			}

			public void handleTextChange() {
				String search = tf_searchcustomer.getText();
				panel_listinfocustomer.removeAll();

				List<Customer> list = Customer_BLL.getInstance().SearchCustomerIntrie(search);
				if (list.isEmpty()) {
					JLabel label = new JLabel();
					panel_listinfocustomer.add(label);
				} else {
					displayListInfoCustomer(list);
				}
			}
		});

		scrollPane_listcustomer = new JScrollPane();
		scrollPane_listcustomer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_listcustomer.setBounds(0, 55, 330, 650);
		panel_listcustomer.add(scrollPane_listcustomer);

		panel_listinfocustomer = new JPanel();
		scrollPane_listcustomer.setViewportView(panel_listinfocustomer);
		panel_listinfocustomer.setBackground(new Color(249, 230, 186));
		panel_listinfocustomer.setLayout(null);
	}
	
	private void LoadCustomerInfo(String customerid) {
		Customer customer = Customer_BLL.getInstance().getCustomerById(customerid);
		tf_searchcustomer.setText(customer.getPhone_number());
		label_countbuy.setText(""+ customer.getPoint());
	}

	private RoundedPanel createPanelInfoCustomer(Customer customer, int y) {
		RoundedPanel panel = new RoundedPanel(50, new Color(254, 215, 124));
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				customerid = customer.getCustomerID();
				LoadCustomerInfo(customerid);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		panel.setBounds(0, y, 330, 80);
		panel.setLayout(null);

		JLabel label_name = new JLabel(customer.getFullname());
		label_name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_name.setBounds(10, 10, 300, 25);
		panel.add(label_name);

		JLabel label_phonenumber = new JLabel(customer.getPhone_number());
		label_phonenumber.setBounds(150, 45, 150, 25);
		panel.add(label_phonenumber);
		label_phonenumber.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblNewLabel = new JLabel("Số điện thoại:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 45, 125, 25);
		panel.add(lblNewLabel);
		
		

		return panel;
	}

	private void displayListInfoCustomer(List<Customer> list) {
		int y = 0;
		if (list.size() > panel_listcustomer.getHeight() / 90) {
			scrollPane_listcustomer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		} else {
			scrollPane_listcustomer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		}
		for (Customer customer : list) {
			RoundedPanel roundedpanel = createPanelInfoCustomer(customer, y);
			panel_listinfocustomer.add(roundedpanel);
			y += 90;
		}
		panel_listinfocustomer.revalidate();
		panel_listinfocustomer.repaint();
	}

	private RoundedPanel createPanelInfoCustomerBuy(Order order, int y) {
		RoundedPanel panel = new RoundedPanel(50, new Color(254, 215, 124));
		panel.setLayout(null);
		panel.setBounds(20, y, 740, 230);

		JLabel lblNewLabel_4 = new JLabel("Thời gian:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(30, 10, 95, 25);
		panel.add(lblNewLabel_4);

		JLabel label_datetime = new JLabel(order.getDatetime());
		label_datetime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_datetime.setBounds(130, 10, 200, 25);
		panel.add(label_datetime);

		JLabel lblNewLabel_6 = new JLabel("Mã nhân viên:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(400, 10, 130, 25);
		panel.add(lblNewLabel_6);

		JLabel label_employeeid = new JLabel(order.getEmployeeID());
		label_employeeid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_employeeid.setBounds(560, 10, 100, 25);
		panel.add(label_employeeid);

		JLabel lblNewLabel_8 = new JLabel("Tổng tiền:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(30, 45, 95, 25);
		panel.add(lblNewLabel_8);

		JLabel label_total = new JLabel(order.getTotal() + "");
		label_total.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_total.setBounds(130, 45, 120, 25);
		panel.add(label_total);

		JLabel label_ = new JLabel("Đơn giá");
		label_.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_.setBounds(400, 70, 100, 30);
//        label_.setBorder(new LineBorder(Color.BLACK, 1));
		label_.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_);

		JLabel lblNewLabel_15 = new JLabel("Số lượng");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_15.setBounds(510, 70, 90, 30);
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_15);

		JLabel lblNewLabel_16 = new JLabel("Tổng tiền");
		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_16.setBounds(620, 70, 90, 30);
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_16);

		JLabel lblNewLabel_3 = new JLabel("Tên món");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(5, 70, 100, 30);
		panel.add(lblNewLabel_3);

		scrollPane_listitem = new JScrollPane();
		scrollPane_listitem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_listitem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_listitem.setBounds(0, 100, 740, 110);
		scrollPane_listitem.setBorder(null);
		panel.add(scrollPane_listitem);

		panel_listitem = new JPanel();
		panel_listitem.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_listitem.setViewportView(panel_listitem);
		panel_listitem.setBackground(new Color(254, 215, 124));
		panel_listitem.setLayout(null);
		displayListItemplus(Orderdetail_DAL.getInstance().GetItemplusByOrderID(order.getOrderID()));

		return panel;
	}

	private void displayListHistoryBuy(List<Order> list) {
		int y = 0;
		for (Order order : list) {
			RoundedPanel roundedpanel = createPanelInfoCustomerBuy(order, y);
			panel_listhistory.add(roundedpanel);
			y += 250;
		}

		panel_listhistory.revalidate();
		panel_listhistory.repaint();
	}

	private JPanel createPanelItemplus(Itemplus itemplus, int y) {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 215, 124));
		panel.setBounds(0, y, 720, 30);
		panel.setLayout(null);

		JLabel label_nameitem = new JLabel(itemplus.getItemName());
		label_nameitem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_nameitem.setBounds(5, 0, 395, 25);
		panel.add(label_nameitem);

		JLabel label_price = new JLabel("" + itemplus.getPrice());
		label_price.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_price.setBounds(400, 0, 100, 25);
		label_price.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_price);

		JLabel label_quantity = new JLabel("" + itemplus.getQuantity());
		label_quantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_quantity.setBounds(535, 0, 40, 25);
		label_quantity.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_quantity);

		JLabel label_total = new JLabel("" + itemplus.getTotal());
		label_total.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_total.setBounds(610, 0, 110, 25);
		label_total.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(label_total);

		return panel;
	}

	private void displayListItemplus(List<Itemplus> list) {
		int y = 0;
		for (Itemplus itemplus : list) {
			JPanel panel = createPanelItemplus(itemplus, y);
			panel_listitem.add(panel);
			y += 40;
		}
		panel_listitem.revalidate();
		panel_listitem.repaint();
	}
	
	private void showChart() {
		if(panel_chart != null) {
			panel_statictical.remove(panel_chart);
		}
		
		if(comboBox_choice.getSelectedIndex() == 0) {
			int year = 2024;
			
			Map<Integer, Integer> totalSpent = Customer_BLL.getInstance().getTotalSpentByMonth(customerid, year);
	        Map<Integer, Integer> purchaseCount = Customer_BLL.getInstance().getPurchaseCountByMonth(customerid, year);
	        
	        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
	        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

	        for (int month = 1; month <= 12; month++) {
	            Integer spent = totalSpent.getOrDefault(month, 0);
	            Integer count = purchaseCount.getOrDefault(month, 0);
	            dataset1.addValue(spent, "Total Spent", String.valueOf(month));
	            dataset2.addValue(count, "Purchase Count", String.valueOf(month));
	        }

	        JFreeChart chart = ChartFactory.createBarChart(
	                "Customer Purchase Statistics",
	                "Month",
	                "Total Spent",
	                dataset1,
	                PlotOrientation.VERTICAL,
	                true,
	                true,
	                false
	        );

	        CategoryPlot plot = (CategoryPlot) chart.getPlot();

	        NumberAxis axis2 = new NumberAxis("Purchase Count");
	        axis2.setTickUnit(new NumberTickUnit(1));
	        plot.setRangeAxis(1, axis2);
	        plot.setDataset(1, dataset2);
	        plot.mapDatasetToRangeAxis(1, 1);

	        BarRenderer barRenderer = new BarRenderer();
	        plot.setRenderer(barRenderer);

	        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
	        plot.setRenderer(1, lineRenderer);
	        
	        panel_chart = new ChartPanel(chart);
	        panel_chart.setBounds(30, 65, 740, 470);
	        panel_statictical.add(panel_chart);
		}
		else {
	        Map<String, Integer> productPurchaseStats = Orderdetail_BLL.getInstance().getCustomerPurchaseStats(customerid);

	        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        for (Map.Entry<String, Integer> entry : productPurchaseStats.entrySet()) {
	            dataset.addValue(entry.getValue(), "Số lượng", entry.getKey());
	        }

	        JFreeChart barChart = ChartFactory.createBarChart(
	                "Thống kê số lượng sản phẩm đã mua",
	                "Sản phẩm",
	                "Số lượng",
	                dataset,
	                PlotOrientation.VERTICAL,
	                true,
	                true,
	                false
	        );

	        CategoryPlot plot = (CategoryPlot) barChart.getPlot();

	        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setTickUnit(new NumberTickUnit(1)); // Ensure the axis displays integer values only

	        BarRenderer renderer = (BarRenderer) plot.getRenderer();
	        renderer.setDrawBarOutline(false);
	        
	        panel_chart = new ChartPanel(barChart);
	        panel_chart.setBounds(30, 65, 740, 470);
	        panel_statictical.add(panel_chart);
		}
        
        panel_statictical.revalidate();
        panel_statictical.repaint();
	}
}
