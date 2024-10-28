package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCUtil {
    // Singleton instance
    private static JDBCUtil instance;

    // Connection object
    private Connection connection;

    // Private constructor to prevent instantiation
    private JDBCUtil() {
        try {
            // Đăng ký driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Thiết lập thông tin kết nối
            String url = "jdbc:mysql://localhost:3306/pbl3";
            String username = "root";
            String password = "12345678";

            // Lấy kết nối
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Static method to get the singleton instance
    public static JDBCUtil getInstance() {
        if (instance == null) {
            synchronized (JDBCUtil.class) {
                if (instance == null) {
                    instance = new JDBCUtil();
                }
            }
        }
        return instance;
    }

    // Method to get connection
    public Connection getConnection() {
        return connection;
    }

    // Method to close connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        try {
            if (connection != null && !connection.isClosed()) { // Kiểm tra kết nối đã được thiết lập và không đóng
                java.sql.Statement statement = connection.createStatement();
                rs = statement.executeQuery(query);
            } else {
                System.err.println("Connection is null or closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
