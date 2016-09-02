package com.camel.scheduler;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelJdbcPollingExample {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("dataSourceApplicationContext.xml");
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		CamelContext camelContext = null;

		try {
			JndiContext jndiContext = new JndiContext();
			jndiContext.bind("dataSource", dataSource);

			camelContext = new DefaultCamelContext(jndiContext);
			camelContext.addRoutes(new RouteBuilder() {

				@Override
				public void configure() throws Exception {
					System.out.println("---------------------------------------------");
					from("timer://pollTable?period=1s")
					.setBody(constant("select * from customer where status = 'NEW'"))
					.to("jdbc:dataSource")
					.split(simple("${body}"))
					.process(new Processor() {
						@Override
						public void process(Exchange exchange) throws Exception {
							Map customer = exchange.getIn().getBody(Map.class);
							System.out.println("Process customer " + customer);
						}
					});
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
			context.close();
		}
	}
}
