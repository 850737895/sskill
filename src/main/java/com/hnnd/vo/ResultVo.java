package com.hnnd.vo;

/**
 * Created by 85073 on 2018/3/27.
 */

/**
 * 后端接口返回给调用方的对象
 */
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
