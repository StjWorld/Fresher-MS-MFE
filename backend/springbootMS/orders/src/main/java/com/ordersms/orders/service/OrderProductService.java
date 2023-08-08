package com.ordersms.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordersms.orders.entity.OrderProducts;
import com.ordersms.orders.repository.OrderProductsRepo;

@Service
public class OrderProductService {
	
	@Autowired
	OrderProductsRepo repo;
	
	public OrderProducts findByOrderId() {
		return new OrderProducts();
	}
}
