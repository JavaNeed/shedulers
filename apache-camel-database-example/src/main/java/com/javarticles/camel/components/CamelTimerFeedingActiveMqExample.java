package com.javarticles.camel.components;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelTimerFeedingActiveMqExample {
	public static final void main(String[] args) throws Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("timerFeedActiveMqApplicationContext.xml");
		//CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);
		CamelContext camelContext = new SpringCamelContext(appContext);
		
		try {
			camelContext.start();
			Thread.sleep(5000);
		} finally {
			camelContext.stop();
		}
	}
}
