package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.Payroll;

public class Payroll_DAL {
	private static Payroll_DAL instance;
	
	private Payroll_DAL() {
		
	}
	
	public static Payroll_DAL getInstance() {
		if(instance == null) {
			instance = new Payroll_DAL();
		}
		return instance;
	}
	
    public List<Payroll> getPayrollByMonthYear(int month, int year) {
        List<Payroll> payrollList = new ArrayList<Payroll>();
        
        // Câu lệnh SQL để lấy danh sách payroll
        String query = "SELECT p.EmployeeID, e.Fullname, p.Wage, p.Bonus, p.Overtime, p.Fine, p.Total, p.PaymentDate, p.Status, p.Note "
                   + "FROM payroll p "
                   + "JOIN employee e ON p.EmployeeID = e.EmployeeID "
                   + "WHERE p.Month = ? AND p.Year = ?";
        try {
			PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, month);
            statement.setInt(2, year);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String employeeID = resultSet.getString("EmployeeID");
                String employeeName = resultSet.getString("Fullname");
                int wage = resultSet.getInt("Wage");
                int bonus = resultSet.getInt("Bonus");
                int overtime = resultSet.getInt("Overtime");
                int fine = resultSet.getInt("Fine");
                int total = resultSet.getInt("Total");
                String paymentDate = resultSet.getString("PaymentDate");
                int status = resultSet.getInt("Status");
                String note = resultSet.getString("Note");

                Payroll payroll = new Payroll(employeeID, employeeName, wage, bonus, overtime, fine, total, paymentDate, status, note);
                payrollList.add(payroll);
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return payrollList;
    }
    
    public int getTotalByYear(int year) {
        int totalSum = 0;
        
        String query = "SELECT SUM(Total) AS TotalSum FROM payroll WHERE Year = ?";
        try {
            PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, year);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                totalSum = resultSet.getInt("TotalSum");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return totalSum;
    }
    
    public List<Integer> getMonthlyTotalsByYear(int year) {
        List<Integer> monthlyTotals = new ArrayList<Integer>(Collections.nCopies(12, 0)); // Initialize list with 12 zeros
        
        String query = "SELECT Month, SUM(Total) AS TotalSum FROM payroll WHERE Year = ? GROUP BY Month ORDER BY Month";
        try {
            PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, year);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int month = resultSet.getInt("Month");
                int totalSum = resultSet.getInt("TotalSum");
                
                // Update the total for the specific month
                monthlyTotals.set(month - 1, totalSum); // Month is 1-based, list index is 0-based
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return monthlyTotals;
    }
    
    public Map<String, Integer> getEmployeeTotalsByMonthYear(int month, int year) {
        Map<String, Integer> employeeTotals = new HashMap<>();
        
        String query = "SELECT e.Fullname, SUM(p.Total) AS TotalSum FROM payroll p JOIN employee e ON p.EmployeeID = e.EmployeeID WHERE p.Month = ? AND p.Year = ? GROUP BY e.Fullname ORDER BY e.Fullname";
        try {
            PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query);
            statement.setInt(1, month);
            statement.setInt(2, year);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                String employeeName = resultSet.getString("Fullname");
                int totalSum = resultSet.getInt("TotalSum");
                employeeTotals.put(employeeName, totalSum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return employeeTotals;
    }
    
    public void updatePayroll(int month, int year, Payroll payroll) {
        String query = "UPDATE payroll SET Wage = ?, Bonus = ?, Overtime = ?, Fine = ?, Total = ?, PaymentDate = ?, Status = ?, Note = ? " +
                       "WHERE EmployeeID = ? AND Month = ? AND Year = ?";

        try {
            PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query);
            
            // Set the values for the statement
            statement.setInt(1, payroll.getWage());
            statement.setInt(2, payroll.getBonus());
            statement.setInt(3, payroll.getOvertime());
            statement.setInt(4, payroll.getFine());
            statement.setInt(5, payroll.getTotal());
            statement.setString(6, payroll.getPaymentDate());
            statement.setInt(7, payroll.getStatus());
            statement.setString(8, payroll.getNote());
            statement.setString(9, payroll.getEmployeeID());
            statement.setInt(10, month);
            statement.setInt(11, year);

            // Execute the update
            int rowsAffected = statement.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Payroll updated successfully.");
            } else {
                System.out.println("No payroll record found for the given parameters.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
