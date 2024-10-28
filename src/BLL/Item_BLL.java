package BLL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import DAL.Item_DAL;
import DTO.Combo;
import DTO.Item;
import DTO.Itemplus;

public class Item_BLL {
	private static Item_BLL instance;
	
	private Item_BLL() {
		
	}
	
	public static Item_BLL getInstance() {
		if(instance == null) {
			instance = new Item_BLL();
		}
		return instance;
	}
	
	public List<Item> getAllItem(){
		return Item_DAL.getInstance().GetAllItem();
	}
	
	public Item getItemByID(String itemid) {
		Item item = null;
		for(Item item2: getAllItem()) {
			if(item2.getItemID().compareTo(itemid) == 0) {
				item = item2;
			}
		}
		return item;
	}
	
	public List<Item> getAlCombotoItem(){
		List<Item> list = new ArrayList<Item>();
		for(Combo combo: Combo_BLL.getInstance().getAllCombo()) {
			Item item = new Item(combo.getComboID(), combo.getImage(), combo.getComboName(), combo.getPrice(), "Combo");
			list.add(item);
		}
		return list;
	}
	
	public List<Item> getAllByType(String type){
		if(type.isEmpty()) {
			return getAllItem();
		}
		List<Item> list = new ArrayList<Item>();
		for(Item item: getAllItem()) {
			if(item.getType().compareTo(type) == 0) {
				list.add(item);
			}
		}
		return list;
	}
	
	public List<Item> getItemByTypeandName(String type, String name){
		List<Item> list = new ArrayList<Item>();
		for(Item item: getAllByType(type)) {
			if(item.getItemName().contains(name)) {
				list.add(item);
			}
		}
		return list;
	}
	
	public List<Itemplus> getItemsNotInList(List<Itemplus> list){
		if(list == null) {
			return list;
		}
		Set<String> itemidexist = new HashSet<>();
		for(Itemplus itemplus: list) {
			itemidexist.add(itemplus.getItemID());
		}
		
		List<Itemplus> result = new ArrayList<Itemplus>();
		for(Item item: getAllItem()) {
			if(!itemidexist.contains(item.getItemID())) {
				Itemplus itemplus = new Itemplus(item,1);
				result.add(itemplus);
			}
		}
		
		return result;
	}
	
//	public Set<String> getAllTypeOfItem(){
//		Set<String> set = new HashSet<>();
//		for(Item item: getAllItem()) {
//			set.add(item.getType());
//		}
//		return set;
//	}
	
	public void insertItem(Item item) {
		Item_DAL.getInstance().insertItem(item);
	}
	
	public void updateItem(Item item) {
		Item_DAL.getInstance().updateItem(item);
	}
	
	public void deleteItem(String itemid) {
		Item_DAL.getInstance().deleteItem(itemid);
	}
}
