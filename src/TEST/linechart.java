package TEST;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class linechart extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    linechart frame = new linechart();
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
    public linechart() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnShowRevenueChart = new JButton("Show Revenue Chart");
        btnShowRevenueChart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Invoice> invoices = getInvoices();
                Map<Integer, Double> monthlyRevenue = getMonthlyRevenue(invoices);

                XYSeries series = new XYSeries("Monthly Revenue");

                for (Map.Entry<Integer, Double> entry : monthlyRevenue.entrySet()) {
                    series.add(entry.getKey(), entry.getValue());
                }

                XYSeriesCollection dataset = new XYSeriesCollection(series);

                JFreeChart lineChart = ChartFactory.createXYLineChart(
                    "Monthly Revenue",
                    "Month",
                    "Revenue",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false
                );

                XYPlot plot = lineChart.getXYPlot();
                NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
                domainAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
                renderer.setSeriesShapesVisible(0, true);
                plot.setRenderer(renderer);

                ChartPanel chartPanel = new ChartPanel(lineChart);
                chartPanel.setBounds(50, 50, 700, 500);
                contentPane.add(chartPanel);
                contentPane.revalidate();
                contentPane.repaint();
            }
        });
        btnShowRevenueChart.setBounds(300, 20, 200, 30);
        contentPane.add(btnShowRevenueChart);
    }

    private List<Invoice> getInvoices() {
        // Example data, replace this with actual data retrieval logic
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice("2023-01-15", 500.0));
        invoices.add(new Invoice("2023-01-20", 1500.0));
        invoices.add(new Invoice("2023-02-10", 1000.0));
        invoices.add(new Invoice("2023-03-05", 2000.0));
        invoices.add(new Invoice("2023-02-25", 2500.0));
        invoices.add(new Invoice("2023-01-30", 3000.0));
        // Add more invoices as needed
        return invoices;
    }

    private Map<Integer, Double> getMonthlyRevenue(List<Invoice> invoices) {
        Map<Integer, Double> monthlyRevenue = new HashMap<>();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (Invoice invoice : invoices) {
            try {
                Date date = inputFormat.parse(invoice.getDate());
                int month = date.getMonth() + 1; // Month is 0-based, so we add 1
                monthlyRevenue.put(month, monthlyRevenue.getOrDefault(month, 0.0) + invoice.getAmount());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return monthlyRevenue;
    }

    private class Invoice {
        private String date;
        private double amount;

        public Invoice(String date, double amount) {
            this.date = date;
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public double getAmount() {
            return amount;
        }
    }
}
