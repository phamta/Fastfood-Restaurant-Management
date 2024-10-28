package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Item;

public class Item_DAL {
    private static Item_DAL instance;

    private Item_DAL() {

    }

    public static Item_DAL getInstance() {
        if (instance == null) {
            instance = new Item_DAL();
        }
        return instance;
    }

    public List<Item> GetAllItem() {
        List<Item> list = new ArrayList<Item>();
        String query = "SELECT p.ProductID, p.Name, p.Price, p.Image, p.CategoryID, c.CategoryName AS Type " +
                       "FROM product p " +
                       "JOIN category c ON p.CategoryID = c.CategoryID;";
        ResultSet rs = JDBCUtil.getInstance().executeQuery(query);
        try {
            while (rs.next()) {
                list.add(GetItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Item GetItemFromResultSet(ResultSet rs) {
        Item item = new Item();
        try {
            item.setItemID(rs.getString("ProductID"));
            item.setImagePath(rs.getString("Image"));
            item.setPrice(rs.getInt("Price"));
            item.setItemName(rs.getString("Name"));
            item.setCategoryID(rs.getInt("CategoryID"));
            item.setType(rs.getString("Type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    // Insert method
    public boolean insertItem(Item item) {
        String query = "INSERT INTO product (ProductID, Image, Name, Price, CategoryID) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = JDBCUtil.getInstance().getConnection().prepareStatement(query);
            pstmt.setString(1, item.getItemID());
            pstmt.setString(2, item.getImagePath());
            pstmt.setString(3, item.getItemName());
            pstmt.setInt(4, item.getPrice());
            pstmt.setInt(5, item.getCategoryID());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update method
    public boolean updateItem(Item item) {
        String query = "UPDATE product SET Image = ?, Name = ?, Price = ?, CategoryID = ? WHERE ProductID = ?";
        try {
            PreparedStatement pstmt = JDBCUtil.getInstance().getConnection().prepareStatement(query);
            pstmt.setString(1, item.getImagePath());
            pstmt.setString(2, item.getItemName());
            pstmt.setInt(3, item.getPrice());
            pstmt.setInt(4, item.getCategoryID());
            pstmt.setString(5, item.getItemID());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete method
    public boolean deleteItem(String itemID) {
        String query = "DELETE FROM product WHERE ProductID = ?";
        try {
            PreparedStatement pstmt = JDBCUtil.getInstance().getConnection().prepareStatement(query);
            pstmt.setString(1, itemID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
