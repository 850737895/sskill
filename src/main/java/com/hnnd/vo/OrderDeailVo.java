package com.hnnd.vo;

import com.hnnd.entity.Order;
import com.hnnd.entity.SSkillUser;

/**
 * 订单希详情vo
 * Created by 85073 on 2018/4/5.
 */
public class OrderDeailVo {

    private SSkillUser sSkillUser;

    private Order order;

    private GoodsVo goodsVo;

    public SSkillUser getsSkillUser() {
        return sSkillUser;
    }

    public void setsSkillUser(SSkillUser sSkillUser) {
        this.sSkillUser = sSkillUser;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }
}
