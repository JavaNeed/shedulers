package com.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	
}
