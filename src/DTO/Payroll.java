package DTO;

public class Payroll {
	private String EmployeeID;
	private String EmployeeName;
	private int Wage;
	private int Bonus;
	private int Overtime;
	private int Fine;
	private int Total;
	private String PaymentDate;
	private int Status;
	private String Note;

	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public int getWage() {
		return Wage;
	}

	public void setWage(int wage) {
		Wage = wage;
	}

	public int getBonus() {
		return Bonus;
	}

	public void setBonus(int bonus) {
		Bonus = bonus;
	}

	public int getOvertime() {
		return Overtime;
	}

	public void setOvertime(int overtime) {
		Overtime = overtime;
	}

	public int getFine() {
		return Fine;
	}

	public void setFine(int fine) {
		Fine = fine;
	}

	public int getTotal() {
		return Total;
	}

	public void setTotal(int total) {
		Total = total;
	}

	public String getPaymentDate() {
		return PaymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		PaymentDate = paymentDate;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public Payroll(String employeeID, String employeeName, int wage, int bonus, int overtime, int fine, int total,
			String paymentDate, int status, String note) {
		EmployeeID = employeeID;
		EmployeeName = employeeName;
		Wage = wage;
		Bonus = bonus;
		Overtime = overtime;
		Fine = fine;
		Total = total;
		PaymentDate = paymentDate;
		Status = status;
		setNote(note);
	}


	public Payroll() {
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	@Override
	public String toString() {
		return "Payroll [EmployeeID=" + EmployeeID + ", EmployeeName=" + EmployeeName + ", Wage=" + Wage + ", Bonus="
				+ Bonus + ", Overtime=" + Overtime + ", Fine=" + Fine + ", Total=" + Total + ", PaymentDate="
				+ PaymentDate + ", Status=" + Status + ", Note=" + Note + "]";
	}

}
