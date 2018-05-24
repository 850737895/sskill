package com.hnnd.enumuration;

/**
 * 渠道枚举
 * Created by 85073 on 2018/4/1.
 */
public enum ChannelType {
    PC(1,"pc电脑"),
    IOS(2,"苹果终端"),
    Andorid(3,"安卓终端")
    ;
    private Integer code;

    private String msg;

    ChannelType(Integer code, String msg) {
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
