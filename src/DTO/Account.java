package DTO;

public class Account {
	private String Username;
	private String Password;
	private String UserID;
	private int Quyen;
	private int Status;
	private String EmployeeName;

	public Account(String username, String password, String userID, int quyen) {
		Username = username;
		Password = password;
		UserID = userID;
		Quyen = quyen;
	}

	public Account() {

	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public int getQuyen() {
		return Quyen;
	}

	public void setQuyen(int quyen) {
		Quyen = quyen;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

}
