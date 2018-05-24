package com.hnnd.Redis;

import com.hnnd.constants.MDA;

/**
 * Created by 85073 on 2018/3/29.
 */
public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public UserKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKey generaById = new UserKey("id");
    public static UserKey generaByName = new UserKey("name");
    public static UserKey generaByToken = new UserKey(MDA.SESSION_EXPIRE_TIME, MDA.TOKEN_NAME);
}
