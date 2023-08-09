package com.ordersms.orders.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	
	private static String OrderToProd = "order-to-prod";

	private static String OrderToProdKey = "order-routingkey";
	
	private static String Exchange = "some-exchange";
	
	private static String CartToOrder = "cart-to-order";

	private static String CartToOrderKey = "cart-order-routingkey";

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(Exchange);
	}
	
	@Bean
	Queue cartToOrderQueue() {
		return new Queue(CartToOrder, false);
	}
	
	@Bean
	Binding cartToOrderBinding() {
		return BindingBuilder.bind(cartToOrderQueue()).to(exchange()).with(CartToOrderKey);
	}
	
	@Bean
	Queue orderToProdQueue() {
		return new Queue(OrderToProd, false);
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
