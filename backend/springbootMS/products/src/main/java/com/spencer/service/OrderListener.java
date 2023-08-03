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
		// For testing, to be replaced
		HashMap<Long,Long> prods = new HashMap<Long, Long>();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("pIds: "+message.keySet()+"\nQtys: "+message.values());
		// end of testing, to be replaced
		
	}
}
