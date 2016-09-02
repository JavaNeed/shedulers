package com.camel.scheduler;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


// Delay and RepeatCount timer options
public class CamelTimerOtherOptionsExample {
	public static void main(String[] args) {
		CamelContext camelContext = new DefaultCamelContext();
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {					
					fromF("timer://simpleTimer?delay=2s&repeatCount=2")
					.setBody(simple("Hello from timer at ${header.firedTime}"))
					.to("stream:out");
				}
			});
			camelContext.start();
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				camelContext.stop();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
