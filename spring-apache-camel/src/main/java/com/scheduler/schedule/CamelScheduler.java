package com.scheduler.schedule;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scheduler.model.Customer;
import com.scheduler.repository.CustomerRepository;

@Component
public class CamelScheduler extends RouteBuilder{

	@Autowired
	private CustomerRepository customerRepository; 
	
	@Override
	public void configure() throws Exception {
		from(IConstant.CUSTOMER_SCHEDULER).process(new Processor() {

			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("~~~~~~~~~ Scheduler for Customer Repository is Running ~~~~~~~~~~~~`");
				List<Customer> customers = customerRepository.findAll();
				System.out.println("Size Of Customers : "+customers.size());
			}
		});
	}
}
