package com.hnnd.entity;

import java.util.Date;

/**
 * 参加秒杀商品表
 * `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `sskill_goods_id` bigint(20) NOT NULL,
 `sskill_price` decimal(10,2) NOT NULL,
 `sskill_stock_count` int(10) NOT NULL,
 `sskill_start_time` datetime NOT NULL,
 `sskill_end_time` datetime NOT NULL,
 * Created by 85073 on 2018/4/1.
 */
public class SSkillGoods {

    private long id;

    private long sskillGoodsId;

    private double sskillPrice;

    private Integer sskillStockCount;

    private Date sskillStartTime;

    private Date sskillEndTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSskillGoodsId() {
        return sskillGoodsId;
    }

    public void setSskillGoodsId(long sskillGoodsId) {
        this.sskillGoodsId = sskillGoodsId;
    }

    public double getSskillPrice() {
        return sskillPrice;
    }

    public void setSskillPrice(double sskillPrice) {
        this.sskillPrice = sskillPrice;
    }

    public Integer getSskillStockCount() {
        return sskillStockCount;
    }

    public void setSskillStockCount(Integer sskillStockCount) {
        this.sskillStockCount = sskillStockCount;
    }

    public Date getSskillStartTime() {
        return sskillStartTime;
    }

    public void setSskillStartTime(Date sskillStartTime) {
        this.sskillStartTime = sskillStartTime;
    }

    public Date getSskillEndTime() {
        return sskillEndTime;
    }

    public void setSskillEndTime(Date sskillEndTime) {
        this.sskillEndTime = sskillEndTime;
    }

    @Override
    public String toString() {
        return "SSkillGoods{" +
                "id=" + id +
                ", sskillGoodsId=" + sskillGoodsId +
                ", sskillPrice=" + sskillPrice +
                ", sskillStockCount=" + sskillStockCount +
                ", sskillStartTime=" + sskillStartTime +
                ", sskillEndTime=" + sskillEndTime +
                '}';
    }
}
