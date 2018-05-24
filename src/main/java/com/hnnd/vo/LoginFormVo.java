package com.hnnd.vo;

import com.hnnd.annotation.IsMobile;

import javax.validation.constraints.NotNull;

/**
 * 登录表单vo
 * Created by 85073 on 2018/3/30.
 */
public class LoginFormVo {

    @IsMobile
    @NotNull
    private String mobile;

    @NotNull
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
