package com.hcl.orders_ms.publisher;

import com.hcl.orders_ms.models.Cart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerToProd {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.cart-to-order}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducerToProd.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducerToProd(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Cart cart){
//        LOGGER.info(String.format("Json message sent -> %s", cart.toString()));
        rabbitTemplate.convertAndSend(exchange,routingKey,cart);
    }
}
