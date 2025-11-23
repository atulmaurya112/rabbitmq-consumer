package com.learn.rabbitmq.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	public static final String EXCHANGE = "direct-exchange";
	public static final String STRING_QUEUE = "string-queue";
	public static final String DTO_QUEUE = "dto-queue";
	public static final String MAP_QUEUE = "map-queue";
	public static final String LIST_QUEUE = "list-queue";

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(EXCHANGE);
	}

	@Bean
	Queue stringQueue() {
		return new Queue(STRING_QUEUE, true);
	}

	@Bean
	Queue dtoQueue() {
		return new Queue(DTO_QUEUE, true);
	}

	@Bean
	Queue mapQueue() {
		return new Queue(MAP_QUEUE, true);
	}

	@Bean
	Queue listQueue() {
		return new Queue(LIST_QUEUE, true);
	}

	@Bean
	Binding stringBinding(Queue stringQueue, DirectExchange exchange) {
		return BindingBuilder.bind(stringQueue).to(exchange).with(STRING_QUEUE);
	}

	@Bean
	Binding dtoBinding(Queue dtoQueue, DirectExchange exchange) {
		return BindingBuilder.bind(dtoQueue).to(exchange).with(DTO_QUEUE);
	}

	@Bean
	Binding mapBinding(Queue mapQueue, DirectExchange exchange) {
		return BindingBuilder.bind(mapQueue).to(exchange).with(MAP_QUEUE);
	}

	@Bean
	Binding listBinding(Queue listQueue, DirectExchange exchange) {
		return BindingBuilder.bind(listQueue).to(exchange).with(LIST_QUEUE);
	}

	@Bean
	MessageConverter jsonMessageConverter() {
		return new JacksonJsonMessageConverter();
	}

	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(jsonMessageConverter);
		return template;
	}

	@Bean
	SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory, MessageConverter jsonMessageConverter) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(jsonMessageConverter);
		factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return factory;
	}

}