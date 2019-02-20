package com.zl.springbootrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zl
 * @date: 2019-02-20 10:09
 */
@Service
public class Receive {

    @RabbitListener(queues = "hello")
    public void receiveString(String message){
        System.out.println("receive string<"+message+">");
    }

    @RabbitListener(queues = "hello")
    public void receiveString2(String message){
        System.out.println("receive string2<"+message+">");
    }

    @RabbitListener(queues = "topic.message")
    public void receiveString3(String message){
        System.out.println("<message> receive "+message);
    }

    @RabbitListener(queues = "topic.#")
    public void receiveString4(String message){
        System.out.println("#receive "+message);
    }

}
