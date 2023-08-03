package com.hcl.orders_ms.consumer;

import com.hcl.orders_ms.config.ProdAck;
import com.hcl.orders_ms.models.Cart;
import com.hcl.orders_ms.publisher.ProducerToOrder;
import com.hcl.orders_ms.repo.CartRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdConsumer {
    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProducerToOrder producerToOrder;
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProdConsumer.class);

    @RabbitListener(queues={"${rabbitmq.queue.name.prod}"})
    public void consumerJsonMessage(ProdAck prodAck){
        LOGGER.info(String.format("Received JSON message -> %s",prodAck));
        if(prodAck.isAck()){

            System.out.println("Cart is ready to sent to order service");

            Cart cart = cartRepo.findById(prodAck.getCartId()).get();

            //Send cart details to order service
            producerToOrder.sendMessage(cart);

            //Delete record from cart database.
            cartRepo.deleteById(prodAck.getCartId());

        }else{
            System.out.println("some products are out of stock");
        }
    }
}
