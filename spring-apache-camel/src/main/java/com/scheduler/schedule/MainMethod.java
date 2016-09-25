package com.scheduler.schedule;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainMethod {
	public static void main(String[] args) {
		String[] contextApp = {"application-context.xml"};
		ApplicationContext context = new ClassPathXmlApplicationContext(contextApp);
		
		CamelScheduler scheduler = (CamelScheduler) context.getBean("camelScheduler");
	}
}
