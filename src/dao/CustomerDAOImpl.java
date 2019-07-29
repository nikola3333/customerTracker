package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session curentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		Query<Customer> theQuery = curentSession.createQuery("from Customer order by lastName", Customer.class);
		
		//get the list of customers from the query
		List<Customer> customers = theQuery.getResultList();
		
		//return the list of customers
		return customers;

		
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save/update the customer ... finally 
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomers(int itheId) {
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//retrieve from db using PK
		Customer theCustomer = currentSession.get(Customer.class, itheId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		//get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query ... sort by last name
		Query<Customer> theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		
		//customerId iz linije iznad moramo nekako dodeliti prvo u theQuery, a to radimo ovako
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
	}

	
	@Override
	public void addFriend(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		theCustomer.addNewFriend(theId);

		for (int temp : theCustomer.getFriends()) {
			
			currentSession.save(theCustomer);
			
		}
		
		
	}

	@Override
	public Customer getCustomerByEmail(String sentEmail) {

		//get the current hibernate session
		Session curentSession = sessionFactory.getCurrentSession();
				
		//create a query ... sort by last name
		Query<Customer> theQuery = curentSession.createQuery("from Customer where email = \'" + sentEmail + "\' order by lastName", Customer.class);
				
		//get the list of customers from the query
		Customer customer = theQuery.getSingleResult();
				
		//return the list of customers
		return customer;		
		
		
	}
	

}
