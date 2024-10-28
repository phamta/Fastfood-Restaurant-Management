package DTO;

public class Category {
	private int CategoryID;
	private String CategoryName;
	private int Status;

	public int getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public Category(int categoryID, String categoryName, int status) {
		CategoryID = categoryID;
		CategoryName = categoryName;
		Status = status;
	}

	public Category() {
	}

	@Override
	public String toString() {
		return CategoryName;
	}

}
