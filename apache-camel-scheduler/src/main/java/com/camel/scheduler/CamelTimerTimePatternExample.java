package com.camel.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


// Time and Pattern Options
public class CamelTimerTimePatternExample {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public static void main(String[] args) {
		CamelContext camelContext = new DefaultCamelContext();
		try {
			camelContext.addRoutes(new RouteBuilder() {
				
				@Override
				public void configure() throws Exception {
					Date future = new Date(new Date().getTime() + 1000);
					String time = sdf.format(future);
					fromF("timer://simpleTimer?time=%s&pattern=dd-MM-yyyy HH:mm:ss", time)
					.setBody(simple("Hello from timer at ${header.firedTime}"))
					.to("stream:out");
				}
			});
			camelContext.start();
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
