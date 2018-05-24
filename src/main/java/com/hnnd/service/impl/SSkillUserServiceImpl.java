package com.hnnd.service.impl;

import com.hnnd.Redis.UserKey;
import com.hnnd.constants.MDA;
import com.hnnd.mapper.SSKillUserMapper;
import com.hnnd.entity.SSkillUser;
import com.hnnd.enumuration.SystemCode;
import com.hnnd.exception.SSkillException;
import com.hnnd.service.RedisService;
import com.hnnd.service.SSKillUserService;
import com.hnnd.util.MD5Utils;
import com.hnnd.util.ResultVoUtils;
import com.hnnd.util.TokenUtils;
import com.hnnd.vo.LoginFormVo;
import com.hnnd.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * user 业务层
 * Created by 85073 on 2018/3/30.
 */
@Service
public class SSkillUserServiceImpl implements SSKillUserService{

    private static final Logger logger = LoggerFactory.getLogger(SSkillUserServiceImpl.class);


    @Autowired
    SSKillUserMapper ssKillUserMapper;
    @Autowired
    RedisService redisServiceImpl;

    /**
     * 优化对象缓存
     * @param request
     * @param response
     * @param loginFormVo 入参
     * @return
     */
    @Override
    public ResultVo findByLoginFormVo(HttpServletRequest request,HttpServletResponse response, LoginFormVo loginFormVo) {
        String mobile = loginFormVo.getMobile();

        SSkillUser sSkillUser = getById(Long.valueOf(mobile));
        if (sSkillUser == null) {
            logger.error("该手机用户没有注册手机号:{}",mobile);
            throw new SSkillException(SystemCode.MOBILE_NOT_REG);
        }
        //对loginFormPassword二次加密
        String loginFormVoPassword= loginFormVo.getPassword();
        String salt = sSkillUser.getSalt();
        String saltPassword = MD5Utils.secondMd5(loginFormVoPassword,salt);

        //密码比对
        String dbPassword = sSkillUser.getPassword();
        if(!saltPassword.equals(dbPassword)) {
            logger.error("密码错误");
            throw new SSkillException(SystemCode.PASSWORD_ERROR);
        }
        //判断是否登录过  没登录过直接写redis
        String loginToken = findLoginToken(request);
        if(!StringUtils.isEmpty(loginToken)){//首次登录
            writeCookie(response,sSkillUser,loginToken);
            return ResultVoUtils.success(loginToken);
        }
        String token = TokenUtils.generatorToken();
        writeCookie(response,sSkillUser,token);
        return ResultVoUtils.success(token);
    }

    /**
     * 实现分布式session
     * @param response
     */
    private void writeCookie(HttpServletResponse response,SSkillUser sSkillUser,String token) {
        redisServiceImpl.set(UserKey.generaByToken,token,sSkillUser);
        Cookie cookie = new Cookie(MDA.TOKEN_NAME,token);
        cookie.setMaxAge(MDA.SESSION_EXPIRE_TIME);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 判断是否登录
     * @return
     */
    public String findLoginToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            return null;
        }
        String loginToken = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(MDA.TOKEN_NAME)) {
                String cookieValue = cookie.getValue();
                SSkillUser sSkillUser = redisServiceImpl.get(UserKey.generaByToken,cookieValue,SSkillUser.class);
                if(sSkillUser != null) {
                    loginToken = cookieValue;
                }
                break;
            }
        }
        return loginToken;
    }

    /**
     *
     * @param response response
     * @param token token
     * @return SSkillUser
     */
    public SSkillUser getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        SSkillUser sSkillUser = redisServiceImpl.get(UserKey.generaByToken,token,SSkillUser.class);
        //延长token的有效期
        if (sSkillUser != null) {
            writeCookie(response,sSkillUser,token);
        }
        return sSkillUser;
    }

    @Override
    public SSkillUser getById(long id) {
        SSkillUser sSkillUser = redisServiceImpl.get(UserKey.generaById,id+"",SSkillUser.class);
        if(sSkillUser != null) {
            return sSkillUser;
        }
        sSkillUser =  ssKillUserMapper.findbyId(id);
        if(sSkillUser!=null) {
            redisServiceImpl.set(UserKey.generaById,id+"",sSkillUser);
        }
        return sSkillUser;
    }

    /**
     *
     * @param token token
     * @param id  用户id
     * @param formPassword  新密码
     * @return boolean 更新是否成功
     */
    public boolean updateSkillUserPassword(String token,long id,String formPassword) {
        SSkillUser skillUser = getById(id);
        if(skillUser == null) {
            throw new SSkillException(SystemCode.MOBILE_NOT_REG);
        }
        SSkillUser updateUser = new SSkillUser();
        updateUser.setUserId(id);
        String newPassword = MD5Utils.secondMd5(formPassword,skillUser.getSalt());
        updateUser.setPassword(newPassword);

        int updateFlag = ssKillUserMapper.updateById(updateUser);
        //更新token对应的缓存
        skillUser.setPassword(newPassword);
        redisServiceImpl.set(UserKey.generaByToken,token,skillUser);
        //更新id对应的缓存
        redisServiceImpl.set(UserKey.generaById,id+"",skillUser);
        return updateFlag>0;
    }
}
