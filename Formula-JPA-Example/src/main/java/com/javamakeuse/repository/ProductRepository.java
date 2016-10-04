package com.javamakeuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javamakeuse.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	Product findByProductCode(String productCode);
}
