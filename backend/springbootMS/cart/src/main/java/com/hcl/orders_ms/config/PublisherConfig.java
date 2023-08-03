package com.hcl.orders_ms.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class PublisherConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.name.cart-to-order}")
    private String queue;

    @Value("${rabbitmq.routing.key.cart-to-order}")
    private String routing;

    @Bean
    public Queue queueToOrder(){
        return new Queue(queue);
    }

    @Bean
    public TopicExchange exchangeToOrder(){
        return new TopicExchange(exchange);
    }

    //bind que with exchange using routing key
    @Bean
    public Binding bindingToOrder(){
        return BindingBuilder.bind(queueToOrder())
                .to(exchangeToOrder())
                .with(routing);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate =
                    new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
