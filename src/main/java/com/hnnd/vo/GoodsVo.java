package com.hnnd.vo;

import com.hnnd.entity.Goods;

import java.util.Date;

/**
 * Created by 85073 on 2018/4/1.
 */
public class GoodsVo extends Goods {

    private double sskillPrice;

    private Integer sskillStockCount;

    private Date sskillStartTime;

    private Date sskillEndTime;

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
        return "GoodsVo{" +
                "sskillPrice=" + sskillPrice +
                ", sskillStockCount=" + sskillStockCount +
                ", sskillStartTime=" + sskillStartTime +
                ", sskillEndTime=" + sskillEndTime +
                '}';
    }
}
