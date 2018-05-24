package com.hnnd.controller;

import com.hnnd.enumuration.SystemCode;
import com.hnnd.exception.SSkillException;
import com.hnnd.service.SSKillUserService;
import com.hnnd.util.ResultVoUtils;
import com.hnnd.vo.LoginFormVo;
import com.hnnd.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by 85073 on 2018/3/30.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SSKillUserService ssKillUserServiceImpl;


    /**
     * 跳转登录页面
     * @return  ResultVo<String>
     */
    @RequestMapping("/index.html")
    public String goIndex(){
        return "index";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public ResultVo<String> doLogin(@Valid LoginFormVo loginFormVo, BindingResult bindingResult, HttpServletRequest request,HttpServletResponse response) {
        logger.info("开始登录.......................");

        //参数校验
        if(bindingResult.hasErrors()) {
            String fieldErrMsg = bindingResult.getFieldError().getDefaultMessage();
            logger.error("参数校验异常:{}",fieldErrMsg);
            throw new SSkillException(SystemCode.MOBILE_FORMART_ERROR.getCode(),fieldErrMsg);
        }
        //登录
        ResultVo resultVo = ssKillUserServiceImpl.findByLoginFormVo(request,response,loginFormVo);

        if(resultVo.getCode()!=SystemCode.SUCCESS.getCode()) {
            return ResultVoUtils.error(resultVo.getCode(),resultVo.getMsg());
        }
        return ResultVoUtils.success(resultVo.getData().toString());
    }
}
