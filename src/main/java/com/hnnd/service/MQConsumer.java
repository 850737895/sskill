package com.hnnd.service;

/**
 * mq的消费者
 * Created by 85073 on 2018/4/6.
 */
public interface MQConsumer {

     void receiveMsg(String msg);

    /**
     * 从直接交换机模式 中获取消息
     * @param msg 消息
     */
    void reciveMsg4DirectQueue(String msg);

    /**
     * 从扇形交换机模式获取消息
     * @param msg 消息
     */
    void reciveMsg4FanoutQueue(String msg);

    /**
     * 从主題交换机模式获取消息
     * @param msg 消息
     */
    void reciveMsg4TopicQueue(String msg);

    /**
     * 从主題交换机模式获取消息
     * @param msg 消息
     */
    void reciveMsg4TopicQueue2(String msg);

    /**
     * 从头部交换机中获取消息
     * @param bytes
     */
    public void reciveMsg4HeaderQueue(byte[] bytes);

    /**
     * 接受秒杀订单消息
     * @param msg 秒杀订单消息
     */
    void reciveSkillOrderMsg(String msg);


}
