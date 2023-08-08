package com.ordersms.orders.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordersms.orders.config.InventoryUpdate;

@Service
public class RabbitSender {
private RabbitTemplate rabbitTemplate;
	
	@Autowired
	public RabbitSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate=rabbitTemplate;
	}
	
	// For testing purposes, should be changed to accept message templates
	public void sendMessage(InventoryUpdate stock) {
		rabbitTemplate.convertAndSend("order-to-prod", stock);
	}
}
