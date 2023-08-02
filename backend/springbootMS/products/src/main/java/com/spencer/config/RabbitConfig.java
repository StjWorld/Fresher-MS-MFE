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
	
	private static String OrderToProd = "order-to-prod";

	private static String OrderToProdKey = "order-routingkey";

	private static String ProdToCart = "prod-to-cart";

	private static String ProdToCartKey = "prod-routingkey";

	private static String CartToProd = "cart-to-prod";

	private static String CartToProdKey = "cart-routingkey";
	
	private static String Exchange = "some-exchange";
	
	
	
	
	@Bean
	Queue prodToCartQueue() {
		return new Queue(ProdToCart, false);
	}
	
	@Bean
	Queue cartToProdQueue() {
		return new Queue(CartToProd, false);
	}
	
	@Bean
	Queue orderToProdQueue() {
		return new Queue(OrderToProd, false);
	}
	
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(Exchange);
	}
	
	@Bean
	Binding prodToCartBinding() {
		return BindingBuilder.bind(prodToCartQueue()).to(exchange()).with(ProdToCartKey);
	}
	
	@Bean
	Binding cartToProdBinding() {
		return BindingBuilder.bind(cartToProdQueue()).to(exchange()).with(CartToProdKey);
	}
	
	@Bean
	Binding orderToProdBinding() {
		return BindingBuilder.bind(orderToProdQueue()).to(exchange()).with(OrderToProdKey);
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
