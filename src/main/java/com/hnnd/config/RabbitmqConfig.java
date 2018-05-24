package com.hnnd.config;

import com.hnnd.constants.MDA;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbitmq的配置文件
 * Created by 85073 on 2018/4/6.
 */

@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue queue() {
        return new Queue(MDA.ORDER_QUEUE_NAME,true);
    }

    @Bean
    public Queue directQueue() {
        return new Queue(MDA.DIRECT_QUEUE,true);
    }

    @Bean
    public Queue directQueue2() {
        return new Queue(MDA.DIRECT_QUEUE2,true);
    }

    @Bean
    public Queue fanoutQueue(){
        return new Queue(MDA.FANOUT_QUEUE,true);
    }

    @Bean
    public Queue fanoutQueue2(){
        return new Queue(MDA.FANOUT_QUEUE2,true);
    }

    @Bean
    public Queue topicQueue(){
        return new Queue(MDA.TOPIC_QUEUE,true);
    }

    @Bean
    public Queue topicQueue2(){
        return new Queue(MDA.TOPIC_QUEUE2,true);
    }

    @Bean
    public Queue headersQueue(){
        return new Queue(MDA.HEADER_QUEUE,true);
    }

    @Bean
    public Queue skillOrderQueue() {
        return new Queue(MDA.SSKILL_ORDER_QUEUE,true);
    }


    /**
     * 直接交换机 DirectExchange
     * @return DirectExchange
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(MDA.DIRECT_EXCHANGE);
    }

    /**
     * 扇形交换机(广播模式)
     * @return FanoutExchange
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(MDA.FANOUT_EXCHANGE);
    }

    /**
     * 主题交换机
     * @return FanoutExchange
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(MDA.TOPIC_EXCHANGE);
    }

    /**
     * 头部交换机
     * @return FanoutExchange
     */
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(MDA.HEADERS_EXCHANGE);
    }

    /**
     * 直接交换机绑定 队列 DIRECT_QUEUE router_key:DIRECT_QUEUE
     * @return
     */
    @Bean
    public Binding directExchangeBinding(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(MDA.DIRECT_QUEUE);
    }



    /**
     * 直接交换机绑定 队列 DIRECT_QUEUE2 router_key:DIRECT_QUEUE2
     * @return
     */
    @Bean
    public Binding directExchangeBinding2(){
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with(MDA.DIRECT_QUEUE2);
    }

    @Bean
    public Binding fanoutExchangeBingdingFanoutQueue(){
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutExchangeBingdingFanoutQueue2(){
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    @Bean
    public Binding topicExchangeBingdingTopicQueue(){
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(MDA.TOPIC_QUEUE);
    }

    @Bean
    public Binding topicExchangeBingdingTopicQueue2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(MDA.TOPIC_QUEUE2);
    }

    @Bean
    public Binding headersExchangeBingdingHeadersQueue(){
        /**
         * 消息中必须含有 header1 value1   header2 value2 消息才能发送到交换机上
         */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("header1", "value1");
        map.put("header2", "value2");
        return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
    }
}
