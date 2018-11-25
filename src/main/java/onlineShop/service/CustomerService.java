package onlineShop.service;

import onlineShop.model.Customer;

public interface CustomerService {
	void addCustomer(Customer customer);

	Customer getCustomerByName(String userName);
}
