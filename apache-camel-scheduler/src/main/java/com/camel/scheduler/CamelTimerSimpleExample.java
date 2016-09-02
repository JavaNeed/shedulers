package com.camel.scheduler;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


// Timer Example using Spring
public class CamelTimerSimpleExample {
	public static void main(String[] args) {
		CamelContext camelContext = new DefaultCamelContext();
		try {
			camelContext.addRoutes(new RouteBuilder() {
				
				@Override
				public void configure() throws Exception {
					from("timer://simpleTimer?period=1000")
					.setBody(simple("Hello from timer at ${header.firedTime}"))
					.to("stream:out");
				}
			});
			
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
