package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.Employee_DAL;
import DTO.Employee;

public class Employee_BLL {
	private static Employee_BLL instance;
	
	private Employee_BLL() {
		
	}
	
	public static Employee_BLL getInstance() {
		if(instance == null) {
			instance = new Employee_BLL();
		}
		return instance;
	}
	
	public List<Employee> getAllEmployeeAvailable() {
		List<Employee> list = new ArrayList<Employee>();
		for(Employee employee: Employee_DAL.getInstance().GetAllEmployee()) {
			if(employee.getIsDeleted() == 0) {
				list.add(employee);
			}
		}
		return list;
	}
	
	public Employee getEmployeeByID(String employeeid) {
		Employee employee = new Employee();
		for (Employee temp : Employee_DAL.getInstance().GetAllEmployee()) {
			if(temp.getEmployeeID().compareTo(employeeid) == 0) {
				employee = temp;
				break;
			}
		}
		return employee;
	}
	
	public boolean checkEmployeeID(String employeeid) {
		boolean check = false;
		for (Employee employee : Employee_DAL.getInstance().GetAllEmployee()) {
			if(employee.getEmployeeID().compareTo(employeeid) == 0) {
				check = true;
				break;
			}
		}
		return check;
	}
	
	public List<Employee> getAllEmployee(){
		return Employee_DAL.getInstance().GetAllEmployee();
	}
	
	public void AddEmployee(Employee employee) {
		Employee_DAL.getInstance().AddEmployee(employee);
	}
	
	public void UpdateEmployee(Employee employee) {
		Employee_DAL.getInstance().UpdateEmployee(employee);
	}
	
	public void DeleteEmployee(String employeeid) {
		Employee_DAL.getInstance().DeleteEmployee(employeeid);
	}
	
    public List<Employee> getEmployeesNotInList(List<String> employeeIds) {
        List<Employee> filteredEmployees = new ArrayList<Employee>();

        for (Employee employee : getAllEmployee()) {
            if (!employeeIds.contains(employee.getEmployeeID())) {
                filteredEmployees.add(employee);
            }
        }
        
        return filteredEmployees;
    }
    
    public List<Employee> getListEmployeeBySearch(String search){
    	List<Employee> list = new ArrayList<Employee>();
    	for(Employee employee: getAllEmployee()) {
    		if(employee.getEmployeeID().contains(search.toUpperCase()) || employee.getFullname().contains(search)) {
    			list.add(employee);
    		}
    	}
    	return list;
    }
}
