package com.hcl.orders_ms.publisher;

import com.hcl.orders_ms.config.CartWithProds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerToProd {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerToProd.class);

    private RabbitTemplate rabbitTemplate;

    public ProducerToProd(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

//    public void sendMessage(Cart cart){
////        LOGGER.info(String.format("Json message sent -> %s", cart.toString()));
//        rabbitTemplate.convertAndSend(exchange,routingKey,cart);
//    }

        public void sendMessage(CartWithProds cart){
//        LOGGER.info(String.format("Json message sent -> %s", cart.toString()));
        rabbitTemplate.convertAndSend(exchange,routingKey,cart);
    }
}
