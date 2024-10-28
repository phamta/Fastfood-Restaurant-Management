package DTO;

public class Employee {
	private String EmployeeID;
	private String Fullname;
	private int Wage_coefficient;
	private String Birthday;
	private String Phone_number;
	private String Address;
	private String Image;
	private String Gender;
	private int isDeleted;

	public Employee(String employeeID, String fullname, int wage_coefficient, String birthday, String phone_number,
			String address, String image, String gender) {
		super();
		EmployeeID = employeeID;
		Fullname = fullname;
		Wage_coefficient = wage_coefficient;
		Birthday = birthday;
		Phone_number = phone_number;
		Address = address;
		Image = image;
		Gender = gender;
	}

	public Employee() {

	}

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public String getFullname() {
		return Fullname;
	}

	public void setFullname(String fullname) {
		Fullname = fullname;
	}

	public int getWage_coefficient() {
		return Wage_coefficient;
	}

	public void setWage_coefficient(int wage_coefficient) {
		Wage_coefficient = wage_coefficient;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public String getPhone_number() {
		return Phone_number;
	}

	public void setPhone_number(String phone_number) {
		Phone_number = phone_number;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return Fullname;
	}

}
