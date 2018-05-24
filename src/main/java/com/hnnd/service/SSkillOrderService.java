package com.hnnd.service;

import com.hnnd.entity.SSkillOrder;

/**
 * 秒杀订单orderService
 * Created by 85073 on 2018/4/1.
 */
public interface SSkillOrderService {

    /**
     * 根据用户id 和商品id  查询秒杀订单
     * @param sskillUserId  用户ID
     * @param sskillGoodsId 秒杀商品ID
     * @return
     */
    public SSkillOrder querySSKillOrderByCondition(long sskillUserId, long sskillGoodsId);

    /**
     * 插入秒杀订单表
     * @param sSkillOrder 秒杀订单表
     * @return
     */
    public int insertSSkillOrder(SSkillOrder sSkillOrder);
}
