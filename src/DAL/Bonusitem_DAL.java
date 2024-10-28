package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Bonusitem;

public class Bonusitem_DAL {
	private static Bonusitem_DAL instance;

	private Bonusitem_DAL() {

	}

	public static Bonusitem_DAL getIstance() {
		if (instance == null) {
			instance = new Bonusitem_DAL();
		}
		return instance;
	}

	public List<Bonusitem> getAllBonusitem() {
	    List<Bonusitem> list = new ArrayList<Bonusitem>();

	    String query = "SELECT b.ProductID, p.Name, p.Image, p.Price, c.CategoryName AS Type, b.Point, b.Status " +
	                   "FROM bonusitem b " +
	                   "JOIN Product p ON b.ProductID = p.ProductID " +
	                   "JOIN category c ON p.CategoryID = c.CategoryID"; // Thêm JOIN với bảng category

	    try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query);
	         ResultSet rs = statement.executeQuery()) {

	        while (rs.next()) {
	            Bonusitem bonusitem = new Bonusitem();
	            bonusitem.setItemID(rs.getString("ProductID"));
	            bonusitem.setItemName(rs.getString("Name"));
	            bonusitem.setImagePath(rs.getString("Image"));
	            bonusitem.setPrice(rs.getInt("Price"));
	            bonusitem.setType(rs.getString("Type")); // Lấy CategoryName làm Type
	            bonusitem.setPoint(rs.getInt("Point"));
	            bonusitem.setStatus(rs.getInt("Status"));
	            list.add(bonusitem);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public void updateBonusitem(Bonusitem bonusitem) {
	    String query = "UPDATE bonusitem SET Point = ?, Status = ? WHERE ProductID = ?";

	    try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
	        statement.setInt(1, bonusitem.getPoint());
	        statement.setInt(2, bonusitem.getStatus());
	        statement.setString(3, bonusitem.getItemID());

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void addBonusitem(Bonusitem bonusitem) {
		String query = "INSERT INTO bonusitem (Point, ProductID, Status) VALUES (?, ?, ?)";
		try {
			PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query);
			statement.setInt(1, bonusitem.getPoint());
			statement.setString(2, bonusitem.getItemID());
			statement.setInt(3, bonusitem.getStatus());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
