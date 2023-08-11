package com.hcl.orders_ms.consumer;

import com.hcl.orders_ms.config.CartWithProds;
import com.hcl.orders_ms.config.ProdAck;
import com.hcl.orders_ms.models.CartItem;
import com.hcl.orders_ms.publisher.ProducerToOrder;
import com.hcl.orders_ms.service.CartItemService;
import com.hcl.orders_ms.service.CartService;

import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProdConsumer.class);

    @Autowired
    CartService service;
    
    @Autowired
    CartItemService itemService;

    @Autowired
    ProducerToOrder producerToOrder;
    
    @Transactional
    @RabbitListener(queues={"${rabbitmq.queue.name.prod}"})
    public void consumerJsonMessage(ProdAck prodAck){
        LOGGER.info(String.format("Received JSON message -> \n%s",prodAck));

        //if stock is no problem, send message to order service.
        if(prodAck.isAck()){
        	try {
        		List<CartItem> cart = itemService.getByCartId(prodAck.getCartId());
        		System.out.println("The cart: "+cart);
        		if(!cart.isEmpty()) {
        			CartWithProds cartWithProds = new CartWithProds();
                    HashMap<Long,Long> map = new HashMap<>();
    
                    cartWithProds.setCartId(prodAck.getCartId());
                    cart.forEach(item->{
                    	map.put(item.getProductId(), Long.valueOf(item.getQuantity()));
                    });
                    cartWithProds.setProds(map);

                    //send message order service
                    System.out.println("Sending {"+cartWithProds+"} to Order MS");
                    producerToOrder.sendMessage(cartWithProds);        			
                    //delete purchased cart from cart db
                    cart.forEach(item ->{
                    	itemService.deleteItem(item.getItemId());
                    });
                    service.deleteCart(prodAck.getCartId());
                    
        		}else {
        			System.out.println("Cart not found");
        		}
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }else{
            System.out.println("Unable to process order, reason:"+prodAck.getMessage());
        }

    }
}
