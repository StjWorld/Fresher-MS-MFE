package com.spencer.config;

import java.util.HashMap;
import lombok.Data;

// Message template 2
@Data
public class CartWithProds {
	private long cartId;
	private HashMap<Long, Integer> prods; 
}
