package com.spencer.service;

import java.util.HashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spencer.config.CartWithProds;
import com.spencer.repo.ProductRepo;

@Component
public class CartListener {
	
	@Autowired
	ProductRepo repo;
	
	private final String queue = "cart-to-prod";
	
	@RabbitListener(queues = queue)
	public void listen(CartWithProds message) {
		CartWithProds cwp = new CartWithProds();
		cwp.setCartId(message.getCartId());
		cwp.setProds(message.getProds());
		
		//START business logic//
		
		// END  business logic// 
		
		/* Some test print */
		System.out.println("Cart ID: "+cwp.getCartId()+"\nProds: ");
		cwp.getProds().forEach((key, value) -> {
			System.out.println("["+key+" = "+ value +"]");
		});
		/**/
	}
		
}