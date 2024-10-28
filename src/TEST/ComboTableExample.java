package TEST;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ComboTableExample {

    public static void main(String[] args) {
        // Tạo frame và panel
        JFrame frame = new JFrame("Combo Table Example");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        // Tạo mô hình bảng với dữ liệu
        String[] columnNames = {"Mã Combo", "Tên Combo", "Giá Bán"};
        Object[][] data = {
            {"001", "Combo 1", 100000},
            {"002", "Combo 2", 150000},
            {"003", "Combo 3", 200000}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table_item = new JTable(model);

        // Ẩn cột mã combo
        TableColumn column = table_item.getColumnModel().getColumn(0);
        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);

        // Thêm JTable vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table_item);
        panel.add(scrollPane);

        // Thêm sự kiện chọn hàng trong bảng
        table_item.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table_item.getSelectedRow() != -1) {
                int selectedRow = table_item.getSelectedRow();
                String maCombo = (String) table_item.getValueAt(selectedRow, 0); // Lấy mã combo từ hàng được chọn
                System.out.println("Mã Combo được chọn: " + maCombo);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
