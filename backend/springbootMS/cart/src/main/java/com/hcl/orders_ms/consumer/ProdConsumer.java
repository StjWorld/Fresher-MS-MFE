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
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProdConsumer.class);

    @Autowired
    CartRepo cartRepo;

    @Autowired
    ProducerToOrder producerToOrder;
    @RabbitListener(queues={"${rabbitmq.queue.name.prod}"})
    public void consumerJsonMessage(ProdAck prodAck){
        LOGGER.info(String.format("Received JSON message -> %s",prodAck));

        //if stock is no problem, send message to order service.
        if(prodAck.isAck()){

            Cart cart = cartRepo.findById(prodAck.getCartId()).get();

            //send message order service
            producerToOrder.sendMessage(cart);

            //Once sent to order service, delete the record from cart database;
            cartRepo.delete(cart);

        }else{
            System.out.println("return out of stock message...");
        }

    }
}
