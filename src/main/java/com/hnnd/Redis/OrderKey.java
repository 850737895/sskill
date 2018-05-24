package com.hnnd.Redis;

import com.hnnd.constants.MDA;

/**
 * Created by 85073 on 2018/3/29.
 */
public class OrderKey extends BasePrefix {

    public OrderKey(String prefix) {
        super(prefix);
    }

    public OrderKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }


    public static OrderKey generaByOrderId = new OrderKey(MDA.OBJ_CACHE_TIME, "OrderId");

    public static OrderKey generaByGoodsIdUserId = new OrderKey("GoodsIdUserId ");
}
