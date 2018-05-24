package com.hnnd.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

/**加强的beanUtils
 * Created by 85073 on 2018/3/28.
 */
public class BeanUtil extends BeanUtils {

    /**
     * bean 转为jsonstr
     * @param value 需要转化的bean
     * @param <T> bean的类型
     * @return bean转换后的jsonstr
     */
    @SuppressWarnings("unchecked")
    public static  <T> String bean2Str(T value) {
        if(value == null) {
            return null;
        }else{
            Class<?> clazz = value.getClass();
            if(clazz == Integer.class || clazz == int.class||clazz == Long.class) {
                return value+"";
            }else if(clazz == String.class) {
                return (String)value;
            }else if(clazz == Double.class ||clazz == Float.class) {
                return String.valueOf(value);
            }else{
                return JSON.toJSONString(value);
            }
        }
    }

    /**
     * 把jsonStr转为bean
     * @param value jsonStr
     * @param clazz bean的class
     * @param <T>  bean的类型
     * @return bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T string2Bean(String value, Class<T> clazz) {
        if(StringUtils.isEmpty(value)||clazz == null) {
            return null;
        }else{
            if(clazz == Integer.class || clazz == int.class) {
                return (T) Integer.valueOf(value);
            }else if(clazz == String.class) {
                return (T) value;
            }else if(clazz == Double.class ) {
                return (T) Double.valueOf(value);
            }else if(clazz == Float.class){
                return (T) Float.valueOf(value);
            }else if(clazz == Long.class){
                return (T) Long.valueOf(value);
            }else {
                return JSON.toJavaObject(JSON.parseObject(value),clazz);
            }
        }
    }

}
