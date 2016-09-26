package com.scheduler.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scheduler.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{
	List<Payment> findByPaymentDateBetween(Date startDate, Date endDate);
	
	List<Payment> findByAmountGreaterThanEqual(BigDecimal amount);
	
	List<Payment> findByAmountLessThanEqualAndAmountGreaterThanEqual(BigDecimal lessAmount, BigDecimal greaterAmount);
	
	@Query( value = "SELECT DISTINCT p FROM Payment p")
	List<Payment> findDistinctByCustomer_CustomerNumber();
}
