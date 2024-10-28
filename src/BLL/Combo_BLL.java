package BLL;

import java.util.List;

import DAL.Combo_DAL;
import DTO.Combo;
import DTO.Itemplus;

public class Combo_BLL {
	private static Combo_BLL instance;
	
	private Combo_BLL() {
		
	}
	
	public static Combo_BLL getInstance() {
		if(instance == null) {
			instance = new Combo_BLL();
		}
		return instance;
	}
	
	public String generateNextComboID() {
		return Combo_DAL.getInstance().generateNextComboID();
	}
	
	public void addCombo(Combo combo) {
	    Combo_DAL.getInstance().addCombo(combo);
	}
	
	public List<Combo> getAllCombo(){
		return Combo_DAL.getInstance().getAllCombo();
	}
	
	public Combo getComboByID(String comboid) {
		Combo combo = null;
		for(Combo temp: getAllCombo()) {
			if(temp.getComboID().compareTo(comboid) == 0) {
				combo = temp;
				break;
			}
		}
		return combo;
	}
	
	public List<Itemplus> getItemOfCombo(String comboid){
		return Combo_DAL.getInstance().getItemOfCombo(comboid);
	}
	
	public void updateCombo(Combo combo) {
		Combo_DAL.getInstance().updateCombo(combo);
	}
	
	public void deleteCombo(String comboId) {
		Combo_DAL.getInstance().deleteCombo(comboId);
	}
	
	public void addItemToCombo(String comboid, List<Itemplus> list) {
		for(Itemplus itemplus: list) {
			Combo_DAL.getInstance().addItemToCombo(comboid, itemplus);
		}
	}
	
	public void deleteComboDetail(List<Itemplus> list, String comboId) {
		for(Itemplus itemplus: list) {
			Combo_DAL.getInstance().deleteComboDetail(itemplus.getItemID(), comboId);
		}
	}
}
