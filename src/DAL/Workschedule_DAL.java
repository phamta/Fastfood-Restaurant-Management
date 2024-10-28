package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.Workschedule;

public class Workschedule_DAL {
	private static Workschedule_DAL instance;
	
	private Workschedule_DAL() {
		
	}
	
	public static Workschedule_DAL getInstance() {
		if(instance == null) {
			instance = new Workschedule_DAL();
		}
		return instance;
	}
	
    // Get work schedules by specific date
    public List<Workschedule> getWorkSchedulesByDate(String date) {
        List<Workschedule> workSchedules = new ArrayList<Workschedule>();
        String query = "SELECT ws.Date, ws.Start_time, ws.End_time, ws.EmployeeID, e.Fullname AS EmployeeName " +
                "FROM work_schedule ws " +
                "JOIN employee e ON ws.EmployeeID = e.EmployeeID " +
                "WHERE ws.Date = ?";

        try (PreparedStatement preparedStatement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, date);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                	Workschedule workschedule = new Workschedule();
                    workschedule.setDate(resultSet.getString("Date"));
                    workschedule.setStart_time(resultSet.getString("Start_time"));
                    workschedule.setEnd_time(resultSet.getString("End_time"));
                    workschedule.setEmployeeID(resultSet.getString("EmployeeID"));
                    workschedule.setEmployeeName(resultSet.getString("EmployeeName"));
                    workSchedules.add(workschedule);
                }
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return workSchedules;
    }
    
    public List<String> getEmployeeIDsByDate(String date) {
        List<String> employeeIDs = new ArrayList<>();
        String query = "SELECT EmployeeID FROM work_schedule WHERE Date = ?";

        try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {

            statement.setString(1, date);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employeeIDs.add(resultSet.getString("EmployeeID"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Thay thế bằng xử lý lỗi phù hợp với ứng dụng của bạn
        }
        return employeeIDs;
    }
    
    // Insert a new work schedule
    public void insert(Workschedule workschedule) {
        String query = "INSERT INTO work_schedule (Date, Start_time, End_time, EmployeeID) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, workschedule.getDate());
            statement.setString(2, workschedule.getStart_time());
            statement.setString(3, workschedule.getEnd_time());
            statement.setString(4, workschedule.getEmployeeID());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Delete a work schedule by EmployeeID and Date
    public void delete(String employeeID, String date) {
        String query = "DELETE FROM work_schedule WHERE EmployeeID = ? AND Date = ?";
        
        try (PreparedStatement statement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, employeeID);
            statement.setString(2, date);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getWorkScheduleByEmployeeAndDate(String date, String employeeID) {
        String query = "SELECT Start_time, End_time FROM work_schedule WHERE Date = ? AND EmployeeID = ?";
        String schedule = "";
        
        try (PreparedStatement preparedStatement = JDBCUtil.getInstance().getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, employeeID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String startTime = resultSet.getString("Start_time");
                    String endTime = resultSet.getString("End_time");
                    schedule = startTime + " - " + endTime;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return schedule;
    }
}
