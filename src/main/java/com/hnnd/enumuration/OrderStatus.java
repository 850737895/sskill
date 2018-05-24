package com.hnnd.enumuration;

/**
 * 订单状态枚举
 * '0:新创建未支付 1:已支付，2已发货，3已收货 4:已退款 5已完成',
 * Created by 85073 on 2018/4/1.
 */
public enum OrderStatus {
    NEW_CREATE(0,"新创建未支付"),
    PAYED(1,"已支付"),
    DELIVERY(2,"已发货"),
    GETED_GOODS(3,"已收货"),
    PAY_BACK(4,"已退款"),
    ORDER_FINISHED(5,"已完成")
    ;
    private Integer code;

    private String msg;

    OrderStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
