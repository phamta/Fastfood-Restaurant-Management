package DTO;

import java.util.ArrayList;
import java.util.List;

public class Combo {
	private String ComboID;
	private String ComboName;
	private int Price;
	private String Image;
//	private List<Itemplus> listitem;

	public Combo(String comboID, String comboName, int price) {
		ComboID = comboID;
		ComboName = comboName;
		Price = price;
	}

	public String getComboID() {
		return ComboID;
	}

	public void setComboID(String comboID) {
		ComboID = comboID;
	}

	public String getComboName() {
		return ComboName;
	}

	public void setComboName(String comboName) {
		ComboName = comboName;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

//	public List<Itemplus> getListitem() {
//		return listitem;
//	}
//
//	public void setListitem(List<Itemplus> listitem) {
//		this.listitem = listitem;
//	}

	public Combo() {
		super();
	}

}
