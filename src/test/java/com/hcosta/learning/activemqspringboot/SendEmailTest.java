package com.hcosta.learning.activemqspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendEmailTest {
	
	private @Autowired Producer producer;

	@Test
	void test() {
		producer.sendEmail("info@example.com","Hello");
	}

}
