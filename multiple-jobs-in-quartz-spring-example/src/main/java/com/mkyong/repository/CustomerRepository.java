package com.mkyong.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkyong.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
