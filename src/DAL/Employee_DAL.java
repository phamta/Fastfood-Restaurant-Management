package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Employee;

public class Employee_DAL {
	private static Employee_DAL instance;
	
	private Employee_DAL() {
		
	}
	
	public static Employee_DAL getInstance() {
		if(instance == null) {
			instance = new Employee_DAL();
		}
		return instance;
	}
	
	public List<Employee> GetAllEmployee(){
		List<Employee> list = new ArrayList<Employee>();
		ResultSet rs = JDBCUtil.getInstance().executeQuery("select * from employee");
		try {
			while (rs.next()) {
				list.add(GetEmployeeFromResultSet(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	private Employee GetEmployeeFromResultSet(ResultSet rs) {
		Employee employee = new Employee();
		try {
			employee.setEmployeeID(rs.getString("EmployeeID"));
			employee.setFullname(rs.getString("Fullname"));
			employee.setWage_coefficient(rs.getInt("Wage_coefficient"));
			employee.setBirthday(rs.getString("Birthday"));
			employee.setPhone_number(rs.getString("Phone_number"));
			employee.setAddress(rs.getString("Address"));
			employee.setImage(rs.getString("Image"));
			employee.setGender(rs.getString("Gender"));
			employee.setIsDeleted(rs.getInt("isDeleted"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
	
	public void AddEmployee(Employee employee) {
        String query = "INSERT INTO employee (EmployeeID, Fullname, Wage_coefficient, Birthday, Phone_number, Address, Image, Gender, isDeleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
            ps.setString(1, employee.getEmployeeID());
            ps.setString(2, employee.getFullname());
            ps.setFloat(3, employee.getWage_coefficient());
            ps.setString(4, employee.getBirthday());
            ps.setString(5, employee.getPhone_number());
            ps.setString(6, employee.getAddress());
            ps.setString(7, employee.getImage());
            ps.setString(8, employee.getGender());
            ps.setInt(9, 0);
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void UpdateEmployee(Employee employee) {
        String query = "UPDATE employee SET Fullname = ?, Wage_coefficient = ?, Birthday = ?, Phone_number = ?, Address = ?, Image = ?, Gender = ?, isDeleted = ? WHERE EmployeeID = ?";
        try (PreparedStatement ps = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
            ps.setString(1, employee.getFullname());
            ps.setFloat(2, employee.getWage_coefficient());
            ps.setString(3, employee.getBirthday());
            ps.setString(4, employee.getPhone_number());
            ps.setString(5, employee.getAddress());
            ps.setString(6, employee.getImage());
            ps.setString(7, employee.getGender());
            ps.setInt(8, 0);
            ps.setString(9, employee.getEmployeeID());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public void DeleteEmployee(String employeeID) {
        String query = "UPDATE employee SET isDeleted = 1 WHERE EmployeeID = ?";
        try (PreparedStatement ps = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
            ps.setString(1, employeeID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
