package com.camel.scheduler;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelTimerSimpleExampleUsingSpring {
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		CamelContext camelContext = null;
		try {
			camelContext = SpringCamelContext.springCamelContext(appContext, false);
			camelContext.start();
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				camelContext.stop();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
