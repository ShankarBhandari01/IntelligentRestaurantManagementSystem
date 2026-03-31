package com.shankar.intelligentrestaurantmanagementsystem.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String QUEUE_NAME = "order_queue";
    public static final String EXCHANGE_NAME = "kot_exchange";


    @Bean
    public DirectExchange kotExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    public Queue createDeviceQueue(String deviceId) {
        return QueueBuilder.durable("kot_device_" + deviceId)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", "kot_device_" + deviceId + "_dlq")
                .build();
    }

    public Binding bindDeviceQueue(Queue queue, DirectExchange exchange, String deviceId) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(deviceId);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    Queue orderQueue() {
        return QueueBuilder.durable("order_queue")
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", "order_queue_dlq")
                .build();
    }

}
