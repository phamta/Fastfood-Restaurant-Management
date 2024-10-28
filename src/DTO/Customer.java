package DTO;

public class Customer {
	private String CustomerID;
	private String Phone_number;
	private String Fullname;
	private int Point;

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getPhone_number() {
		return Phone_number;
	}

	public void setPhone_number(String phone_number) {
		Phone_number = phone_number;
	}

	public String getFullname() {
		return Fullname;
	}

	public void setFullname(String fullname) {
		Fullname = fullname;
	}

	public int getPoint() {
		return Point;
	}

	public void setPoint(int point) {
		Point = point;
	}

	public Customer(String customerID, String phone_number, String fullname, int point) {
		super();
		CustomerID = customerID;
		Phone_number = phone_number;
		Fullname = fullname;
		Point = point;
	}

	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return Phone_number + ": " + Fullname;
	}

}
