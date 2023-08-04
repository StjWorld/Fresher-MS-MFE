package com.hcl.orders_ms.config;

import lombok.Data;

// Message template 1
@Data
public class ProdAck {
	private long cartId;
	private boolean ack;
	private String message;
}
