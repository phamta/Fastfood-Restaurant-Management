package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.Customer;

public class Customer_DAL {
	private static Customer_DAL instance;

	private Customer_DAL() {

	}

	public static Customer_DAL getInstance() {
		if (instance == null) {
			instance = new Customer_DAL();
		}
		return instance;
	}

	public List<Customer> GetAllCustomer() {
		List<Customer> list = new ArrayList<Customer>();
		ResultSet rs = JDBCUtil.getInstance().executeQuery("select * from customer");
		try {
			while (rs.next()) {
				list.add(GetCustomerFromResultSet(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private Customer GetCustomerFromResultSet(ResultSet rs) {
		Customer customer = new Customer();
		try {
			customer.setCustomerID(rs.getString("CustomerID"));
			customer.setFullname(rs.getString("Name"));
			customer.setPhone_number(rs.getString("Phone_number"));
			customer.setPoint(rs.getInt("Point"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	public void updatePoint(Customer customer) {
		String query = "update customer " + "set Point = " + customer.getPoint() + " where CustomerID like '"
				+ customer.getCustomerID() + "';";
		try {
			Statement statement = JDBCUtil.getInstance().getConnection().createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addCustomer(Customer customer) {
		String query = "INSERT INTO customer (CustomerID, Name, Phone_number, Point) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {

			pstmt.setString(1, customer.getCustomerID());
			pstmt.setString(2, customer.getFullname());
			pstmt.setString(3, customer.getPhone_number());
			pstmt.setInt(4, customer.getPoint());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

    public Customer getCustomerById(String customerId) {
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE CustomerID = ?";
        try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, customerId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    customer = GetCustomerFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    
}
