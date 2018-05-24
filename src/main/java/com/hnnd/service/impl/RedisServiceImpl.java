package com.hnnd.service.impl;

import com.hnnd.Redis.KeyPrefix;
import com.hnnd.service.RedisService;
import com.hnnd.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis 封裝
 * Created by 85073 on 2018/3/28.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public <T> boolean set(String key, T value) {
        Jedis jedis = null;
        boolean retFlag = true;
        try {
            jedis = jedisPool.getResource();
            String beanStr = BeanUtil.bean2Str(value);
            if(StringUtils.isEmpty(beanStr)) {
                retFlag = false;
            }
            jedis.set(key,beanStr);
        }catch (Exception e){
            retFlag = false;
        }finally {
            returnToPool(jedis);
        }
        return retFlag;
    }

    @Override
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        Jedis jedis = null;
        boolean retFlag = true;
        try {
            String beanStr = BeanUtil.bean2Str(value);
            if(StringUtils.isEmpty(beanStr)) {
                retFlag = false;
            }
            String realKey = prefix.getKeyPrefix()+key;
            if(prefix.expireSeconds()<=0) {//永不过期
                return set(realKey,beanStr);
            }else{
                jedis = jedisPool.getResource();
                jedis.setex(realKey,prefix.expireSeconds(),beanStr);
            }
        }catch (Exception e){
            retFlag = false;
        }finally {
            returnToPool(jedis);
        }
        return retFlag;
    }


    /**
     * 把连接对象返回到连接池中
     * @param jedis jedis 客戶端
     */
    private void returnToPool(Jedis jedis) {
        if(jedis!=null) {
            jedis.close();
        }
    }


    @Override
    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = jedis.get(key);
            return BeanUtil.string2Bean(value,clazz);
        }catch (Exception e){
            return null;
        }finally {
            returnToPool(jedis);
        }
    }

    @Override
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        String realKey = prefix.getKeyPrefix()+key;
        return get(realKey,clazz);
    }

    @Override
    public boolean isExists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        }finally {
            returnToPool(jedis);
        }
    }


    @Override
    public boolean isExists(KeyPrefix keyPrefix, String key) {
        String realKey = keyPrefix.getKeyPrefix()+key;
        return isExists(realKey);
    }

    @Override
    public  Long incr(KeyPrefix prefix, String key) {
        String realKey = prefix.getKeyPrefix()+key;
        return incr(realKey);
    }

    @Override
    public  Long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        }finally {
            returnToPool(jedis);
        }
    }

    @Override
    public Long decr(KeyPrefix prefix, String key) {
        String realKey = prefix.getKeyPrefix()+key;
        return decr(realKey);
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        }finally {
            returnToPool(jedis);
        }
    }


}
