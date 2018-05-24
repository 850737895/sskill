package com.hnnd.vo;

import com.hnnd.entity.SSkillUser;

/**
 * 秒杀商品详情页面
 * Created by 85073 on 2018/4/4.
 */
public class GoodsDetailVo {

    private SSkillUser sSkillUser;

    private GoodsVo goodsVo;

    private Integer sskillStatus;

    private Integer remainSeconds;

    public SSkillUser getsSkillUser() {
        return sSkillUser;
    }

    public void setsSkillUser(SSkillUser sSkillUser) {
        this.sSkillUser = sSkillUser;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }

    public Integer getSskillStatus() {
        return sskillStatus;
    }

    public void setSskillStatus(Integer sskillStatus) {
        this.sskillStatus = sskillStatus;
    }

    public Integer getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(Integer remainSeconds) {
        this.remainSeconds = remainSeconds;
    }
}
