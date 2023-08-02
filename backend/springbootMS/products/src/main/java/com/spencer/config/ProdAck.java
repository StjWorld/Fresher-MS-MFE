package com.spencer.config;

import lombok.Data;

// Message template 1
@Data
public class ProdAck {
	private long cartId;
	private boolean ack;
	private String message;
}
