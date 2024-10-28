package EDIT;

import javax.swing.table.DefaultTableModel;
public class PayrollTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;
	
	private final int WAGE_COL = 2;
    private final int BONUS_COL = 3;
    private final int OVERTIME_COL = 4;
    private final int FINE_COL = 5;
    private final int TOTAL_COL = 7;

    public PayrollTableModel(String[] columnNames) {
        super(columnNames, 0);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
        if (column == WAGE_COL || column == BONUS_COL || column == OVERTIME_COL || column == FINE_COL) {
            updateTotal(row);
        }
    }

    private void updateTotal(int row) {
        int wage = getIntegerValueAt(row, WAGE_COL);
        int bonus = getIntegerValueAt(row, BONUS_COL);
        int overtime = getIntegerValueAt(row, OVERTIME_COL);
        int fine = getIntegerValueAt(row, FINE_COL);

        int total = wage + bonus + overtime - fine;
        setValueAt(total, row, TOTAL_COL);
    }

    private int getIntegerValueAt(int row, int column) {
        Object value = getValueAt(row, column);
        try {
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            return 0; // or handle the error as needed
        }
    }
}