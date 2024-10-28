package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.Bonusitem_DAL;
import DTO.Bonusitem;

public class Bonusitem_BLL {
	private static Bonusitem_BLL instance;

	private Bonusitem_BLL() {

	}

	public static Bonusitem_BLL getIstance() {
		if (instance == null) {
			instance = new Bonusitem_BLL();
		}
		return instance;
	}
	
	public List<Bonusitem> getAllBonusitem(){
		return Bonusitem_DAL.getIstance().getAllBonusitem();
	}
	
	public void updateBonusitem(Bonusitem bonusitem) {
		Bonusitem_DAL.getIstance().updateBonusitem(bonusitem);
	}
	
	public void addBonusitem(Bonusitem bonusitem) {
		Bonusitem_DAL.getIstance().addBonusitem(bonusitem);
	}
	
	public List<Bonusitem> getBonusitemAvailble(){
		List<Bonusitem> list = new ArrayList<Bonusitem>();
		for(Bonusitem bonusitem: list) {
			if(bonusitem.getStatus() == 1) {
				list.add(bonusitem);
			}
		}
		return list;
	}
}