package DTO;

public class Orderdetail {
	private String OrderID;
	private String ItemID;
	private int Quantity;
	private Double Value;

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getItemID() {
		return ItemID;
	}

	public void setItemID(String itemID) {
		ItemID = itemID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public Double getValue() {
		return Value;
	}

	public void setValue(Double value) {
		Value = value;
	}

	public Orderdetail(String orderID, String itemID, int quantity, Double value) {
		super();
		OrderID = orderID;
		ItemID = itemID;
		Quantity = quantity;
		Value = value;
	}

	public Orderdetail() {
		super();
	}

}
