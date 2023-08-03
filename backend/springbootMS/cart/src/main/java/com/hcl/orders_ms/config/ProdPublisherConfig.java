package com.hcl.orders_ms.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdPublisherConfig {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.name}")
    private String queueToProd;

    @Value("${rabbitmq.routing.key}")
    private String routingToProd;

    @Bean
    public Queue queueToProd(){
        return new Queue(queueToProd,false);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(exchange);
    }

    //bind que with exchange using routing key
    @Bean
    public Binding bindingToProd(){
        return BindingBuilder.bind(queueToProd())
                .to(exchange())
                .with(routingToProd);
    }


}
