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

	@Test
	public void testfindByCustomerNumberByCityJPA(){
		List<Customer> customers = customerRepository.findByCustomerNumberByCity("Nantes");
		System.out.println("Size of Customers : "+customers.size());
		for (Customer customer : customers) {
			System.out.println("===================================================");
			System.out.println("AddressLine 1  : "+customer.getAddressLine1());
			System.out.println("AddressLine 2  : "+customer.getAddressLine2());
			System.out.println("City           : "+customer.getCity());
			System.out.println("First Name     : "+customer.getContactFirstName());
			System.out.println("Last Name      : "+customer.getContactLastName());
			System.out.println("Country        : "+customer.getCountry());
			System.out.println("Credit Limit   : "+customer.getCreditLimit());
			System.out.println("CustomerName   : "+customer.getCustomerName());
			System.out.println("Phone          : "+customer.getPhone());
			System.out.println("Postal Code    : "+customer.getPostalCode());
			System.out.println("State          : "+customer.getState());
			System.out.println("Employee No    : "+customer.getEmployee().getEmployeeNumber());
			System.out.println("Order No       : "+customer.getOrders().get(0).getOrderNumber());
		}
	}
	
	@Test
	public void testfindByCustomerNumberByCityNative(){
		List<Customer> customers = customerRepository.findByCustomerNumberByCityNative("Nantes");
		System.out.println("Size of Customers : "+customers.size());
		for (Customer customer : customers) {
			System.out.println("---------------------------------");
			System.out.println("AddressLine 1  : "+customer.getAddressLine1());
			System.out.println("AddressLine 2  : "+customer.getAddressLine2());
			System.out.println("City           : "+customer.getCity());
			System.out.println("First Name     : "+customer.getContactFirstName());
			System.out.println("Last Name      : "+customer.getContactLastName());
			System.out.println("Country        : "+customer.getCountry());
			System.out.println("Credit Limit   : "+customer.getCreditLimit());
			System.out.println("CustomerName   : "+customer.getCustomerName());
			System.out.println("Phone          : "+customer.getPhone());
			System.out.println("Postal Code    : "+customer.getPostalCode());
			System.out.println("State          : "+customer.getState());
			System.out.println("Employee No    : "+customer.getEmployee().getEmployeeNumber());
			System.out.println("Order No       : "+customer.getOrders().get(0).getOrderNumber());
		}
	}
}
