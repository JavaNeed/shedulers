package com.scheduler.customRepository;

import java.util.List;

import com.scheduler.model.Customer;

public interface CustomerCustomRepository {

	List<Customer> findByCustomerNumberByCity(String city);
}
