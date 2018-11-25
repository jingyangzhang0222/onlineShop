package onlineShop.dao;

import org.springframework.stereotype.Repository;

import onlineShop.model.Customer;

public interface CustomerDao {
	// data access object
	void addCustomer(Customer customer);

	Customer getCustomerByName(String userName);
}
