package DTO;

public class Workschedule {
	private String Date;
	private String Start_time;
	private String End_time;
	private String EmployeeID;
	private String EmployeeName;

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getStart_time() {
		return Start_time;
	}

	public void setStart_time(String start_time) {
		Start_time = start_time;
	}

	public String getEnd_time() {
		return End_time;
	}

	public void setEnd_time(String end_time) {
		End_time = end_time;
	}

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public Workschedule(String date, String start_time, String end_time, String employeeID, String employeeName) {
		Date = date;
		Start_time = start_time;
		End_time = end_time;
		EmployeeID = employeeID;
		EmployeeName = employeeName;
	}

	public Workschedule() {
	}

	@Override
	public String toString() {
		return "Workschedule [Date=" + Date + ", Start_time=" + Start_time + ", End_time=" + End_time + ", EmployeeID="
				+ EmployeeID + ", EmployeeName=" + EmployeeName + "]";
	}

}
