package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import DTO.Timekeeping;

public class Timekeeping_DAL {
	private static Timekeeping_DAL instance;
	
	private Timekeeping_DAL() {
	  
	}

	public static Timekeeping_DAL getInstance() {
	  if(instance == null) {
	    instance = new Timekeeping_DAL();
	  }
	  return instance;
	}
	
	public float getTotalWorkingHours(int month, int year, String employeeId) {
		float result = 0;
        String sql = "SELECT SUM(Working_hour) AS total_hours " +
                "FROM timekeeping " +
                "WHERE EmployeeID = ? AND " +
                "MONTH(STR_TO_DATE(Day, '%d/%m/%Y')) = ? AND " +
                "YEAR(STR_TO_DATE(Day, '%d/%m/%Y')) = ?";
        
        try {
			PreparedStatement ps = JDBCUtil.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, employeeId);
			ps.setInt(2, month);
			ps.setInt(3, year);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result += rs.getFloat("total_hours");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Map<String, Float> getTotalWorkingHoursByWeek(int weekNumber) {
        Map<String, Float> employeeHoursMap = new HashMap<>();

        String query = "SELECT e.Fullname, SUM(t.Working_hour) as TotalHours " +
                       "FROM timekeeping t " +
                       "JOIN employee e ON t.EmployeeID = e.EmployeeID " +
                       "WHERE t.Week = ? " +
                       "GROUP BY e.Fullname";

        try (PreparedStatement preparedStatement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, weekNumber);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String employeeName = resultSet.getString("Fullname");
                    float totalHours = resultSet.getFloat("TotalHours");
                    employeeHoursMap.put(employeeName, totalHours);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeHoursMap;
    }
	
	public String getTimeInOutByDate(String employeeId, String date) {
	    String result = "";
	    String query = "SELECT In_time, Out_time " +
	                   "FROM timekeeping " +
	                   "WHERE EmployeeID = ? AND Day = ?";

	    try (PreparedStatement preparedStatement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
	        preparedStatement.setString(1, employeeId);
	        preparedStatement.setString(2, date);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                String inTime = resultSet.getString("In_time");
	                String outTime = resultSet.getString("Out_time");
	                result = inTime + " - " + outTime;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	
	public void addTimekeepingRecord(Timekeeping timekeeping) {
	    String query = "INSERT INTO timekeeping (Day, In_time, Out_time, Working_hour, Late, EmployeeID, Week) " +
	                   "VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement preparedStatement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
	        preparedStatement.setString(1, timekeeping.getDay());
	        preparedStatement.setString(2, timekeeping.getIn_time());
	        preparedStatement.setString(3, timekeeping.getOut_time());
	        preparedStatement.setFloat(4, timekeeping.getWorking_hour());
	        preparedStatement.setFloat(5, timekeeping.getLate());
	        preparedStatement.setString(6, timekeeping.getEmployeeID());
	        preparedStatement.setInt(7, timekeeping.getWeek());

	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public float getTotalWorkingHoursByEmployeeAndWeek(String employeeId, int weekNumber) {
	    float totalHours = 0;
	    String query = "SELECT SUM(Working_hour) AS TotalHours " +
	                   "FROM timekeeping " +
	                   "WHERE EmployeeID = ? AND Week = ?";

	    try (PreparedStatement preparedStatement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
	        preparedStatement.setString(1, employeeId);
	        preparedStatement.setInt(2, weekNumber);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                totalHours = resultSet.getFloat("TotalHours");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return totalHours;
	}

	public float getTotalLateByEmployeeAndWeek(String employeeId, int weekNumber) {
	    float totalLate = 0;
	    String query = "SELECT SUM(Late) AS TotalLate " +
	                   "FROM timekeeping " +
	                   "WHERE EmployeeID = ? AND Week = ?";

	    try (PreparedStatement preparedStatement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
	        preparedStatement.setString(1, employeeId);
	        preparedStatement.setInt(2, weekNumber);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                totalLate = resultSet.getFloat("TotalLate");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return totalLate;
	}

}
