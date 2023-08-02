package com.spencer.config;

import java.util.HashMap;
import lombok.Data;

// Message template 3
@Data
public class InventoryUpdate {
	HashMap<Long, Integer> prods;
}
