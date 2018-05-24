package com.hnnd.Resolver;

import com.hnnd.constants.MDA;
import com.hnnd.entity.SSkillUser;
import com.hnnd.service.SSKillUserService;
import com.hnnd.service.impl.SSkillUserServiceImpl;
import groovy.util.logging.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 从参数中解析SSkillUser对象
 * Created by 85073 on 2018/3/31.
 */

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private SSkillUserServiceImpl ssKillUserServiceImpl;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isSupport = false;
        Class<?> clazz = parameter.getParameterType();
        if(clazz == SSkillUser.class) {
            isSupport = true;
        }
        return isSupport;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        String requestCookieValue = request.getParameter(MDA.TOKEN_NAME);
        String cookieValue = ssKillUserServiceImpl.findLoginToken(request);
        if(StringUtils.isEmpty(requestCookieValue) && StringUtils.isEmpty(cookieValue)) {
            return null;
        }
        String token = StringUtils.isEmpty(cookieValue)?requestCookieValue:cookieValue;

        return ssKillUserServiceImpl.getByToken(response,token);
    }
}
