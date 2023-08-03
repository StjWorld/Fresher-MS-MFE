package com.hcl.orders_ms.consumer;

import com.hcl.orders_ms.config.ProdAck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ProdConsumer {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(ProdConsumer.class);

    @RabbitListener(queues={"${rabbitmq.queue.name.prod}"})
    public void consumerJsonMessage(ProdAck yesOrno){
        LOGGER.info(String.format("Received JSON message -> %s",yesOrno));
    }
}
