package com.hnnd.enumuration;

/**
 * 秒杀状态
 * Created by 85073 on 2018/4/4.
 */
public enum SSkillStatus {

    SSKILL_NOT_BEGIN(0,"秒杀还没有开始"),

    SSKILL_IN_TIME(1,"秒杀进行中"),

    SSKILL_IS_OVER(2,"秒杀结束")

    ;
    private Integer code;

    private String msg;

    SSkillStatus(Integer code, String msg) {
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
