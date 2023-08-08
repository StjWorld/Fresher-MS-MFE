package com.ordersms.orders.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordersms.orders.config.CartWithProds;
import com.ordersms.orders.repository.OrderRepo;


@Component
public class CartListener {
	
	@Autowired
	OrderRepo repo;
	
	@Autowired
	RabbitSender sender;
	
	private final String queue = "cart-to-order";
	
	@RabbitListener(queues = queue)
	public void listen(CartWithProds message) {
		CartWithProds cwp = new CartWithProds();
		cwp.setCartId(message.getCartId());
		cwp.setProds(message.getProds());
		
		System.out.println(cwp);
	}
}
