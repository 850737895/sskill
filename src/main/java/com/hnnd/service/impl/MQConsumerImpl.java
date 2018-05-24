package com.hnnd.service.impl;

import com.hnnd.constants.MDA;
import com.hnnd.entity.SSkillUser;
import com.hnnd.service.MQConsumer;
import com.hnnd.service.SSKillService;
import com.hnnd.service.SSkillGoodsService;
import com.hnnd.util.BeanUtil;
import com.hnnd.vo.GoodsVo;
import com.hnnd.vo.SkillOrderMsgBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消费者
 * Created by 85073 on 2018/4/6.
 */
@Service
public class MQConsumerImpl implements MQConsumer {

    public static final Logger logger = LoggerFactory.getLogger(MQConsumerImpl.class);

    @Autowired
    private SSkillGoodsService sSkillGoodsServiceImpl;
    @Autowired
    private SSKillService ssKillServiceImpl;

    /**
     * 四种交换机(exchange)模式之:
     * direct 模式
     * @param msg
     */
    @RabbitListener(queues = MDA.ORDER_QUEUE_NAME)
    @Override
    public void receiveMsg(String msg) {
        logger.info("消费者从队列:{}中消费消息{}",MDA.ORDER_QUEUE_NAME,msg);
    }

    @RabbitListener(queues = {MDA.DIRECT_QUEUE,MDA.DIRECT_QUEUE2})
    @Override
    public void reciveMsg4DirectQueue(String msg) {
        logger.info("通过直接交换机模式来获取消息:{}",msg);
    }

    @RabbitListener(queues =MDA.FANOUT_QUEUE)
    @Override()
    public void reciveMsg4FanoutQueue(String msg) {
        logger.info("通过扇形交换机模式来获取消息:{}",msg);
    }

    @RabbitListener(queues =MDA.TOPIC_QUEUE)
    @Override
    public void reciveMsg4TopicQueue(String msg) {
        logger.info("通过主题交换机模式从  TOPIC_QUEUE 获取消息:{}",msg);
    }

    @RabbitListener(queues =MDA.TOPIC_QUEUE2)
    @Override
    public void reciveMsg4TopicQueue2(String msg) {
        logger.info("通过主题交换机模式从  TOPIC_QUEUE2 获取消息:{}",msg);
    }

    @RabbitListener(queues =MDA.HEADER_QUEUE)
    @Override
    public void reciveMsg4HeaderQueue(byte[] message) {
        logger.info("通过主题交换机模式从  TOPIC_QUEUE2 获取消息:{}",new String(message));
    }


    @RabbitListener(queues = MDA.SSKILL_ORDER_QUEUE)
    @Override
    public void reciveSkillOrderMsg(String msg) {
        logger.info("消息队列开始处理秒杀订单信息{}",msg);
        SkillOrderMsgBo skillOrderMsgBo = BeanUtil.string2Bean(msg,SkillOrderMsgBo.class);

        SSkillUser sSkillUser = skillOrderMsgBo.getSsKillUser();
        Long sskillGoogdsId = skillOrderMsgBo.getSskillGoodsId();

        //synchronized (MQConsumerImpl.class){
            //判断真是的库存
            GoodsVo goodsVo = sSkillGoodsServiceImpl.queryGoodsVoDetails(skillOrderMsgBo.getSskillGoodsId());
            if(goodsVo.getSskillStockCount()<=0) {
                logger.warn("秒杀商品库存不足,用户:{}秒杀商品{}失败",sSkillUser.getUserId(),goodsVo.getGoodsName());
                return ;
            }
            //判断是否秒杀过
            if(ssKillServiceImpl.isSkilled(sSkillUser.getUserId(),sskillGoogdsId)){
                logger.warn(",用户:{}重复秒杀商品{}",sSkillUser.getUserId(),goodsVo.getGoodsName());
                return ;
            }
            //创建订单
            ssKillServiceImpl.doSkill(sSkillUser,goodsVo);
        //}

    }


}
