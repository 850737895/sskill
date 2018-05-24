package com.hnnd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by 85073 on 2018/3/28.
 */

/**
 * 从.yml文件中读取属性
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    private String host;

    private Integer port;

    private String password;

    private Integer timeout;

    private Map<String,Object> pool;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Map<String, Object> getPool() {
        return pool;
    }

    public void setPool(Map<String, Object> pool) {
        this.pool = pool;
    }

    @Override
    public String toString() {
        return "RedisConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                ", timeout=" + timeout +
                ", pool=" + pool +
                '}';
    }
}
