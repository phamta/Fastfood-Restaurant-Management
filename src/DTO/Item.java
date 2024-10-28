package DTO;

public class Item {
	private String ItemID;
	private String Image;
	private String Name;
	private int Price;
	private int CategoryID;
	private String Type;

	public int getCategoryID() {
		return CategoryID;
	}

	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}


	public Item() {
		super();
	}

	public Item(String imagePath, String itemName, int price) {
		this.Image = imagePath;
		this.Name = itemName;
		this.Price = price;
	}

	public Item(String itemID, String image, String name, int price, String type) {
		super();
		ItemID = itemID;
		Image = image;
		Name = name;
		Price = price;
		Type = type;
	}

	public String getImagePath() {
		return Image;
	}

	public void setImagePath(String imagePath) {
		this.Image = imagePath;
	}

	public String getItemName() {
		return Name;
	}

	public void setItemName(String itemName) {
		this.Name = itemName;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		this.Price = price;
	}

	public String getItemID() {
		return ItemID;
	}

	public void setItemID(String itemID) {
		ItemID = itemID;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@Override
	public String toString() {
		return "Item [ItemID=" + ItemID + ", Image=" + this.getImagePath() + ", Name=" + Name + ", Price=" + Price
				+ ", Type=" + Type + "]";
	}

}