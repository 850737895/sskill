package com.hnnd.exception;

import com.hnnd.enumuration.SystemCode;

/**
 * 秒杀自定义异常
 * Created by 85073 on 2018/3/30.
 */
public class SSkillException extends RuntimeException {

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SSkillException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public SSkillException(SystemCode systemCode){
        super(systemCode.getMsg());
        this.code = systemCode.getCode();
        this.msg = systemCode.getMsg();
    }
}
