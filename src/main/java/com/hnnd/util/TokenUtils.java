package com.hnnd.util;

import java.util.UUID;

/**
 * token 工具
 * Created by 85073 on 2018/3/31.
 */
public class TokenUtils {

    /**
     * 生成随机的token
     * @return 随机token值
     */
    public static String generatorToken(){
        return UUID.randomUUID().toString().replace("-","");
    }
}


