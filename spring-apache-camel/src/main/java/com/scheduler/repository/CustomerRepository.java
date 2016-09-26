package com.scheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scheduler.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	List<Customer> findByContactFirstName(String firstName);
	
	
	@Query("SELECT c FROM Customer c JOIN c.orders o WHERE c.customerNumber = o.customer.customerNumber AND c.city=:city")
	List<Customer> findByCustomerNumberByCity(@Param("city") String city);
	
	@Query(value = "SELECT c.* FROM customers c INNER JOIN orders o ON c.customerNumber=o.customerNumber WHERE c.city=:city", nativeQuery = true)
	List<Customer> findByCustomerNumberByCityNative(@Param("city") String city);

}
