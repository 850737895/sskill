package com.hnnd.util;

import com.hnnd.Redis.GoodsKey;
import com.hnnd.Redis.KeyPrefix;
import com.hnnd.service.RedisService;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * thymeleaf模版引擎工具
 * Created by 85073 on 2018/4/3.
 */
public class ThymeleafUtil {

    /**
     * 页面级别缓存工具
     * @param thymeleafViewResolver  模版解析器
     * @param request  request对象
     * @param response response对象
     * @param model modeal对象
     * @return 渲染的html
     */
    public static String pageCacheHtml(ThymeleafViewResolver thymeleafViewResolver, HttpServletRequest request, HttpServletResponse response, Model model,String pageUri){
        WebContext webContext = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        //加载模版
        String html = thymeleafViewResolver.getTemplateEngine().process(pageUri,webContext);
        return html;
    }

    public static String urlCacheHtml(ThymeleafViewResolver thymeleafViewResolver, HttpServletRequest request, HttpServletResponse response, Model model,String pageUrl){
        WebContext webContext = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        //加载模版
        String html = thymeleafViewResolver.getTemplateEngine().process(pageUrl,webContext);
        return html;
    }
}
