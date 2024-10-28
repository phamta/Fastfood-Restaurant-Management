package DTO;

public class Timekeeping {
	private String EmployeeID;
	private String Day;
	private String In_time;
	private String Out_time;
	private float Working_hour;
	private float Late;
	private int Week;

	public Timekeeping(String employeeID, String day, String in_time, String out_time, float working_hour, float late, int week) {
		EmployeeID = employeeID;
		Day = day;
		In_time = in_time;
		Out_time = out_time;
		Working_hour = working_hour;
		Late = late;
		Week = week;
	}

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public String getDay() {
		return Day;
	}

	public void setDay(String day) {
		Day = day;
	}

	public String getIn_time() {
		return In_time;
	}

	public void setIn_time(String in_time) {
		In_time = in_time;
	}

	public String getOut_time() {
		return Out_time;
	}

	public void setOut_time(String out_time) {
		Out_time = out_time;
	}

	public float getWorking_hour() {
		return Working_hour;
	}

	public void setWorking_hour(float working_hour) {
		Working_hour = working_hour;
	}

	public float getLate() {
		return Late;
	}

	public void setLate(float late) {
		Late = late;
	}

	public int getWeek() {
		return Week;
	}

	public void setWeek(int week) {
		Week = week;
	}

	public Timekeeping() {
		
	}

}
