package com.hnnd.service.impl;

import com.hnnd.Redis.GoodsKey;
import com.hnnd.entity.SSkillGoods;
import com.hnnd.mapper.SSkillGoodsMapper;
import com.hnnd.service.RedisService;
import com.hnnd.service.SSkillGoodsService;
import com.hnnd.vo.GoodsDetailVo;
import com.hnnd.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 秒杀商品接口实现
 * Created by 85073 on 2018/4/1.
 */
@Service
public class SSkillGoodsServiceImpl implements SSkillGoodsService {


    private static final Logger logger = LoggerFactory.getLogger(SSkillGoodsServiceImpl.class);

    @Autowired
    private SSkillGoodsMapper sSkillGoodsMapper;

    @Autowired
    private RedisService redisServiceImpl;

    @Override
    public List<GoodsVo> queryGoodsVoList() {
        logger.info("开始查询秒杀商品列表..........");
        return sSkillGoodsMapper.queryGoodsVoList();
    }

    @Override
    public GoodsVo queryGoodsVoDetails(long id) {

        GoodsVo goodsVo = sSkillGoodsMapper.queryGoodsVoDetails(id);
        return goodsVo;
    }

    @Override
    public boolean updateSSkillGoodsStock(GoodsVo goodsVo) {
        int stoukCount = goodsVo.getSskillStockCount()-1;
        System.out.println("库存................................................."+stoukCount);
        //减少库存
        SSkillGoods upatesSkillGoods = new SSkillGoods();
        upatesSkillGoods.setSskillGoodsId(goodsVo.getId());
        int updateFlag = sSkillGoodsMapper.updateSSkillGoodsStock(upatesSkillGoods);
        if(updateFlag>0){
            return true;
        }
        return false;
    }


}
