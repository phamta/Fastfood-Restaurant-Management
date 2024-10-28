package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.Combo;
import DTO.Itemplus;

public class Combo_DAL {
	private static Combo_DAL instance;

	private Combo_DAL() {

	}

	public static Combo_DAL getInstance() {
		if (instance == null) {
			instance = new Combo_DAL();
		}
		return instance;
	}
	
	public String generateNextComboID() {
	    String prefix = "CMBS"; // Prefix for the ComboID
	    String maxComboID = null;

	    String sql = "SELECT MAX(ComboID) as MaxComboID FROM combo WHERE ComboID LIKE 'CMBS%'";

	    try (PreparedStatement ps = JDBCUtil.getInstance().getConnection().prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        if (rs.next()) {
	            maxComboID = rs.getString("MaxComboID");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    if (maxComboID != null) {
	        int nextId = Integer.parseInt(maxComboID.substring(prefix.length())) + 1;
	        return prefix + String.format("%03d", nextId);
	    } else {
	        // Nếu không có ComboID nào, bắt đầu với CMBS001
	        return prefix + "001";
	    }
	}

	
	public void addCombo(Combo combo) {
	    String sql = "INSERT INTO combo (ComboID, ComboName, Price, Image) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement ps = JDBCUtil.getInstance().getConnection().prepareStatement(sql)) {
	        // Thiết lập các giá trị cho các tham số trong câu lệnh SQL
	        ps.setString(1, combo.getComboID());
	        ps.setString(2, combo.getComboName());
	        ps.setInt(3, combo.getPrice());
	        ps.setString(4, combo.getImage());
	        
	        // Thực thi câu lệnh
	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Combo added successfully.");
	        } else {
	            System.out.println("Failed to add combo.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public List<Combo> getAllCombo() {
		List<Combo> list = new ArrayList<>();
		String query = "SELECT * FROM combo";
		ResultSet rs = JDBCUtil.getInstance().executeQuery(query);
		try {
			while (rs.next()) {
				list.add(getComboFromResultSet(rs));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Combo getComboFromResultSet(ResultSet rs) {
		Combo combo = new Combo();
		try {
			String comboID = rs.getString("ComboID");
			if (!rs.wasNull()) {
				combo.setComboID(comboID);
			}
			combo.setComboName(rs.getString("ComboName"));
			combo.setPrice(rs.getInt("Price"));
			combo.setImage(rs.getString("Image"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return combo;
	}
	
	public void updateCombo(Combo combo) {
	    String sql = "UPDATE combo SET ComboName = ?, Price = ? WHERE ComboID = ?";

	    try (PreparedStatement ps = JDBCUtil.getInstance().getConnection().prepareStatement(sql)) {
	        ps.setString(1, combo.getComboName());
	        ps.setInt(2, combo.getPrice());
	        ps.setString(3, combo.getComboID());
	        
	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Combo updated successfully.");
	        } else {
	            System.out.println("No combo found with the given ComboID.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void deleteCombo(String comboId) {
	    String sql = "DELETE FROM combo WHERE ComboID = ?";

	    try (PreparedStatement ps = JDBCUtil.getInstance().getConnection().prepareStatement(sql)) {
	        ps.setString(1, comboId);
	        
	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Combo deleted successfully.");
	        } else {
	            System.out.println("No combo found with the given ComboID.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public List<Itemplus> getItemOfCombo(String comboid) {
		List<Itemplus> list = new ArrayList<Itemplus>();
		String query = "SELECT p.ProductID, p.Price, p.Name, cd.Quantity " + "FROM combodetail cd "
				+ "JOIN product p ON cd.ProductID = p.ProductID " + "WHERE cd.ComboID like '" + comboid + "';";
		ResultSet rs = JDBCUtil.getInstance().executeQuery(query);
		try {
			while (rs.next()) {
				list.add(getItemplusFromResultSet(rs));
//	            System.out.println(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private Itemplus getItemplusFromResultSet(ResultSet rs) {
		Itemplus itemplus = new Itemplus();
		try {
			itemplus.setItemID(rs.getString("ProductID"));
			itemplus.setItemName(rs.getString("Name"));
			itemplus.setQuantity(rs.getInt("Quantity"));
			itemplus.setPrice(rs.getInt("Price"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemplus;
	}

	public void addItemToCombo(String comboid, Itemplus itemplus) {
		String query = "INSERT INTO combodetail (ProductID, Quantity, ComboID) VALUES ('" + itemplus.getItemID() + "', "
				+ itemplus.getQuantity() + ", '" + comboid + "');";
		try {
			Statement statement = JDBCUtil.getInstance().getConnection().createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteComboDetail(String productId, String comboId) {
	    String sql = "DELETE FROM combodetail WHERE ProductID = ? AND ComboID = ?";
	    
	    try (PreparedStatement ps = JDBCUtil.getInstance().getConnection().prepareStatement(sql)) {
	        ps.setString(1, productId);
	        ps.setString(2, comboId);
	        
	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Record deleted successfully.");
	        } else {
	            System.out.println("No record found with the given ProductID and ComboID.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
