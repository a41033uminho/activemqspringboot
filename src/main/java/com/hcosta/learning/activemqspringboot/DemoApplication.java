package com.hcosta.learning.activemqspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 
 * @EnableJms triggers the discovery of methods annotated with @JmsListener, creating the message listener container under the covers.
 * 
 * @author hcosta
 *
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
