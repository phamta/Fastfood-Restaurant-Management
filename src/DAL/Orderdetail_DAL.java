package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.Itemplus;

public class Orderdetail_DAL {
	private static Orderdetail_DAL instance;

	private Orderdetail_DAL() {
		
	}

	public static Orderdetail_DAL getInstance() {
		if (instance == null) {
			instance = new Orderdetail_DAL();
		}
		return instance;
	}

	public void AddOrderdetail(String orderid, Itemplus itemplus) {
	    String sql = String.format("INSERT INTO orderdetail (Quantity, OrderID, ItemID, Value) VALUES (%d, '%s', '%s', %d)",
	            itemplus.getQuantity(), orderid, itemplus.getItemID(), itemplus.getTotal());
	    
	    try (Statement stmt = JDBCUtil.getInstance().getConnection().createStatement()) {
	         
	        stmt.executeUpdate(sql);
//	        System.out.println("Order detail added: " + sql);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

    public List<Itemplus> GetItemplusByOrderID(String orderid) {
        List<Itemplus> itemplusList = new ArrayList<>();
        
        String query = "SELECT p.ProductID, p.Name, p.Price, p.Image, od.Quantity " +
                       "FROM orderdetail od " +
                       "JOIN product p ON od.ItemID = p.ProductID " +
                       "WHERE od.OrderID = ?";
        
        try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, orderid);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String productID = resultSet.getString("ProductID");
                String name = resultSet.getString("Name");
//                String type = resultSet.getString("Type");
                int price = resultSet.getInt("Price");
                String image = resultSet.getString("Image");
                int quantity = resultSet.getInt("Quantity");

                Itemplus itemplus = new Itemplus(productID, image, name, price, "", quantity);
                itemplusList.add(itemplus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemplusList;
    }
    
    public Map<String, Integer> getCustomerPurchaseStats(String customerID) {
        Map<String, Integer> purchaseStats = new HashMap<>();
        List<String> orderIDs = new ArrayList<>();

        String orderQuery = "SELECT OrderID FROM orderform WHERE CustomerID = ?";

        try (PreparedStatement orderStmt = JDBCUtil.getInstance().getConnection().prepareStatement(orderQuery)) {
            orderStmt.setString(1, customerID);
            ResultSet orderRs = orderStmt.executeQuery();

            while (orderRs.next()) {
                orderIDs.add(orderRs.getString("OrderID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String orderID : orderIDs) {
            String detailQuery = "SELECT p.Name, od.Quantity " +
                                 "FROM orderdetail od " +
                                 "JOIN product p ON od.ItemID = p.ProductID " +
                                 "WHERE od.OrderID = ?";

            try (PreparedStatement detailStmt = JDBCUtil.getInstance().getConnection().prepareStatement(detailQuery)) {
                detailStmt.setString(1, orderID);
                ResultSet detailRs = detailStmt.executeQuery();

                while (detailRs.next()) {
                    String productName = detailRs.getString("Name");
                    int quantity = detailRs.getInt("Quantity");

                    purchaseStats.put(productName, purchaseStats.getOrDefault(productName, 0) + quantity);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return purchaseStats;
    }
}