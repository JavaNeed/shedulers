package com.scheduler.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.scheduler.repository.ProductRepository;

public class ProductTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
