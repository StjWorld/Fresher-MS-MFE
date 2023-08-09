package com.hcl.orders_ms.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdPublisherConfig {
	
    private String exchange = "some-exchange";
    private String queueToProd = "cart-to-prod";
    private String routingToProd = "cart-routingkey";

    @Bean
    public Queue queueToProd(){
        return new Queue(queueToProd, false);
    }

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(exchange);
    }

    //bind queue with exchange using routing key
    @Bean
    public Binding bindingToProd(){
        return BindingBuilder.bind(queueToProd())
                .to(exchange())
                .with(routingToProd);
    }


}
