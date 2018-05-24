package com.hnnd.service;

import com.hnnd.vo.SkillOrderMsgBo;

/**
 * mq的生产者
 * Created by 85073 on 2018/4/6.
 */
public interface MQProducer {

    /**
     * 生产消息
     * @param obj 消息对象
     */
     void sendMsgToQueue(Object obj);

    /**
     * 发送消息到直接交换机上
     * @param obj
     */
     void sendMsgToDirectQueue(Object obj);

    /**
     * 发送消息到扇形交换机
     * @param obj
     */
     void sendMsgToFanoutQueue(Object obj);

    /**
     * 发送消息到主题交换机
     * @param obj
     */
     void sendMsgToTopicQueue(Object obj);

    /**
     * 发送消息到头部交换机
     * @param obj 消息信息
     */
     void sendMsgToHeaderQueue(Object obj);

    /**
     *@param skillOrderMsgBo 秒杀消息对象
     * 把秒杀信息写入到秒杀队列中
     */
    void sendSkillMsgToSkillOrder(SkillOrderMsgBo skillOrderMsgBo);


}
