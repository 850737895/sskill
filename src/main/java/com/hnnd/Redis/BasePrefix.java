package com.hnnd.Redis;

/**
 * Created by 85073 on 2018/3/29.
 */
public abstract class BasePrefix implements KeyPrefix {

    private String prefix;

    private Integer expireSecends;

    public BasePrefix(String prefix) {
        this(0,prefix);
    }

    public BasePrefix(Integer  expireSeconds, String prefix) {
        this.prefix = prefix;
        this.expireSecends = expireSeconds;
    }

    public Integer  expireSeconds() {//默认0代表永不过期
        return expireSecends;
    }

    public String getKeyPrefix() {
        String className = getClass().getSimpleName();
        return className+":" + prefix;
    }
}
