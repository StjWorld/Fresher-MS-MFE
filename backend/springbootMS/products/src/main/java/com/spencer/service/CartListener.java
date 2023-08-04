package com.spencer.service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spencer.config.CartWithProds;
import com.spencer.config.ProdAck;
import com.spencer.entity.Product;

@Component
public class CartListener {
	
	@Autowired
	ProductService service;	
	
	@Autowired
	RabbitSender sender;
	
	private final String queue = "cart-to-prod";
	@RabbitListener(queues = queue)
	public void listen(CartWithProds message) {
		CartWithProds cwp = new CartWithProds();
		AtomicReference<Boolean> flag = new AtomicReference<Boolean>();
		AtomicReference<String> body = new AtomicReference<String>();
		
		flag.set(true);
		body.set("");
		
		cwp.setCartId(message.getCartId());
		cwp.setProds(message.getProds());
		
		//START business logic//
		cwp.getProds().forEach((key, value) -> {
			try {
				Optional<Product> prod = service.getProductById(key);
				if(!prod.isPresent()) {
					// set flag=false, message="Product with pId = "+key+" not found"
					flag.set(false);
					body.set(body.get()+"\nProduct with pId = "+key+" not found");
				}else {
					if(prod.get().getQty() < value) {
						//set flag=false, message="Product with pId = "+key+" doesn't have enough qty to fulfill order"
						flag.set(false);
						body.set(body.get()+"\nProduct with pId = "+key+" doesn't have enough qty to fulfill order");
					}
				}
			} catch(Exception e) {
				System.out.println(e);
				flag.set(false);
				body.set(body.get()+"\n"+e);
			}
		});
		//System.out.println("cartId = "+message.getCartId()+"\nack = "+flag.get().toString()+"\nmessages: "+body.get());
		ProdAck ack = new ProdAck();
		ack.setCartId(message.getCartId());
		ack.setAck(flag.get());
		ack.setMessage(body.get());
		sender.sendMessage(ack);
	}
		
}