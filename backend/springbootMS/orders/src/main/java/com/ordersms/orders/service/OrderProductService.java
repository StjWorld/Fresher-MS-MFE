package com.ordersms.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ordersms.orders.entity.Order;
import com.ordersms.orders.entity.OrderProducts;
import com.ordersms.orders.repository.OrderProductsRepo;

@Service
public class OrderProductService {
	
	@Autowired
	OrderProductsRepo repo;
	
	//To be used to update order
	public List<OrderProducts> findByOrderId(Order order) {
		OrderProducts toSearch = new OrderProducts();
		toSearch.setOrder(order);
		Example<OrderProducts> example = Example.of(toSearch);
		return repo.findAll(example);
	}
}
