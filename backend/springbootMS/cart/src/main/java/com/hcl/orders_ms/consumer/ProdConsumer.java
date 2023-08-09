package com.hcl.orders_ms.consumer;

import com.hcl.orders_ms.config.CartWithProds;
import com.hcl.orders_ms.config.ProdAck;
import com.hcl.orders_ms.models.Cart;
import com.hcl.orders_ms.models.CartItem;
import com.hcl.orders_ms.publisher.ProducerToOrder;
import com.hcl.orders_ms.service.CartItemService;
import com.hcl.orders_ms.service.CartService;

import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.Optional;

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
        		Optional<Cart> cart = service.getCartById(prodAck.getCartId());
        		if(cart.isPresent()) {
        			CartWithProds cartWithProds = new CartWithProds();
                    HashMap<Long,Long> map = new HashMap<>();
    
                    cartWithProds.setCartId(cart.get().getId());
                    for(CartItem cartItem: cart.get().getCartItems()){
                        map.put(cartItem.getProductId(), Long.valueOf(cartItem.getQuantity()));
                    }
                    cartWithProds.setProds(map);

                    //send message order service
                    producerToOrder.sendMessage(cartWithProds);        			

                    //Once sent to order service, delete the record from cart database;
                    // Had to compound deleting from cart_item database since databases are decoupled
                    // Query for deleting by cartId (one call instead of many):
                    //		DELETE FROM cart_item c WHERE c.cart_id = ?1
                    cart.get().getCartItems().forEach(item ->{
                    	itemService.deleteItem(item.getItemId());
                    });
                    service.deleteCart(cart.get().getId());
                    
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
