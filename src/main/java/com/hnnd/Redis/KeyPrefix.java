package com.hnnd.Redis;

/**
 * redis  key的前缀和超时时间
 * Created by 85073 on 2018/3/29.
 */
public interface KeyPrefix {

    public String getKeyPrefix();

    public Integer  expireSeconds();
}
