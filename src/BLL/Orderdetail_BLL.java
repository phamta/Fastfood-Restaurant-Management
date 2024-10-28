package BLL;

import java.util.List;
import java.util.Map;

import DAL.Orderdetail_DAL;
import DTO.Itemplus;

public class Orderdetail_BLL {
	private static Orderdetail_BLL instance;
	
	private Orderdetail_BLL() {
		
	}
	
	public static Orderdetail_BLL getInstance() {
		if(instance == null) {
			instance = new Orderdetail_BLL();
		}
		return instance;
	}
	
	public void AddOrderdetail(String orderid, List<Itemplus> list) {
		for(Itemplus itemplus: list) {
			Orderdetail_DAL.getInstance().AddOrderdetail(orderid, itemplus);
		}
	}
	
    public Map<String, Integer> getCustomerPurchaseStats(String customerid){
    	return Orderdetail_DAL.getInstance().getCustomerPurchaseStats(customerid);
    }
    
    public List<Itemplus> GetItemplusByOrderID(String orderid){
    	return Orderdetail_DAL.getInstance().GetItemplusByOrderID(orderid);
    }
}
