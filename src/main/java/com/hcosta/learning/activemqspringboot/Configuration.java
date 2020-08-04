package com.hcosta.learning.activemqspringboot;

import javax.jms.ConnectionFactory;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@org.springframework.context.annotation.Configuration
@EnableJms
public class Configuration {

	@Bean
	/**
	 * For clarity, we have also defined a myFactory bean that is referenced in the JmsListener annotation of the receiver. 
	 * Because we use the DefaultJmsListenerContainerFactoryConfigurer infrastructure provided by Spring Boot, that JmsMessageListenerContainer will be identical to the one that boot creates by default.
	 * 
	 * @param connectionFactory
	 * @param configurer
	 * @return
	 */
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the message converter
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		return factory;
	}

	@Bean // Serialize message content to json using TextMessage
	/**
	 * The default MessageConverter is able to convert only basic types (such as String, Map, Serializable) and our Email is not Serializable on purpose. 
	 * We want to use Jackson and serialize the content to json in text format (i.e. as a TextMessage). 
	 * Spring Boot will detect the presence of a MessageConverter and will associate it to both the default JmsTemplate and any JmsListenerContainerFactory created by DefaultJmsListenerContainerFactoryConfigurer.
	 * 
	 * @return
	 */
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
