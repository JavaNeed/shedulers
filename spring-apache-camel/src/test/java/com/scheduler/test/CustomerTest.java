package com.scheduler.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.scheduler.customRepository.CustomerCustomRepository;
import com.scheduler.model.Customer;
import com.scheduler.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
@Transactional
@Rollback
public class CustomerTest {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CustomerRepository customerRepository; 
	
	@Autowired
	private CustomerCustomRepository customerCustomRepository;
	
	@Test
	public void testCamelQuartz(){
		
	}
	
	@Test
	public void testCustomer() {
		System.out.println(" Test Count The Customers ");
		
		List<Customer> customers = customerRepository.findAll();
		System.out.println("Size of Customers : "+customers.size());
	}
	
	@Test
	public void testCustomerByFirstName() {
		System.out.println(" Test Find By Customers Name ");
		
		List<Customer> customers = customerRepository.findByContactFirstName("Carine");
		System.out.println("Size of Customers : "+customers.size());
	}
	
	@Test
	public void testByCustomerNumber(){
		List<Customer> customers = customerCustomRepository.findByCustomerNumberByCity("Nantes");
		System.out.println("Size of Customers : "+customers.size());
	}

}
