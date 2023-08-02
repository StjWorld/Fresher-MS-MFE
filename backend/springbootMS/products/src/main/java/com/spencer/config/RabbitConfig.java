package com.spencer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

	@Bean
	Queue prodToCartQueue() {
		return new Queue("${skorb.rabbitmq.queue.prod}", false);
	}
	
	@Bean
	Queue cartToProdQueue() {
		return new Queue("${skorb.rabbitmq.queue.cart}", false);
	}
	
	@Bean
	Queue orderToProdQueue() {
		return new Queue("${skorb.rabbitmq.queue.order}", false);
	}
	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange("${skorb.rabbitmq.exchange}");
	}
	
	@Bean
	Binding prodToCartBinding() {
		return BindingBuilder.bind(prodToCartQueue()).to(exchange()).with("${skorb.rabbitmq.routingkey.prod}");
	}
	
	@Bean
	Binding cartToProdBinding() {
		return BindingBuilder.bind(cartToProdQueue()).to(exchange()).with("${skorb.rabbitmq.routingkey.cart}");
	}
	
	@Bean
	Binding orderToProdBinding() {
		return BindingBuilder.bind(orderToProdQueue()).to(exchange()).with("${skorb.rabbitmq.routingkey.order}");
	}
	
	@Bean
	public MessageConverter jsonMessageConvertor() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	AmqpTemplate rabbitTemplate(ConnectionFactory cf) {
		RabbitTemplate rt = new RabbitTemplate(cf);
		rt.setMessageConverter(jsonMessageConvertor());
		return rt;
	}
}
