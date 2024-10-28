package DTO;

public class Order {
	private String OrderID;
	private String Datetime;
	private int Total;
	private int Take;
	private int Returnmoney;
	private String EmployeeID;
	private String CustomerID;
	private String CustomerName;
	private String BonusitemID;
	private String BonusitemName;

	public Order(String orderID, String datetime, int total, int take, int returnmoney, String employeeID,
			String customerID, String bonusitemID) {
		OrderID = orderID;
		Datetime = datetime;
		Total = total;
		Take = take;
		Returnmoney = returnmoney;
		EmployeeID = employeeID;
		CustomerID = customerID;
		BonusitemID = bonusitemID;
	}

	public Order() {
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getDatetime() {
		return Datetime;
	}

	public void setDatetime(String datetime) {
		Datetime = datetime;
	}

	public int getTotal() {
		return Total;
	}

	public void setTotal(int total) {
		Total = total;
	}

	public int getTake() {
		return Take;
	}

	public void setTake(int take) {
		Take = take;
	}

	public int getReturnmoney() {
		return Returnmoney;
	}

	public void setReturnmoney(int returnmoney) {
		Returnmoney = returnmoney;
	}

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getBonusitemID() {
		return BonusitemID;
	}

	public void setBonusitemID(String bonusitemID) {
		BonusitemID = bonusitemID;
	}

	public String getBonusitemName() {
		return BonusitemName;
	}

	public void setBonusitemName(String bonusitemName) {
		BonusitemName = bonusitemName;
	}
}