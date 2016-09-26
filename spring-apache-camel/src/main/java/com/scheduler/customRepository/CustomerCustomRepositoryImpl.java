package com.scheduler.customRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.scheduler.model.Customer;
import com.scheduler.model.Employee;

@Transactional
public class CustomerCustomRepositoryImpl implements CustomerCustomRepository{

	@Autowired
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findByCustomerNumberByCity(String city) {
		StringBuilder sql = new StringBuilder("SELECT c.* FROM customers c INNER JOIN orders o "
				+ "ON c.customerNumber=o.customerNumber "
				+ "where 1=1 ");
		
		if(city != null && !city.isEmpty())
			sql.append("AND c.city='").append(city).append("'");
		
		System.out.println("Final Query : "+sql.toString());
		
		Query query = em.createNativeQuery(sql.toString());
		
		
		List<Customer> customers = new ArrayList<>();
		
		List<Object> resultList = query.getResultList();
		
		// check if result-set is empty
		if (resultList != null && !resultList.isEmpty()) {
			
			for (Object object : resultList) {
				Object[] data = (Object[]) object;
				
				// set Customer
				Customer customer = new Customer();
				customer.setCustomerNumber(data[0] != null ? (int) data[0] : null);
				customer.setCustomerName(data[1] != null ? (String) data[1] : null);
				customer.setContactLastName(data[2] != null ? (String) data[2] : null);
				customer.setContactFirstName(data[3] != null ? (String) data[3] : null);
				customer.setPhone(data[4] != null ? (String) data[4] : null);
				customer.setAddressLine1(data[5] != null ? (String) data[5] : null);
				customer.setAddressLine2(data[6] != null ? (String) data[6] : null);
				customer.setCity(data[7] != null ? (String) data[7] : null);
				customer.setState(data[8] != null ? (String) data[8] : null);
				customer.setPostalCode(data[9] != null ? (String) data[9] : null);
				customer.setCountry(data[10] != null ? (String) data[10] : null);
				
				// set Employee
				Employee employee = new Employee();
				employee.setEmployeeNumber(data[11] != null ? (int) data[11] : null);
				customer.setEmployee(employee);
				
				customer.setCreditLimit(data[12] != null ? ((BigDecimal) data[12]).longValue() : 0);
				
				// Add each customer
				customers.add(customer);
			}
		}
		return customers;
	}

}
