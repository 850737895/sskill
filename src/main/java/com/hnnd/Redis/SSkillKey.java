package com.hnnd.Redis;

import com.hnnd.constants.MDA;

/**
 * Created by 85073 on 2018/3/29.
 */
public class SSkillKey extends BasePrefix {

    public SSkillKey(String prefix) {
        super(prefix);
    }

    public SSkillKey(Integer expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }


    public static SSkillKey generaRandomStr = new SSkillKey(MDA.OBJ_CACHE_TIME, "randomStr");

}
