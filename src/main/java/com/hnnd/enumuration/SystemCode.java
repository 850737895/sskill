package com.hnnd.enumuration;

/**
 * Created by 85073 on 2018/3/27.
 */

/**
 * 系统编码定义
 */
public enum SystemCode {
    SUCCESS(0,"成功"),
    ERROR(-1,"失败"),

    //用户模块
    MOBILE_IS_EMPTY(1001,"手机号码为空"),
    PASSWORD_IS_EMPTY(1002,"密码为空"),
    MOBILE_FORMART_ERROR(1003,"手机格式不正确"),
    MOBILE_NOT_REG(1004,"手机未注册"),
    PASSWORD_ERROR(1005,"密码错误"),
    USER_NOT_LOGIN(1006,"用户没有登录"),



    SYSTEM_ERROR(10000,"系统运行错误"),

    //商品模块
    GOODS_NOT_EXISTS(2000,"商品不存在"),
    SSKILL_GOODS_NOT_EXISTS(2001,"秒杀商品不存在"),
    SSKILL_GOODS_NOT_STOCK(2002,"秒杀商品库存不足"),
    DO_NOT_REPEAT_SKILL(2003,"不能多次秒杀同商品"),
    ILLEGAL_REQUEST(2004,"不是正确的请求"),
    //订单模块
    ORDER_NOT_EXIST(3000,"订单不存在"),
    ORDER_IN_QUEUE(3001,"订单排队中......")
    ;

    private Integer code;

    private String msg;

    SystemCode(Integer code, String msg) {
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
