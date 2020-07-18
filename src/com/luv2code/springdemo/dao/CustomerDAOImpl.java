package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session curresntSession = sessionFactory.getCurrentSession();

		// create a query .. sort by last name
		Query<Customer> theQuery = curresntSession.createQuery("from Customer order by lastName", Customer.class);

		// execute query and get result list
		List<Customer> customer = theQuery.getResultList();

		// return the results
		return customer;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get the current hibernate session
		Session curresntSession = sessionFactory.getCurrentSession();

		// save the customer
		curresntSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session curresntSession = sessionFactory.getCurrentSession();

		// now get data from database using ID

		Customer theCustomer = curresntSession.get(Customer.class, theId);
		
		System.out.println("After getting the one customer"+ theCustomer);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
				// get the current hibernate session
				Session curresntSession = sessionFactory.getCurrentSession();

				// delete object with peimary key
				Query theQuery = curresntSession.createQuery("delete from Customer where id = :customerId");
				
				theQuery.setParameter("customerId", theId);
				
				theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomer(String theSearchName) {
		// get the current hibernate session
		Session curresntSession = sessionFactory.getCurrentSession();
		
		Query theQuery = null;
		
		// 
		// only search by name if thSearchNamer is not empty
		//
		if(theSearchName != null && theSearchName.trim().length() > 0) {
			// search for first Name or lastName .. case Insensitive
			theQuery = curresntSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like:theName", Customer.class);
			
			theQuery.setParameter("theName","%"+theSearchName.toLowerCase() +"%");
			
		}else {
			  // theSearchName is empty ... so just get all customers
            theQuery =curresntSession.createQuery("from Customer", Customer.class);   
		}
		
		// execute query and get ewsult list
		List<Customer> customers = theQuery.getResultList();
		
		
		return customers;
	}

}
