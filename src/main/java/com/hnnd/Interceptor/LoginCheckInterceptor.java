package com.hnnd.Interceptor;

import com.hnnd.constants.MDA;
import com.hnnd.service.impl.SSkillUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 检查用户是否登录拦截器
 * Created by 85073 on 2018/4/5.
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private SSkillUserServiceImpl sSkillUserServiceImpl;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestCookieValue = httpServletRequest.getParameter(MDA.TOKEN_NAME);
        String cookieValue = sSkillUserServiceImpl.findLoginToken(httpServletRequest);

        if(StringUtils.isEmpty(requestCookieValue) && StringUtils.isEmpty(cookieValue)) {
            //重定向到登录页面
            httpServletRequest.getRequestDispatcher("/login/index.html").forward(httpServletRequest,httpServletResponse);
        }else {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
