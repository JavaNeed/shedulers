package com.mkyong.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.mkyong.model.Customer;
import com.mkyong.repository.CustomerRepository;


@Service
public class JobA extends QuartzJobBean {

	@Autowired
	private CustomerRepository customerRepository = null;
	
	@Override
	protected void executeInternal(JobExecutionContext executionContext) throws JobExecutionException {
		System.out.println("~~~~~~~ Job A is runing ~~~~~~~~");
		Trigger trigger = executionContext.getTrigger();
		System.out.println(trigger.getPreviousFireTime());
		System.out.println(trigger.getNextFireTime());
		getCustomerList();
	}
	
	private List<Customer> getCustomerList(){
		List<Customer> customers = customerRepository.findAll();
		for (Customer customer : customers) {
			System.out.println("------------------------------");
			System.out.println("ID : "+customer.getId());
			System.out.println("NAME : "+customer.getName());
			System.out.println("STATUS : "+customer.getStatus());
		}
		return customers;
	}
}
