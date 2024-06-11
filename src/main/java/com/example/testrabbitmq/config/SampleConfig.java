package com.example.testrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleConfig {
    public static final String EXCHANGE_NAME = "sample.jh2022101";
    public static final String QUEUE_NAME = "sample.queuejh2022101";
    public static final String ROUTING_KEY = "sample.routingkey.jh2022101";

    /**
     * Publisher 설정.
     * 앞서 RabbitMQ management UI (localhost:15672)에서 설정한 exchange, queue, routing key 정보를 바탕으로 bean 생성
     */

//    @Bean
//    TopicExchange exchange(){
//        return new TopicExchange(EXCHANGE_NAME);
//    }
    @Bean
    DirectExchange exchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue queue() {
//        return new Queue(QUEUE_NAME);
        return new Queue(QUEUE_NAME, true);// 메시지가 안사라지려면.........?
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
        //Binding은 exchange와 routing key의 패턴이 일치하는 queue에 메시지를 전달하겠다는 일종의 규칙
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        // rabbitTemplate.setMessageConverter를 통해 메세지에 담을 Object를 rabbitmq의 메시지 형식으로 변환
        // 만약 Jackson2JsonMessageConverter 를 사용하지 않는다면 우리는 ObjectMapper를 통해 내부에서 한 번 더 변환 작업을 수행하고 메세지를 발행해야 하는 추가 작업이 발생

        return rabbitTemplate;
    }




}
