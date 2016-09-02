package com.camel.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelTimerExchangePropertiesExample {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public static void main(String[] args) throws Exception {
		CamelContext camelContext = new DefaultCamelContext();
		try {
			camelContext.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					Date future = new Date(new Date().getTime() + 1000);

					String time = sdf.format(future);

					fromF("timer://simpleTimer?time=%s&pattern=dd-MM-yyyy HH:mm:ss&period=1000", time)
					.process(new Processor() {
						public void process(Exchange msg) {
							Date firedTime = msg.getProperty(Exchange.TIMER_FIRED_TIME,Date.class);
							int eventCount = msg.getProperty(Exchange.TIMER_COUNTER, Integer.class);
							String timerName = msg.getProperty(Exchange.TIMER_NAME, String.class);							
							int period = msg.getProperty(Exchange.TIMER_PERIOD, Integer.class);
							Date time = msg.getProperty(Exchange.TIMER_TIME, Date.class);

							msg.getOut().setBody("Exchange Properties: name: " + timerName + " time: " + time + " period: " + period + 
									"\n fired At: " + firedTime + " counter: " + eventCount);
						}
					}).to("stream:out");
				}
			});
			camelContext.start();
			Thread.sleep(3000);
		} finally {
			camelContext.stop();
		}
	}
}
