package com.hnnd.Redis;

import com.hnnd.constants.MDA;

/**
 * Created by 85073 on 2018/3/29.
 */
public class GoodsKey extends BasePrefix {

    public GoodsKey(String prefix) {
        super(prefix);
    }

    public GoodsKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey generaGoodsList = new GoodsKey(MDA.PAGE_CACHE_TIME, "GoodsList");
    public static GoodsKey generaGoodDetail = new GoodsKey(MDA.PAGE_CACHE_TIME, "GoodDetail");
    public static GoodsKey generaGoodVo = new GoodsKey(MDA.OBJ_CACHE_TIME, "GoodVo");

    /**
     * 加载到缓存中的秒杀商品的数量
     */
    public static GoodsKey GoodsId2Count = new GoodsKey("GoodsId2Count");

    public static GoodsKey GoodsIsNotStock = new GoodsKey("GoodsIsNotStock");




}
