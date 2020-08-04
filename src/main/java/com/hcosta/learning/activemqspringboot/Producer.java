package com.hcosta.learning.activemqspringboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	
	/**
	 * JmsTemplate makes it very simple to send messages to a JMS destination. In the main runner method, after starting things up, you can just use jmsTemplate to send an Email POJO. 
	 * Because our custom MessageConverter has been automatically associated to it, a json document will be generated in a TextMessage only.
	 * 
	 */
	private @Autowired JmsTemplate jmsTemplate;
	
	private static String queue = "mailbox";
	
	public void sendEmail(String email, String body) {
	    jmsTemplate.convertAndSend(queue, new Email(email, body));
	}

}
