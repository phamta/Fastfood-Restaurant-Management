package BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DAL.Payroll_DAL;
import DTO.Employee;
import DTO.Payroll;

public class Payroll_BLL {
	private static Payroll_BLL instance;
	
	private Payroll_BLL() {
	  
	}

	public static Payroll_BLL getInstance() {
	  if(instance == null) {
	    instance = new Payroll_BLL();
	  }
	  return instance;
	}
	
	public List<Payroll> getPayrollByMonthYear(int month, int year){
		return Payroll_DAL.getInstance().getPayrollByMonthYear(month, year);
	}
	
	public List<Payroll> createPayrollList(int month, int year){
		List<Payroll> list = new ArrayList<Payroll>();
		for(Employee employee: Employee_BLL.getInstance().getAllEmployeeAvailable()) {
			String employeeid = employee.getEmployeeID();
			String employeename = employee.getFullname();
			int wage = (int)(employee.getWage_coefficient() * Timekeeping_BLL.getInstance().getTotalWorkingHours(month, year, employeeid));
			Payroll payroll = new Payroll(employeeid, employeename, wage, 0,0,0,0,"",0,"");
			list.add(payroll);
		}
		
		return list;
	}
	
	public int getTotalByYear(int year) {
		return Payroll_DAL.getInstance().getTotalByYear(year);
	}
	
	public List<Integer> getMonthlyTotalsByYear(int year){
		return Payroll_DAL.getInstance().getMonthlyTotalsByYear(year);
	}
	
	public Map<String, Integer> getEmployeeTotalsByMonthYear(int month, int year){
		return Payroll_DAL.getInstance().getEmployeeTotalsByMonthYear(month, year);
	}
	
	public void updatePayroll(int month, int year, List<Payroll> list) {
		for(Payroll payroll: list) {
			Payroll_DAL.getInstance().updatePayroll(month, year, payroll);
		}
	}
}
