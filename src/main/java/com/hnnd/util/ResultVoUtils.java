package com.hnnd.util;

/**
 * Created by 85073 on 2018/3/27.
 */

import com.hnnd.enumuration.SystemCode;
import com.hnnd.vo.ResultVo;

import javax.xml.transform.Result;

/**
 * 处理ResultVo对象的工具类
 */
public class ResultVoUtils {

    /**
     * 带有 data节点success
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> success(T data){
        ResultVo<T> resultVo = new ResultVo<T>();
        resultVo.setCode(SystemCode.SUCCESS.getCode());
        resultVo.setMsg(SystemCode.SUCCESS.getMsg());
        resultVo.setData(data);
        return resultVo;
    }

    /**
     * 带有 data节点success
     * @param systemCode
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> success(SystemCode systemCode){
        ResultVo<T> resultVo = new ResultVo<T>();
        resultVo.setCode(systemCode.getCode());
        resultVo.setMsg(systemCode.getMsg());
        resultVo.setData(null);
        return resultVo;
    }

    /**
     * data节点为null
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> success(){
        ResultVo<T> resultVo = new ResultVo<T>();
        resultVo.setCode(SystemCode.SUCCESS.getCode());
        resultVo.setMsg(SystemCode.SUCCESS.getMsg());
        resultVo.setData(null);
        return resultVo;
    }

    /**
     * 返回失败的节点
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> error() {
        ResultVo<T> resultVo = new ResultVo<T>();
        resultVo.setCode(SystemCode.ERROR.getCode());
        resultVo.setMsg(SystemCode.ERROR.getMsg());
        return resultVo;
    }

    /**
     * 返回失败的节点
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> error(Integer code,String msg) {
        ResultVo<T> resultVo = new ResultVo<T>();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }

    public static <T> ResultVo<T> error(SystemCode systemCode) {
        ResultVo<T> resultVo = new ResultVo<T>();
        resultVo.setCode(systemCode.getCode());
        resultVo.setMsg(systemCode.getMsg());
        return resultVo;
    }

}
