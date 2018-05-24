package com.hnnd.service;

import com.hnnd.Redis.KeyPrefix;

/**
 * Created by 85073 on 2018/3/28.
 */
public interface RedisService {

    public <T> boolean set(String key,T value);

    public <T> boolean set(KeyPrefix prefix, String key, T value);

    public <T> T get(String key,Class<T> clazz);

    public <T> T get(KeyPrefix prefix, String key,Class<T> clazz);

    /**
     * 是否存在key
     * @param key
     * @return
     */
    public boolean isExists(String key);

    public boolean isExists(KeyPrefix keyPrefix,String key);

    public  Long incr(KeyPrefix prefix, String key);

    public  Long incr(String key);

    public  Long decr(KeyPrefix prefix, String key);

    public  Long decr(String key);
}
