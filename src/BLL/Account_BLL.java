package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.Account_DAL;
import DTO.Account;

public class Account_BLL {

	private static Account_BLL instance;

	// Constructor private để ngăn việc tạo instance từ bên ngoài
	private Account_BLL() {
		// Any necessary initialization code here
	}

	// Phương thức static để lấy instance duy nhất của lớp
	public static synchronized Account_BLL getInstance() {
		if (instance == null) {
			instance = new Account_BLL();
		}
		return instance;
	}
	
	public List<Account> getAllAccount() {
		return Account_DAL.getInstance().GetAllUser();
	}

	public Account GetUserByLogin(String username, String password) {
		Account user = null;
		for (Account u : Account_DAL.getInstance().GetAllUser()) {
			if (u.getUsername().compareTo(username) == 0 && u.getPassword().compareTo(password) == 0) {
				user = u;
				break;
			}
		}
		return user;
	}
	
	public Account GetUserByEmployeeID(String employeeid) {
		Account user = null;
		for(Account u: Account_DAL.getInstance().GetAllUser()) {
			if(u.getUserID().compareTo(employeeid) == 0) {
				user = u;
				break;
			}
		}
		return user;
	}
	
	public void UpdatePassword(String employeeid, String password) {
		Account_DAL.getInstance().UpdatePassword(employeeid, password);
	}
	
	public void addAccount(Account user) {
		Account_DAL.getInstance().addAccount(user);
	}
	
	public void updateAccount(Account account) {
		Account_DAL.getInstance().updateAccount(account);
	}
	
	public List<Account> searchAccount(String search){
		List<Account> list = new ArrayList<Account>();
		for(Account account: getAllAccount()) {
			if(account.getUserID().contains(search.toUpperCase()) || account.getEmployeeName().contains(search)) {
				list.add(account);
			}
		}
		return list;
	}
}
