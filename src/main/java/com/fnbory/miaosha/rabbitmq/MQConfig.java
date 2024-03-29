package com.fnbory.miaosha.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.MXBean;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: fnbory
 * @Date: 2019/6/19 21:02
 */
@Configuration
public class MQConfig {

    public  static  final String QUEUE="queue";
    public static final String MIAOSHA_QUEUE = "miaosha.queue";
    public  static  final String TOPIC_QUEUE1="topic.queue1";
    public  static  final String TOPIC_QUEUE2="topic.queue2";
    public  static  final String TOPIC_EXCHANGE="topic.exchange";
    public  static  final String ROUTING_KEY1="topic.key1";
    public  static  final  String ROUTING_KEY2="topic.#";
    public static final String FANOUT_EXCHANGE = "fanoutxchage";
    public static final String HEADERS_EXCHANGE = "headersExchage";
    /**
     * direct
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(QUEUE,true);  // true 持久化
    }

    @Bean
    public Queue miaosha_queue(){
        return new Queue(MIAOSHA_QUEUE,true);  // true 持久化
    }

    @Bean
    public  Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE1,true);
    }

    @Bean
    public  Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE2,true);
    }

    /**
     * topic
     * @return
     */

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTING_KEY1);
    }

    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTING_KEY2);
    }

    /**
     * Fanout
     * @return
     */

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
    }

    /**
     * header
     */
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    @Bean
    public Queue headersQueue1(){
        return new Queue(HEADERS_EXCHANGE,true);
    }

    @Bean
    public Binding headerBinding(){
        Map<String,Object> map=new HashMap<>();
        map.put("header1", "value1");
        map.put("header2", "value2");
        return BindingBuilder.bind(headersQueue1()).to(headersExchange()).whereAll(map).match();
    }





}
