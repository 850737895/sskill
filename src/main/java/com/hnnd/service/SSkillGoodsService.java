package com.hnnd.service;

import com.hnnd.entity.Goods;
import com.hnnd.entity.SSkillGoods;
import com.hnnd.vo.GoodsVo;

import java.util.List;

/**
 * 秒杀商品接口
 * Created by 85073 on 2018/4/1.
 */
public interface SSkillGoodsService {

    /**
     * 查询秒杀商品列表
     * @return
     */
    public List<GoodsVo> queryGoodsVoList();

    /**
     * 查询商品详情
     * @param id 商品id
     * @return GoodsVo
     */
    public GoodsVo queryGoodsVoDetails(long id);

    /**
     * 更新秒杀商品表库存
     * @param goodsVo 商品对象
     * @return 库存是否减少成功
     */
    public boolean updateSSkillGoodsStock(GoodsVo goodsVo);
}
