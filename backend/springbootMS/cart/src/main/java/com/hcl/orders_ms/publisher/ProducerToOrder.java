package com.hcl.orders_ms.publisher;

import com.hcl.orders_ms.config.CartWithProds;
import com.hcl.orders_ms.models.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerToOrder {

    //@Value("${rabbitmq.exchange.name}")
    private String exchange = "some-exchange";

    //@Value("${rabbitmq.routing.key.cart-to-order}")
    private String routingKey = "cart-order-routingkey";

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducerToProd.class);

    private RabbitTemplate rabbitTemplate;

    public ProducerToOrder(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(CartWithProds cartWithProds){
//        LOGGER.info(String.format("Json message sent -> %s", cart.toString()));
    	System.out.println("Sending message");
        rabbitTemplate.convertAndSend(exchange,routingKey,cartWithProds);
    }
}
