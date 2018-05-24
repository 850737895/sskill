package com.hnnd.entity;

/**
 *秒杀订单表
 *   `sskill_order_id` bigint(100) NOT NULL AUTO_INCREMENT,
 `order_id` varchar(100) DEFAULT NULL,
 `sskill_goods_id` bigint(10) DEFAULT NULL,
 `user_id` bigint(10) DEFAULT NULL,
 * Created by 85073 on 2018/4/1.
 */
public class SSkillOrder {

    private long sskillOrderId;

    private String orderId;

    private long sskillGoodsId;

    private long userId;

    @Override
    public String toString() {
        return "SSkillOrder{" +
                "sskillOrderId=" + sskillOrderId +
                ", orderId='" + orderId + '\'' +
                ", sskillGoodsId='" + sskillGoodsId + '\'' +
                ", userId=" + userId +
                '}';
    }

    public long getSskillOrderId() {
        return sskillOrderId;
    }

    public void setSskillOrderId(long sskillOrderId) {
        this.sskillOrderId = sskillOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getSskillGoodsId() {
        return sskillGoodsId;
    }

    public void setSskillGoodsId(long sskillGoodsId) {
        this.sskillGoodsId = sskillGoodsId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
