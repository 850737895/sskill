package com.hnnd.util;

/**
 * Created by 85073 on 2018/3/28.
 */

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 操作map的工具类
 */
public class MapUtils {

    /**
     * 从map获取String 的value
     * @param key
     * @return
     */
    public static String getString(Map<String,Object> map, String key) {
        if(map.isEmpty()||StringUtils.isEmpty(key)) {
            return null;
        }else {
            if(map.containsKey(key)){
                return map.get(key).toString();
            }else {
                return null;
            }
        }
    }

    /**
     * 从map中获取Integer 的值
     * @param map
     * @param key
     * @return
     */
    public static Integer getInteger(Map<String,Object> map,String key) {
        if(map.isEmpty()||StringUtils.isEmpty(key)) {
            return null;
        }else {
            if(map.containsKey(key)) {
                return Integer.valueOf(map.get(key).toString());
            }else {
                return null;
            }
        }
    }

}
