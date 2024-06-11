package com.example.testrabbitmq.consumer;

import com.example.testrabbitmq.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqListener {

    @RabbitListener(queues = "sample.queuejh2022101")
    public void receiveMessage(String message){
//    public void receiveMessage(Message message){
        log.info("### message = {}",message.toString());
    }
}
