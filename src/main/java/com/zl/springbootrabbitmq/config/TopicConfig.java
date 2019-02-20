package com.zl.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: topic 是RabbitMQ最灵活的一种方式，根据routing_key自由的绑定不同的队列
 * topic模式：生产者将消息发送给Exchange，依据路由关键字routingkey绑定
 * Exchange与Queues队列的依据是bingdingkey
 * @author: zl
 * @date: 2019-02-20 13:11
 */
@Configuration
public class TopicConfig {

    final static String message = "topic.message";
    final static String mes = "topic.#";


    @Bean(name="note")
    public Queue queueMessage1(){
        return new Queue(TopicConfig.mes);
    }

    @Bean(name="message")
    public Queue queueMessage(){
        return new Queue(TopicConfig.message);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    /**
     * 将队列message绑定到交换机上，绑定规则是topic.message
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将队列绑定到交换机上，绑定规则是topic.#
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(@Qualifier("note") Queue queueMessages,TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
