package com.drivingtalking.configure;



import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue confirmQueue() {
        return new Queue("driving-working");
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange-2");
    }

    @Bean
    public Binding bindMessage() {
        return BindingBuilder.bind(confirmQueue()).to(exchange()).with("driving-working");
    }
}
