package com.javarticles.camel.components;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class OrderProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        System.out.println("Order received: " + exchange);
        Map<?, ?> order = exchange.getIn().getBody(Map.class);
        System.out.println("Processing order: " + order);
    }
}
