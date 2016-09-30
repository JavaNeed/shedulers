package com.scheduler.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
public class CustomerJPQLTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CustomerRepository customerRepository; 

	@Autowired
	private CustomerCustomRepository customerCustomRepository;

	private static final String ORG_HIBERNATE_CACHEABLE = "org.hibernate.cacheable"; 

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

	// Ref: http://www.objectdb.com/java/jpa/query/execute
	// Both Query and TypedQuery define a getResultList method, but the version of Query returns a result list of a raw type (non generic) 
	// instead of a parameterized (generic) type:
	@Test
	public void testTypeQueryfindByCustomerNumberByCity(){
		String sql = "SELECT c FROM Customer c INNER JOIN Order o ON c.customerNumber = o.customer.customerNumber "
				+ "WHERE c.city=:city";

		TypedQuery<Customer> typedQuery = em.createQuery(sql, Customer.class);
		typedQuery.setParameter("city", "Nantes");
		typedQuery.setHint(ORG_HIBERNATE_CACHEABLE, Boolean.TRUE);

		List<Customer> customers = typedQuery.getResultList();
		for (Customer customer : customers) {
			System.out.println("------------------------------");
			System.out.println("AddressLine 1  : "+customer.getAddressLine1());
			System.out.println("AddressLine 2  : "+customer.getAddressLine2());
			System.out.println("City           : "+customer.getCity());
			System.out.println("First Name     : "+customer.getContactFirstName());
			System.out.println("Last Name      : "+customer.getContactLastName());
		}
	}


	// SELECT clause (JPQL / Criteria API)
	@Test
	public void testGetAllCustomers(){
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
		List<Customer> results = query.getResultList();

		System.out.println("SIZE : "+results.size());
	}

	// Single Result Query Execution (with getSingleResult)
	// Notice that when a query returns a single object it might be tempting to prefer Query over TypedQuery 
	// even when the result type is known because the casting of a single object is easy and the code is simple:
	@Test
	public void testCountOfEmployees_JPQL(){
		TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Customer c", Long.class);
		long customerCount = query.getSingleResult();
		System.out.println("Count : "+customerCount);
	}
	
	
	
	// Ref: http://www.objectdb.com/java/jpa/query/jpql/select
	@Test
	public void testGetContactFirstName_JPQL() {
		TypedQuery<String> query = em.createQuery("SELECT c.contactFirstName FROM Customer AS c", String.class);
		List<String> results = query.getResultList();
		for (String firstName : results) {
			System.out.println("FIRST NAME : "+firstName);
		}
	}
	
	// Multiple SELECT Expressions
	@Test
	public void testMultiple_Select_JPQL(){
		String sql = "SELECT c.contactFirstName, c.contactLastName, c.postalCode FROM Customer c";
		TypedQuery<Object[]> query = em.createQuery(sql, Object[].class);
		
		List<Object[]> results = query.getResultList();
		for (Object[] result : results) {
			System.out.println("First Name : "+result[0]+", Last Name : "+result[1]+", PostalCode :"+result[1]);
		}
	}
}
