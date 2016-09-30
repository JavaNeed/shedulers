package com.scheduler.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.internal.QueryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.scheduler.model.Office;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
@Transactional
@Rollback
public class OfficeNamedParametersTest {
	@PersistenceContext
	private EntityManager em;

	// @Ref: http://www.objectdb.com/java/jpa/query/parameter
	//Named Parameters (:name)
	@Test
	public void testNameParameters() {
		String sql = "SELECT O FROM Office O WHERE O.state = :state AND O.postalCode =:postalCode";
		TypedQuery<Office> query = em.createQuery(sql, Office.class);
		query.setParameter("state", "CA");
		query.setParameter("postalCode", "94080");

		List<Office> offices = query.getResultList();
		for (Office office : offices) {
			System.out.println("State  : "+office.getState());
			System.out.println("PostalCode  : "+office.getPostalCode());
		}
	}

	// Ordinal Parameters (?index)
	@Test
	public void testOrdinalIndex(){
		String sql = "SELECT O FROM Office O WHERE O.state =? AND O.postalCode =?";
		TypedQuery<Office> query = em.createQuery(sql, Office.class);
		query.setParameter(1, "CA");
		query.setParameter(2, "94080");

		System.out.println("QUERY :"+query.toString());
		
		List<Office> offices = query.getResultList();
		for (Office office : offices) {
			System.out.println("----------------------------------");
			System.out.println("State  : "+office.getState());
			System.out.println("PostalCode  : "+office.getPostalCode());
		}
	}

	// Parameters vs. Literals
	@Test
	public void testParameters_vs_Literals(){
		String state = "CA";
		String postalCode = "94080";
		
		String sql = "SELECT O FROM Office O WHERE O.state='"+ state +"' AND O.postalCode ='" + postalCode +"'"; 
		TypedQuery<Office> query = em.createQuery(sql, Office.class);
		
		List<Office> offices = query.getResultList();
		for (Office office : offices) {
			System.out.println("----------------------------------");
			System.out.println("State  : "+office.getState());
			System.out.println("PostalCode  : "+office.getPostalCode());
		}
	}
}
