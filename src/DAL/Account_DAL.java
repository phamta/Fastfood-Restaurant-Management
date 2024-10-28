package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.Account;

public class Account_DAL {
	private static Account_DAL instance;

	private Account_DAL() {

	}

	public static Account_DAL getInstance() {
		if (instance == null) {
			instance = new Account_DAL();
		}
		return instance;
	}

	public List<Account> GetAllUser() {
	    List<Account> list = new ArrayList<>();
	    ResultSet rs = JDBCUtil.getInstance().executeQuery(
	        "SELECT account.Username, account.Password, account.EmployeeID, account.Quyen, account.Status, employee.Fullname AS EmployeeName " +
	        "FROM account " +
	        "JOIN employee ON account.EmployeeID = employee.EmployeeID " +
	        "where employee.isDeleted = 0"
	    );
	    try {
	        while (rs.next()) {
	            list.add(GetUserFromResultSet(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	private Account GetUserFromResultSet(ResultSet rs) {
	    Account account = new Account();
	    try {
	        account.setUsername(rs.getString("Username"));
	        account.setPassword(rs.getString("Password"));
	        account.setUserID(rs.getString("EmployeeID"));
	        account.setQuyen(rs.getInt("Quyen"));
	        account.setStatus(rs.getInt("Status"));
	        account.setEmployeeName(rs.getString("EmployeeName"));  // Retrieve EmployeeName
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return account;
	}

	public void UpdatePassword(String employeeid, String password) {
		try {
			Statement statement = JDBCUtil.getInstance().getConnection().createStatement();
			String query = "update account " + "set Password = '" + password + "' " + "where EmployeeID like '"
					+ employeeid + "'";
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addAccount(Account user) {
		String query = "INSERT INTO account (Username, Password, EmployeeID, Quyen) VALUES (?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getUserID());
			preparedStatement.setInt(4, user.getQuyen());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAccount(Account account) {
	    String query = "UPDATE account SET Username = ?, Password = ?, Status = ? WHERE EmployeeID = ?";
	    
	    try (PreparedStatement preparedStatement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {

	        preparedStatement.setString(1, account.getUsername());
	        preparedStatement.setString(2, account.getPassword());
	        preparedStatement.setInt(3, account.getStatus());
	        preparedStatement.setString(4, account.getUserID());

	        preparedStatement.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
