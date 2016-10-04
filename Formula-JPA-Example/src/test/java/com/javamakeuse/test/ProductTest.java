package com.javamakeuse.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javamakeuse.entity.Product;
import com.javamakeuse.repository.ProductRepository;
import com.javamakeuse.service.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class ProductTest {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat SDF = new SimpleDateFormat(DATE_FORMAT);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void testProductFinal() throws ParseException {
		Product product = new Product();
		product.setId(1);
		product.setManufacturedDate(SDF.parse("2016-08-08 01:01:01"));
		product.setPrice(200);
		product.setProductCode("AZ0100");
		product.setProductName("Lenovo Vibe X3");
		product.setVat(5.64F);
		
		productService.create(product);
	}
	
	
	@Test
	public void testProductFinalAndGet() throws ParseException {
		Product product = new Product();
		product.setId(1);
		product.setManufacturedDate(SDF.parse("2016-08-08 10:10:15"));
		product.setPrice(400);
		product.setProductCode("AZ0111");
		product.setProductName("Nokia Lumia");
		product.setVat(5.64F);
		
		Product product1 = productRepository.save(product);
		
		
		System.out.println("----------------------------------------");
		System.out.println("ManufacturedDate  : "+product1.getManufacturedDate());
		System.out.println("Price             : "+product1.getPrice());
		System.out.println("ProductCode       : "+product1.getProductCode());
		System.out.println("Product Name      : "+product1.getProductName());
		System.out.println("Vat               : "+product1.getVat());
		
	}
	
	
	@Test
	public void testFindByProductCode(){
		Product product = productService.findByProductCode("AZ00010");
		System.out.println("----------------------------------------");
		System.out.println("ManufacturedDate  : "+product.getManufacturedDate());
		System.out.println("Price             : "+product.getPrice());
		System.out.println("ProductCode       : "+product.getProductCode());
		System.out.println("Product Name      : "+product.getProductName());
		System.out.println("Vat               : "+product.getVat());
	}
}
