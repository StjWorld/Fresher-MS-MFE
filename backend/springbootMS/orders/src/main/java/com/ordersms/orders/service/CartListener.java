package com.ordersms.orders.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ordersms.orders.config.CartWithProds;

import com.ordersms.orders.config.InventoryUpdate;
import com.ordersms.orders.entity.Order;
import com.ordersms.orders.entity.OrderProducts;

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
		InventoryUpdate toUpdate = new InventoryUpdate();
		Order toSave = new Order();
		List<OrderProducts> items = new ArrayList<>();
		
		//populate inventory update
		toUpdate.setProds(message.getProds());
		//populate order and save to backend
		toSave.setId(message.getCartId());
		toSave.setOrderDate(new Date());
		message.getProds().forEach((key,value)->{
			OrderProducts prod = new OrderProducts();
			prod.setProductId(key);
			prod.setProductQuantity(value.intValue());
			prod.setOrder(toSave);
			items.add(prod);
		});
		toSave.setOrderProducts(items);
		repo.save(toSave);
		sender.sendMessage(toUpdate);

	}
}
