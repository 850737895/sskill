package com.hnnd.service.impl;

import com.hnnd.constants.MDA;
import com.hnnd.service.MQProducer;
import com.hnnd.util.BeanUtil;
import com.hnnd.vo.SkillOrderMsgBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * mq 消费者实现类
 * Created by 85073 on 2018/4/6.
 */
@Service
public class MQProducerImpl implements MQProducer  {

    public static final Logger logger = LoggerFactory.getLogger(MQProducerImpl.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMsgToQueue(Object obj) {
        String beanStr = BeanUtil.bean2Str(obj);
        logger.info("开始发送消息到{},消息内容为{}", MDA.ORDER_QUEUE_NAME,beanStr);
        amqpTemplate.convertAndSend(MDA.ORDER_QUEUE_NAME,beanStr);
    }

    @Override
    public void sendMsgToDirectQueue(Object obj) {
        String beanStr = BeanUtil.bean2Str(obj);
        logger.info("开始发送消息到{},消息内容为{}", MDA.DIRECT_QUEUE,beanStr);
        amqpTemplate.convertAndSend(MDA.DIRECT_EXCHANGE,MDA.DIRECT_QUEUE,beanStr);
        amqpTemplate.convertAndSend(MDA.DIRECT_EXCHANGE,MDA.DIRECT_QUEUE2,beanStr);
    }

    @Override
    public void sendMsgToFanoutQueue(Object obj) {
        String beanStr = BeanUtil.bean2Str(obj);
        logger.info("开始发送消息到扇形交换机{},消息内容为{}", MDA.FANOUT_EXCHANGE,beanStr);
        amqpTemplate.convertAndSend(MDA.FANOUT_EXCHANGE,"",beanStr);
    }

    @Override
    public void sendMsgToTopicQueue(Object obj) {
        String beanStr = BeanUtil.bean2Str(obj);
        logger.info("开始发送消息到主题交换机{},消息内容为{}", MDA.TOPIC_EXCHANGE,beanStr);
        amqpTemplate.convertAndSend(MDA.TOPIC_EXCHANGE,MDA.TOPIC_QUEUE,beanStr+1);
        amqpTemplate.convertAndSend(MDA.TOPIC_EXCHANGE,"TOPIC.QUEUE5",beanStr+2);
    }

    @Override
    public void sendMsgToHeaderQueue(Object obj) {
        String beanStr = BeanUtil.bean2Str(obj);
        logger.info("开始发送消息到头部交换机{},消息内容为{}", MDA.HEADERS_EXCHANGE,beanStr);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("header1","value1");
        properties.setHeader("header2","value2");
        Message message = new Message(beanStr.getBytes(),properties);
        amqpTemplate.convertAndSend(MDA.HEADERS_EXCHANGE, "", message);
    }

    @Override
    public void sendSkillMsgToSkillOrder(SkillOrderMsgBo skillOrderMsgBo) {
        String beanStr = BeanUtil.bean2Str(skillOrderMsgBo);
        amqpTemplate.convertAndSend(MDA.SSKILL_ORDER_QUEUE,beanStr);
    }
}
