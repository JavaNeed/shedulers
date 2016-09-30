package com.scheduler.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.scheduler.model.Payment;
import com.scheduler.repository.PaymentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
@Transactional
@Rollback
public class PaymentTest {
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static SimpleDateFormat SDF = new SimpleDateFormat(DATE_PATTERN);
	
	@Autowired
	private PaymentRepository paymentRepository; 
	
	@Test
	public void testfindByPaymentDateBetween() throws ParseException {
		Date startDate = SDF.parse("2003-12-10");
		Date endDate = SDF.parse("2004-12-10");
		List<Payment> payments = paymentRepository.findByPaymentDateBetween(startDate, endDate);
		System.out.println("Size : "+payments.size());
	}

	
	@Test
	public void testfindByAmountGreaterThanEqual(){
		List<Payment> payments = paymentRepository.findByAmountGreaterThanEqual(new BigDecimal("2000"));
		System.out.println("SIZE : "+payments.size());
		for (Payment payment : payments) {
			System.out.println("----------------------------------");
			System.out.println("Amount  : "+ payment.getAmount());
			System.out.println("Payment Date : "+ payment.getPaymentDate());
			System.out.println("ID : "+ payment.getId());
			System.out.println("CustomerNumber : "+ payment.getCustomer().getCustomerNumber());
		}
	}
	
	
	@Test
	public void testfindByAmountLessThanEqualAndAmountGreaterThanEqual(){
		List<Payment> payments = paymentRepository.findByAmountLessThanEqualAndAmountGreaterThanEqual(new BigDecimal("2000"), new BigDecimal("1000"));
		System.out.println("SIZE : "+payments.size());
		for (Payment payment : payments) {
			System.out.println("----------------------------------");
			System.out.println("Amount  : "+ payment.getAmount());
			System.out.println("Payment Date : "+ payment.getPaymentDate());
			System.out.println("CheckNumber : "+ payment.getId().getCheckNumber());
			System.out.println("CustomerNumber : "+ payment.getCustomer().getCustomerNumber());
		}
	}
	
	@Test
	public void testfindDistinctByCustomer_CustomerNumber(){
		List<Payment> payments = paymentRepository.findDistinctByCustomer_CustomerNumber();
		System.out.println("Payments SIZE  : " +payments.size());
	}
}
