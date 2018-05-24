package com.hnnd.entity;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import java.util.Date;

/**
 * 秒殺用戶
 * Created by 85073 on 2018/3/30.
 */
public class SSkillUser {

    private long userId;

    private String nickName;

    private String password;

    private String salt;

    private String headImg;

    private Date registerTime;

    private Date lastLoginTime;

    private int loginCount;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public String toString() {
        return "SSkillUser{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", headImg='" + headImg + '\'' +
                ", registerTime=" + registerTime +
                ", lastLoginTime=" + lastLoginTime +
                ", loginCount=" + loginCount +
                '}';
    }
}
