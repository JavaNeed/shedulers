package com.javamakeuse.service;

import com.javamakeuse.entity.Product;


public interface ProductService {
	void create(Product product);
	Product findByProductCode(String productCode);
}
