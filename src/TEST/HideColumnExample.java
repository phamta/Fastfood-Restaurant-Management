package TEST;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class HideColumnExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Hide Column Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Object[][] data = {
                {"1", "John", "Doe"},
                {"2", "Jane", "Doe"},
                {"3", "Tom", "Smith"}
            };
            Object[] columnNames = {"ID", "First Name", "Last Name"};

            CustomTableModel model = new CustomTableModel(data, columnNames);
            JTable table = new JTable(model);

            // Ẩn cột ID
            TableColumn idColumn = table.getColumnModel().getColumn(0);
            table.removeColumn(idColumn);

            // Thêm sự kiện nhấn vào hàng để lấy giá trị của cột ID
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Chuyển đổi hàng đã chọn từ chế độ hiển thị sang chế độ mô hình
                        int modelRow = table.convertRowIndexToModel(selectedRow);
                        Object idValue = model.getValueAt(modelRow, 0);
                        System.out.println("ID value at row " + selectedRow + ": " + idValue);
                    }
                }
            });

            frame.add(new JScrollPane(table));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class CustomTableModel extends DefaultTableModel {
    public CustomTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Không cho phép chỉnh sửa các ô trong bảng
        return false;
    }
}
