package BLL;

import java.util.List;
import java.util.Map;

import DAL.Timekeeping_DAL;
import DAL.Workschedule_DAL;
import DTO.Workschedule;

public class Workschedule_BLL {
	private static Workschedule_BLL instance;
	
	private Workschedule_BLL() {
		
	}
	
	public static Workschedule_BLL getInstance() {
		if(instance == null) {
			instance = new Workschedule_BLL();
		}
		return instance;
	}
	
	public String getWorkSchedulesByDate(String date) {
		String result = "";
		for(Workschedule workschedule: Workschedule_DAL.getInstance().getWorkSchedulesByDate(date)) {
			result += workschedule.getEmployeeName() + "\t"
					+ "Giờ làm: " + workschedule.getStart_time() + " - " + workschedule.getEnd_time() + "\n";
		}
		return result;
	}
	
	public List<String> getEmployeeIDsByDate(String date){
		return Workschedule_DAL.getInstance().getEmployeeIDsByDate(date);
	}
	
	public void insert(Workschedule workschedule) {
		Workschedule_DAL.getInstance().insert(workschedule);
	}
	
	public List<Workschedule> getWorkschedulelist(String date){
		return Workschedule_DAL.getInstance().getWorkSchedulesByDate(date);
	}
	
	public void delete(List<Workschedule> list) {
		for(Workschedule workschedule: list) {
			Workschedule_DAL.getInstance().delete(workschedule.getEmployeeID(), workschedule.getDate());
		}
	}
	
	public String getWorkScheduleByEmployeeAndDate(String date, String employeeID) {
		return Workschedule_DAL.getInstance().getWorkScheduleByEmployeeAndDate(date, employeeID);
	}
}
