package com.hnnd.service;

import com.hnnd.entity.SSkillUser;
import com.hnnd.vo.LoginFormVo;
import com.hnnd.vo.ResultVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 85073 on 2018/3/30.
 */
public interface SSKillUserService {

    /**
     * 通过loginFormVo查询用户进行登录
     * @param loginFormVo 入参
     * @return ResultVo
     */
     ResultVo findByLoginFormVo(HttpServletRequest request,HttpServletResponse response, LoginFormVo loginFormVo);

    /**
     * 通过token 获取redis 的redis对象
     * @param response response对象
     * @param token token
     * @return 用户对象
     */
     public SSkillUser getByToken(HttpServletResponse response, String token);

    /**
     * 通过id 查询用户信息
     * @param id 用户id
     * @return  用户对象
     */
     public SSkillUser getById(long id);

    /**
     * 更新用户密码
     * @param token token 对象
     * @param id 用户Id
     * @param formPassword 新密码
     * @return 更新是否成功
     */
     public boolean updateSkillUserPassword(String token,long id,String formPassword);
}
