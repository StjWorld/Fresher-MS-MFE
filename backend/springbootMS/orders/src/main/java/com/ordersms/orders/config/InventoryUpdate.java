package com.ordersms.orders.config;

import java.util.HashMap;
import lombok.Data;

// Message template 3
@Data
public class InventoryUpdate {
	HashMap<Long, Long> prods;
}
