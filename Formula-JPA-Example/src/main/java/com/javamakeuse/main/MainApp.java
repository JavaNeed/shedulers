package com.javamakeuse.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.javamakeuse.entity.Product;
import com.javamakeuse.service.ProductService;

public class MainApp {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static SimpleDateFormat SDF = new SimpleDateFormat(DATE_FORMAT);
	
	public static void main(String[] args) throws ParseException {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		ProductService productService = (ProductService) context.getBean("productService");
		
		// create Product
		Product product = new Product();
		product.setId(1);
		product.setManufacturedDate(SDF.parse("2016-08-08 01:01:01"));
		product.setPrice(200);
		product.setProductCode("AZ0100");
		product.setProductName("Lenovo Vibe X3");
		product.setVat(5.64F);
		
		productService.create(product);
	}
}
