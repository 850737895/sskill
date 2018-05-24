package com.hnnd.config;

import com.hnnd.util.MapUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 配置
 * Created by 85073 on 2018/3/28.
 */


@Component
public class JedisPoolCfg {

    @Autowired
    private  RedisConfig redisConfig;

    @Bean
    public JedisPool jedisPool(){

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(MapUtils.getInteger(redisConfig.getPool(),"max-active"));
        poolConfig.setMaxIdle(MapUtils.getInteger(redisConfig.getPool(),"max-idle"));
        poolConfig.setMinIdle(MapUtils.getInteger(redisConfig.getPool(),"min-idle"));
        JedisPool jedisPool = new  JedisPool(poolConfig,redisConfig.getHost(), redisConfig.getPort(),
                                            redisConfig.getTimeout()*1000, redisConfig.getPassword(), 0);
        return jedisPool;
    }

}
