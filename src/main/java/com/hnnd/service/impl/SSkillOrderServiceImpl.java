package com.hnnd.service.impl;

import com.hnnd.entity.SSkillOrder;
import com.hnnd.mapper.SSkillOrderMapper;
import com.hnnd.service.SSkillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 秒杀订单的实现
 * Created by 85073 on 2018/4/1.
 */

@Service
public class SSkillOrderServiceImpl implements SSkillOrderService {

    @Autowired
    private SSkillOrderMapper sSkillOrderMapper;

    @Override
    public SSkillOrder querySSKillOrderByCondition(long sskillUserId, long sskillGoodsId) {
        return sSkillOrderMapper.querySSKillOrderByCondition(sskillUserId,sskillGoodsId);
    }

    @Override
    public int insertSSkillOrder(SSkillOrder sSkillOrder) {
        return sSkillOrderMapper.insertSSkillOrder(sSkillOrder);
    }


}
