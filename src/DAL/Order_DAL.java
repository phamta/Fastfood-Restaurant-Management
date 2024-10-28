package DAL;

import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;

import DTO.Order;

public class Order_DAL {
	private static Order_DAL instance;

	private Order_DAL() {

	}

	public static Order_DAL getInstance() {
		if (instance == null) {
			instance = new Order_DAL();
		}
		return instance;
	}

	public List<Order> GetAllOrder() {
	    List<Order> list = new ArrayList<Order>();
	    String query = "SELECT o.OrderID, o.Datetime, o.Total, o.Take, o.Returnmoney, o.EmployeeID, "
	            + "o.CustomerID, c.Name AS CustomerName, o.BonusitemID, p.Name AS BonusitemName "
	            + "FROM orderform o "
	            + "LEFT JOIN customer c ON o.CustomerID = c.CustomerID "
	            + "LEFT JOIN product p ON o.BonusitemID = p.ProductID ";
	    ResultSet rs = JDBCUtil.getInstance().executeQuery(query);
	    try {
	        while (rs.next()) {
	            list.add(GetOrderFromResultSet(rs));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	private Order GetOrderFromResultSet(ResultSet rs) {
	    Order order = new Order();
	    try {
	        order.setOrderID(rs.getString("OrderID"));
	        order.setDatetime(rs.getString("Datetime"));
	        order.setTotal(rs.getInt("Total"));
	        order.setTake(rs.getInt("Take"));
	        order.setReturnmoney(rs.getInt("Returnmoney"));
	        order.setEmployeeID(rs.getString("EmployeeID"));
	        order.setCustomerID(rs.getString("CustomerID"));
	        order.setCustomerName(rs.getString("CustomerName")); // Ensure alias matches query
	        order.setBonusitemID(rs.getString("BonusitemID"));
	        order.setBonusitemName(rs.getString("BonusitemName")); // Ensure alias matches query
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return order;
	}


	public void AddOrder(Order order) {
		String sql = "INSERT INTO orderform (OrderID, Total, Take, Returnmoney, Datetime, EmployeeID, CustomerID, BonusitemID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = JDBCUtil.getInstance().getConnection().prepareStatement(sql)) {
			pstmt.setString(1, order.getOrderID());
			pstmt.setDouble(2, order.getTotal());
			pstmt.setDouble(3, order.getTake());
			pstmt.setDouble(4, order.getReturnmoney());
			pstmt.setString(5, order.getDatetime());
			pstmt.setString(6, order.getEmployeeID());
			if (order.getCustomerID() != null) {
				pstmt.setString(7, order.getCustomerID());
			} else {
				pstmt.setNull(7, java.sql.Types.VARCHAR);
			}

			if (order.getBonusitemID() != null) {
				pstmt.setString(8, order.getBonusitemID());
			} else {
				pstmt.setNull(8, java.sql.Types.VARCHAR);
			}
			pstmt.executeUpdate();
//			System.out.println("order");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Order> getOrdersByCustomerID(String customerID) {
	    List<Order> list = new ArrayList<Order>();
	    String query = "SELECT o.OrderID, o.Datetime, o.Total, o.Take, o.Returnmoney, o.EmployeeID, "
	            + "o.CustomerID, c.Name AS CustomerName, o.BonusitemID, p.Name AS BonusitemName "
	            + "FROM orderform o "
	            + "LEFT JOIN customer c ON o.CustomerID = c.CustomerID "
	            + "LEFT JOIN product p ON o.BonusitemID = p.ProductID "
	            + "WHERE o.CustomerID = ?";  // Adding condition to filter by customerID

	    try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
	        statement.setString(1, customerID);  // Setting the customerID parameter

	        try (ResultSet rs = statement.executeQuery()) {
	            while (rs.next()) {
	                list.add(GetOrderFromResultSet(rs));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	public List<String> getOrderIDsByDateRange(String date_start, String date_end) {
		List<String> list = new ArrayList<>();
		String query = "SELECT OrderID FROM orderform WHERE STR_TO_DATE(Datetime, '%Y-%m-%d %H:%i:%s') BETWEEN STR_TO_DATE(?, '%d/%m/%Y') AND STR_TO_DATE(?, '%d/%m/%Y')";
		try (PreparedStatement pstmt = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
			pstmt.setString(1, date_start);
			pstmt.setString(2, date_end);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("OrderID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//        System.out.println(list.size());
		return list;
	}

    public List<Integer> getTotalSalesPerMonth() {
        List<Integer> salesPerMonth = new ArrayList<>();

        // Khởi tạo danh sách với 12 giá trị 0
        for (int i = 0; i < 12; i++) {
            salesPerMonth.add(0);
        }

        String query = "SELECT DATE_FORMAT(Datetime, '%m') AS month, SUM(Total) AS total_sales " +
                       "FROM orderform " +
                       "GROUP BY DATE_FORMAT(Datetime, '%m')";

        try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String month = rs.getString("month");
                int monthIndex = Integer.parseInt(month) - 1; // Chuyển đổi tháng thành chỉ số danh sách (0-11)
                int totalSales = rs.getInt("total_sales");
                salesPerMonth.set(monthIndex, totalSales);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Sửa để in ra lỗi
        }

        return salesPerMonth;
    }
    
    public Map<String, Integer> getItemCounts() {
        Map<String, Integer> itemCounts = new HashMap<>();

        String query = "SELECT p.Name, SUM(od.Quantity) AS total_quantity " +
                       "FROM orderdetail od " +
                       "JOIN product p ON od.ItemID = p.ProductID " +
                       "GROUP BY p.Name";

        try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String itemName = rs.getString("Name");
                int quantity = rs.getInt("total_quantity");
                itemCounts.put(itemName, quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi nếu có
        }

        return itemCounts;
    }
}
