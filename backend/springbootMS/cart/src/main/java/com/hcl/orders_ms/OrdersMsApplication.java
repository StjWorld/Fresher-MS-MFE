package com.hcl.orders_ms;

import com.hcl.orders_ms.models.Cart;
import com.hcl.orders_ms.models.CartItem;
import com.hcl.orders_ms.repo.CartItemRepo;
import com.hcl.orders_ms.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class OrdersMsApplication implements CommandLineRunner {
//public class OrdersMsApplication{


	@Autowired
	CartRepo cartRepo;

	@Autowired
	CartItemRepo cartItemRepo;


	public static void main(String[] args) {
		SpringApplication.run(OrdersMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		CartItem cartItem = new CartItem(1,5);
		CartItem cartItem2 = new CartItem(2,15);
		Date date = new Date();
		Long id = Long.valueOf(3);
		Cart cart = new Cart(id, date);
		cart.add(cartItem);
		cart.add(cartItem2);
		cartRepo.save(cart);
		cartItemRepo.save(cartItem);
		cartItemRepo.save(cartItem2);

	}

}
