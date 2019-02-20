package com.zl.springbootrabbitmq.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zl
 * @date: 2019-02-20 10:09
 */
@Service
public class Send {

    @Autowired
    RabbitMessagingTemplate rabbitMessagingTemplate;

    @Autowired
    AmqpTemplate template;

    /**
     * 简单测试一对一消费者，生产者关系
     * @param message
     */
    public void setEmail(String message){
        //rabbitMessagingTemplate.convertAndSend("exchange","queue.string",message);
        template.convertAndSend("hello",message);
    }

    /**
     * 测试topic模式消息队列
     */
    public void send1(){
        String context = "hi, i am message 1";
        System.out.println("send: "+context);
        template.convertAndSend("exchange","topic.message",context);
    }
}
