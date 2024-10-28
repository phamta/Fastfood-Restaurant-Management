package BLL;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import DAL.Order_DAL;
import DTO.Order;
import TRIE.Trie;

public class Order_BLL {
	private static Order_BLL instance;
	private Trie<Order> trie;
	
	private Order_BLL() {
		trie = new Trie<Order>();
		loadOrderToTrie();
	}
	
	public static Order_BLL getInstance() {
		if(instance == null) {
			instance = new Order_BLL();
		}
		return instance;
	}
	
    private void loadOrderToTrie() {
        List<Order> list = Order_DAL.getInstance().GetAllOrder();
        for (Order order : list) {
            trie.insert(order.getOrderID(), order);
        }
    }
    
    public List<Order> getOrderByID(String orderid){
    	return trie.searchByStartsWith(orderid);
    }
	
	public List<Order> getAllOrder(){
		return Order_DAL.getInstance().GetAllOrder();
	}
	
	public void AddOrder(Order order) {
		Order_DAL.getInstance().AddOrder(order);
	}
	
	public List<Order> getOrdersByCustomerId(String customerId){
		return Order_DAL.getInstance().getOrdersByCustomerID(customerId);
	}
	
	public List<Order> getOrderByDateRange(String date_start, String date_end){
		List<Order> list = new ArrayList<Order>();
		
		for(String orderid: Order_DAL.getInstance().getOrderIDsByDateRange(date_start, date_end)) {
			Order order = getOrderByID(orderid).getFirst();
			list.add(order);
		}
		return list;
	}
	
	public List<Integer> getTotalSalesPerMonth(){
		return Order_DAL.getInstance().getTotalSalesPerMonth();
	}
	
	public Map<String, Integer> getItemCounts() {
		Map<String, Integer> itemCounts = Order_DAL.getInstance().getItemCounts();
		
		 Map<String, Integer> top10Items = itemCounts.entrySet().stream()
	                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	                .limit(10)
	                .collect(Collectors.toMap(
	                        Map.Entry::getKey,
	                        Map.Entry::getValue,
	                        (e1, e2) -> e1,
	                        LinkedHashMap::new));
		 return top10Items;
	}
}
