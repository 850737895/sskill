package com.hnnd.Handler;

import com.hnnd.enumuration.SystemCode;
import com.hnnd.exception.SSkillException;
import com.hnnd.util.ResultVoUtils;
import com.hnnd.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * Created by 85073 on 2018/3/30.
 */

@ControllerAdvice
@ResponseBody
public class SSkillExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(SSkillExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)

    public ResultVo exceptionHandler(HttpServletRequest request, Exception e){
        logger.info("全局异常拦截器拦截异常:{}",e.getMessage());
        if(e instanceof SSkillException) {
            SSkillException sSkillException = (SSkillException) e;
            logger.error("异常信息:{}",sSkillException.getMsg());
            return ResultVoUtils.error(sSkillException.getCode(),sSkillException.getMsg());
        }else {
            e.printStackTrace();
            return ResultVoUtils.error(SystemCode.SYSTEM_ERROR);
        }

    }
}
