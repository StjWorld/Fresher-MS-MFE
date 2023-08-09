package com.spencer.service;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spencer.config.InventoryUpdate;
import com.spencer.entity.Product;

@Component
public class OrderListener {
	
	@Autowired
	ProductService service;
	
	private final String queue = "order-to-prod";
	
	@RabbitListener(queues = queue)
	public void listen(InventoryUpdate message) {
		System.out.println(message);
		try {
			message.getProds().forEach((key, value) ->{
				Optional<Product> prod = service.getProductById(key);
				prod.get().setQty(prod.get().getQty()-value);
				service.updateProd(prod.get());
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
