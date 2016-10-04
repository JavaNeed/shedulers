package com.javamakeuse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.javamakeuse.entity.Product;
import com.javamakeuse.repository.ProductRepository;

@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void create(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product findByProductCode(String productCode) {
		return productRepository.findByProductCode(productCode);
	}
}
