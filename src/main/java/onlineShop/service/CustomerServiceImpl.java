package onlineShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineShop.dao.CustomerDao;
import onlineShop.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao dao;
	
	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		dao.addCustomer(customer);		
	}

	@Override
	public Customer getCustomerByName(String userName) {
		// TODO Auto-generated method stub
		return dao.getCustomerByName(userName);
	}

}
