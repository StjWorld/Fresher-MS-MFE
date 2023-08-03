package com.spencer.service;

import java.util.HashMap;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spencer.entity.Product;

@Service
public class RabbitSender {
	
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public RabbitSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate=rabbitTemplate;
	}
	
	// For testing purposes, should be changed to accept message templates
	public void sendMessage(Product product) {
		HashMap<Long, Long> obj = new HashMap<Long, Long>();
		obj.put(product.getPId(), product.getQty());
		rabbitTemplate.convertAndSend("order-to-prod", obj);
	}
}

