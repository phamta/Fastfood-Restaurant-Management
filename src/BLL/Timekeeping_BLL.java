package BLL;

import java.util.Map;

import DAL.Timekeeping_DAL;
import DTO.Timekeeping;

public class Timekeeping_BLL {
	private static Timekeeping_BLL instance;
	
	private Timekeeping_BLL() {
	  
	}

	public static Timekeeping_BLL getInstance() {
	  if(instance == null) {
	    instance = new Timekeeping_BLL();
	  }
	  return instance;
	}

	public float getTotalWorkingHours(int month, int year, String employeeId) {
		return Timekeeping_DAL.getInstance().getTotalWorkingHours(month, year, employeeId);
	}
	
	public Map<String, Float> getTotalWorkingHoursByWeek(int weekNumber){
		return Timekeeping_DAL.getInstance().getTotalWorkingHoursByWeek(weekNumber);
	}
	
	public String getTimeInOutByDate(String employeeId, String date) {
		return Timekeeping_DAL.getInstance().getTimeInOutByDate(employeeId, date);
	}
	
	public void addTimekeepingRecord(Timekeeping timekeeping) {
		Timekeeping_DAL.getInstance().addTimekeepingRecord(timekeeping);
	}
	
	public float getTotalWorkingHoursByEmployeeAndWeek(String employeeId, int weekNumber) {
		return Timekeeping_DAL.getInstance().getTotalWorkingHoursByEmployeeAndWeek(employeeId, weekNumber);
	}
	
	public float getTotalLateByEmployeeAndWeek(String employeeId, int weekNumber) {
		return Timekeeping_DAL.getInstance().getTotalLateByEmployeeAndWeek(employeeId, weekNumber);
	}
}
