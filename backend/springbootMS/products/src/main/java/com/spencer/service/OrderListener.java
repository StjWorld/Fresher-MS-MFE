package com.spencer.service;

import java.util.HashMap;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spencer.repo.ProductRepo;

@Component
public class OrderListener {
	
	@Autowired
	ProductRepo repo;
	
	private final String queue = "order-to-prod";
	
	@RabbitListener(queues = queue)
	public void listen(HashMap<Long,Long> message) {
		
	}
}
