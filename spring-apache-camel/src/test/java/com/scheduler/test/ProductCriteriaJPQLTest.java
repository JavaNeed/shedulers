package com.scheduler.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.scheduler.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@Transactional
@Rollback
public class ProductCriteriaJPQLTest {

	@PersistenceContext
	private EntityManager em;

	private CriteriaBuilder cb;
	CriteriaQuery<Object> criteriaQuery;

	@Before
	public void before() {
		cb = em.getCriteriaBuilder();
		criteriaQuery = cb.createQuery();
	}

	// SELECT in Criteria Queries
	@Test
	public void testProductInCriteraiBuilder() {
		Root<Product> from = criteriaQuery.from(Product.class);

		// select all records
		System.out.println("--------- Select all records ------------");
		CriteriaQuery<Object> select = criteriaQuery.select(from);
		TypedQuery<Object> typedQuery = em.createQuery(select);
		List<Object> resultlist = typedQuery.getResultList();

		for (Object o : resultlist) {
			Product product = (Product) o;
			System.out.println("ProductCode : " + product.getProductCode());
			System.out.println("ProductName : " + product.getProductName());
		}
	}


	// ordering the records
	@Test
	public void testOrderingInCriteriaBuilder(){
		System.out.println("----------------- Select all records by follow ordering-------------------");
		Root<Product> from = criteriaQuery.from(Product.class);

		CriteriaQuery<Object> select = criteriaQuery.select(from);
		select.orderBy(cb.asc(from.get("productCode")));
		select.distinct(true);

		TypedQuery<Object> typedQuery = em.createQuery(select);
		List<Object> resultlist = typedQuery.getResultList();
		for (Object o : resultlist) {
			Product product = (Product) o;
			System.out.println("ProductCode : " + product.getProductCode());
			System.out.println("ProductName : " + product.getProductName());
		}
	}

	// Multi Selection
	@Test
	public void testMultiSelection(){
		CriteriaQuery<Object[]> select = cb.createQuery(Object[].class);
		Root<Product> from = select.from(Product.class);
		select.select(cb.array(from.get("productCode"), from.get("productVendor"),from.get("productline").get("productLine")));

		List<Object[]> results = em.createQuery(select).getResultList();
		for (Object[] result : results) {
			System.out.println("ProductCode: " + result[0] + ", ProductName: " + result[1]+", ProductVendor : "+result[2]);
		}
	}

	// CriteriaBuilder's tuple
	@Test
	public void testTupleInterface(){
		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		Root<Product> c = q.from(Product.class);
		q.select(cb.tuple(c.get("productCode"), c.get("productVendor"), c.get("productline").get("productLine")));

		List<Tuple> results = em.createQuery(q).getResultList();
		for (Tuple t : results) {
			System.out.println("ProductCode: " + t.get(0) + ", ProductName: " + t.get(1)+", ProductVendor : "+t.get(2));
		}
	}
	
	// @Ref: http://www.thejavageek.com/2014/04/27/criteria-api-selecting-multiple-expressions/
	// CriteriaQuery's multiselect
	@Test
	public void testCriteriaQuerysMultiselect(){
		CriteriaQuery<Tuple> query = cb.createTupleQuery();
		
		Root<Product> product = query.from(Product.class);
		query.multiselect(cb.tuple(product.get("productCode"), product.get("productVendor")));

		List<Tuple> results = em.createQuery(query).getResultList();
		for (Tuple t : results) {
			System.out.println("ProductCode: " + t.get(0) + ", ProductName: " + t.get(1)+", ProductVendor : "+t.get(2));
		}
	}
}
