package dao;

import java.util.List;

import entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomers(int itheId);

	public void deleteCustomer(int theId);

	public void addFriend(int theId);

	public Customer getCustomerByEmail(String sentEmail);


}
