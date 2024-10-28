package BLL;

import java.util.HashMap;
//import java.util.ArrayList;
import java.util.List;
//import java.util.TreeMap;
import java.util.Map;

import DAL.Customer_DAL;
import DAL.Order_DAL;
import DTO.Customer;
import DTO.Order;
import TRIE.Trie;

public class Customer_BLL {
	private static Customer_BLL instance;
	private Trie<Customer> trie;

	private Customer_BLL() {
		trie = new Trie<Customer>();
		loadCustomersToTrie();
	}

	public static Customer_BLL getInstance() {
		if (instance == null) {
			instance = new Customer_BLL();
		}
		return instance;
	}
	
    private void loadCustomersToTrie() {
        List<Customer> list = Customer_DAL.getInstance().GetAllCustomer();
        for (Customer customer : list) {
            trie.insert(customer.getPhone_number(), customer);
        }
    }

	public List<Customer> SearchCustomerIntrie(String phone_number) {
		return trie.searchByStartsWith(phone_number);
	}

	public void updatePoint(Customer customer) {
		Customer_DAL.getInstance().updatePoint(customer);
	}

	public List<Customer> getAllCustomer() {
		return Customer_DAL.getInstance().GetAllCustomer();
	}

	public void addCustomer(Customer customer) {
		Customer_DAL.getInstance().addCustomer(customer);
	}

	public Customer getCustomerById(String customerid) {
		return Customer_DAL.getInstance().getCustomerById(customerid);
	}

	public Map<Integer, Integer> getTotalSpentByMonth(String customerID, int year) {
		List<Order> orders = Order_DAL.getInstance().getOrdersByCustomerID(customerID);
		Map<Integer, Integer> monthlyTotalSpent = new HashMap<>();

		for (Order order : orders) {
			String[] datetimeParts = order.getDatetime().split("-");
			int orderYear = Integer.parseInt(datetimeParts[0]);
			int orderMonth = Integer.parseInt(datetimeParts[1]);

			if (orderYear == year) {
				monthlyTotalSpent.put(orderMonth, monthlyTotalSpent.getOrDefault(orderMonth, 0) + order.getTotal());
			}
		}

		return monthlyTotalSpent;
	}

	public Map<Integer, Integer> getPurchaseCountByMonth(String customerID, int year) {
		List<Order> orders = Order_DAL.getInstance().getOrdersByCustomerID(customerID);
		Map<Integer, Integer> monthlyPurchaseCount = new HashMap<>();

		for (Order order : orders) {
			String[] datetimeParts = order.getDatetime().split("-");
			int orderYear = Integer.parseInt(datetimeParts[0]);
			int orderMonth = Integer.parseInt(datetimeParts[1]);

			if (orderYear == year) {
				monthlyPurchaseCount.put(orderMonth, monthlyPurchaseCount.getOrDefault(orderMonth, 0) + 1);
			}
		}

		return monthlyPurchaseCount;
	}

}
