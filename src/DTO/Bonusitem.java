package DTO;

public class Bonusitem extends Item {
	private int Point;
	private int Status;

	public int getPoint() {
		return Point;
	}

	public void setPoint(int point) {
		Point = point;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public Bonusitem(String itemID, String image, String name, int price, String type, int point, int status) {
		super(itemID, image, name, price, type);
		Point = point;
		Status = status;
	}

	public Bonusitem() {

	}

}
