package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CustomerDAO;
import entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject customer dao
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {

		return customerDAO.getCustomers();
	}


	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		
		customerDAO.saveCustomer(theCustomer);
		
	}


	@Override
	@Transactional
	public Customer getCustomers(int itheId) {
		
		return customerDAO.getCustomers(itheId);
	}


	@Override
	@Transactional
	public void deleteCustomer(int theId) {

		customerDAO.deleteCustomer(theId);
		
	}


	@Override
	@Transactional
	public void addFriend(int theId) {

		customerDAO.addFriend(theId);
		
	}


	@Override
	@Transactional
	public Customer getCustomerByEmail(String sentEmail) {

		return customerDAO.getCustomerByEmail(sentEmail);
		
	}



}
