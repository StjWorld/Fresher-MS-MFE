package com.ordersms.orders.config;

import lombok.Data;

import java.util.HashMap;

// Message template 2
@Data
public class CartWithProds {
	private long cartId;
	private HashMap<Long, Long> prods;
}
