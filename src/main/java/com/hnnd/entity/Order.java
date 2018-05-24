package com.hnnd.entity;

import java.util.Date;

/**
 * 订单表
 *   `order_id` varchar(100) NOT NULL,
 `user_id` bigint(100) NOT NULL,
 `goods_id` bigint(20) NOT NULL,
 `order_address` varchar(300) NOT NULL,
 `goods_name` varchar(100) NOT NULL,
 `goods_price` decimal(10,2) NOT NULL,
 `order_channel` int(5) NOT NULL COMMENT '1:pc  2安卓 3：ios',
 `order_status` int(5) NOT NULL COMMENT '0:新创建未支付 1:已支付，2已发货，3已收货 4:已退款 5已完成',
 `create_date` datetime NOT NULL,
 `pay_date` datetime NOT NULL,
 * Created by 85073 on 2018/4/1.
 */
public class Order {

    private String orderId;

    private long userId;

    private long goodsId;

    private String orderAddress;

    private String goodsName;

    private double goodsPrice;

    private Integer goodsCount;

    private Integer orderChannel=1;

    private Integer orderStatus=0;

    private Date createDate;

    private Date payDate;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", orderAddress='" + orderAddress + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                ", goodsCount=" + goodsCount +
                ", orderChannel=" + orderChannel +
                ", orderStatus=" + orderStatus +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                '}';
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
