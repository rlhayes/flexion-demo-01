package com.flexion.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 */
public class SpringIntegrationDemo {

	/**
	 * Starting the context fires up the pollers and starts the flow.
	 */
    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/integration/spring-integration-context.xml");    
        
        System.out.printf("Running, context=%s\n", context);
    }
}
