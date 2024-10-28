package DTO;

public class Itemplus extends Item {
	private int Quantity;

	public Itemplus(int quantity) {
		super();
		Quantity = quantity;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public Itemplus() {
		super();
	}

	public Itemplus(Item item, int quantity) {
		super();
		Quantity = quantity;
		this.setImagePath(item.getImagePath());
		this.setItemID(item.getItemID());
		this.setItemName(item.getItemName());
		this.setPrice(item.getPrice());
		this.setType(item.getType());
	}

	@Override
	public String toString() {
		return "Itemplus [Quantity=" + Quantity + ", toString()=" + super.toString() + "]";
	}
	
	public int getTotal() {
		return Quantity * getPrice();
	}

	public Itemplus(String itemID, String image, String name, int price, String type, int quantity) {
		super(itemID, image, name, price, type);
		Quantity = quantity;
	}
	
	
}
