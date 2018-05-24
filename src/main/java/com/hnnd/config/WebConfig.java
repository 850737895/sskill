package com.hnnd.config;

import com.hnnd.Interceptor.LoginCheckInterceptor;
import com.hnnd.Resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 扩展Mvc功能
 * Created by 85073 on 2018/3/31.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

    @Autowired
    UserArgumentResolver userArgumentResolver;

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }

    /**
     * 注册拦截器
     * @param registry 注册器
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").
                excludePathPatterns("/login/index.html","/login/doLogin","/index.html","/demo/**");
    }


}
