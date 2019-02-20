package com.zl.springbootrabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.zl.springbootrabbitmq.producer.Send;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    Send send;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Test
    public void sendString(){
        System.out.println("send message");
        send.setEmail(new Date(System.currentTimeMillis())+"->hello world ");
    }

    @Test
    public void send1(){
        send.send1();
    }

    @Test
    public void send2(){
        String context = "hi , i am message 2";
        System.out.println("send: "+context);
        amqpTemplate.convertAndSend("exchange","topic.messages",context);
    }
    /*@Test
    public void send() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("weather",false,false,false,null);
        channel.basicPublish("","weather",null,"helloword".getBytes());
        System.out.println("send ...");
        channel.close();
        connection.close();
    }

    @Test
    public void receive() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("weather",false,false,false,null);
        System.out.println("waiting for : ");
        DefaultConsumer consumer = new DefaultConsumer(channel);
        channel.basicConsume("weather",true,consumer);
        while (true){
        }
    }*/
}
