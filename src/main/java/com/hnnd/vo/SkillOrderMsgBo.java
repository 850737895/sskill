package com.hnnd.vo;

import com.hnnd.entity.SSkillUser;

/**
 * 写入到mq中的消息对象
 * Created by 85073 on 2018/4/6.
 */
public class SkillOrderMsgBo {

    private SSkillUser ssKillUser;

    private long sskillGoodsId;

    public SSkillUser getSsKillUser() {
        return ssKillUser;
    }

    public void setSsKillUser(SSkillUser ssKillUser) {
        this.ssKillUser = ssKillUser;
    }

    public long getSskillGoodsId() {
        return sskillGoodsId;
    }

    public void setSskillGoodsId(long sskillGoodsId) {
        this.sskillGoodsId = sskillGoodsId;
    }
}
