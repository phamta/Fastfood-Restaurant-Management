package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Category;

public class Category_DAL {
	private static Category_DAL instance;

	private Category_DAL() {

	}

	public static Category_DAL getInstance() {
		if (instance == null) {
			instance = new Category_DAL();
		}
		return instance;
	}

	public List<Category> getAllCategory() {
		List<Category> list = new ArrayList<Category>();
		String query = "SELECT * FROM category";

		try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query);
				ResultSet rs = statement.executeQuery()) {

			while (rs.next()) {
				Category category = new Category();
				category.setCategoryID(rs.getInt("CategoryID"));
				category.setCategoryName(rs.getString("CategoryName"));
				category.setStatus(rs.getInt("Status"));
				list.add(category);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public void insertCategory(Category category) {
		String query = "INSERT INTO category (CategoryName, Status) VALUES (?, ?)";
		try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
			statement.setString(1, category.getCategoryName());
			statement.setInt(2, category.getStatus());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateCategory(Category category) {
		String query = "UPDATE category SET CategoryName = ?, Status = ? WHERE CategoryID = ?";
		try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
			statement.setString(1, category.getCategoryName());
			statement.setInt(2, category.getStatus());
			statement.setInt(3, category.getCategoryID());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
