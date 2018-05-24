package com.hnnd.controller;

import com.hnnd.Redis.UserKey;
import com.hnnd.config.RedisConfig;
import com.hnnd.entity.SSkillUser;
import com.hnnd.entity.UserDemo;
import com.hnnd.service.DemoService;
import com.hnnd.service.MQProducer;
import com.hnnd.service.RedisService;
import com.hnnd.util.ResultVoUtils;
import com.hnnd.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 85073 on 2018/3/27.
 */

@Controller
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    DemoService demoServiceImpl;

    @Autowired
    RedisService redisServiceImpl;
    @Autowired
    private MQProducer mqProducerImpl;

    @RequestMapping("/success")
    @ResponseBody
    public ResultVo<String> success() {
        return ResultVoUtils.success("你好");
    }

    @RequestMapping("/error")
    @ResponseBody
    public ResultVo<String> error() {
        return ResultVoUtils.error();
    }

    /**
     * 测试thymeleaf
     * @param model
     * @return
     */
    @RequestMapping("/index.html")
    public String testHtml(Model model){
        model.addAttribute("name","朱伟");
        return "index";
    }

    /**
     * 测试连接数据库
     * @param id
     * @return
     */
    @RequestMapping("/testDb/{id}")
    @ResponseBody
    public ResultVo<UserDemo> testDb(@PathVariable("id") Integer id) {
        return ResultVoUtils.success(demoServiceImpl.qryUserDemo(id));
    }

    /**
     * 测试事物
     * @return
     */
    @RequestMapping("/testTx")
    @ResponseBody
    public ResultVo<Boolean> testTx() {
        boolean flag = true;
        try {
            demoServiceImpl.testTx();
        }catch (Exception e) {
            flag = false;
        }
        return ResultVoUtils.success(flag);
    }

    @RequestMapping("/redisGet/{key}")
    @ResponseBody
    public ResultVo<String> testRedisGet(@PathVariable("key") String key){
        String value = redisServiceImpl.get(key,String.class);
        return ResultVoUtils.success(value);
    }

    @RequestMapping("/redisGetWithPrefix/{key}")
    @ResponseBody
    public ResultVo<String> testRedisGetWithPrefix(@PathVariable("key") String key){
        String value = redisServiceImpl.get(UserKey.generaById,key,String.class);
        return ResultVoUtils.success(value);
    }

    @RequestMapping("/redisGetBeanWithPrefix/{key}")
    @ResponseBody
    public ResultVo<UserDemo> testRedisGetBean(@PathVariable("key") String key){
        UserDemo value = redisServiceImpl.get(UserKey.generaById,key,UserDemo.class);
        logger.info("UserDemo:{}",value);
        return ResultVoUtils.success(value);
    }

    @RequestMapping("/redisSetWithPrefix")
    @ResponseBody
    public ResultVo<Boolean> testRedisSetPrefix(){
        UserDemo userDemo = demoServiceImpl.qryUserDemo(1);
        boolean flag = redisServiceImpl.set(UserKey.generaById,"userDemo",userDemo);
        return ResultVoUtils.success(flag);
    }

    @RequestMapping("/userInfo")
    @ResponseBody
    public ResultVo<SSkillUser> testSSKill(Model model,SSkillUser sSkillUser) {
        return  ResultVoUtils.success(sSkillUser);
    }

    @RequestMapping("/testMq")
    @ResponseBody
    public ResultVo testMq(){
        mqProducerImpl.sendMsgToQueue("hello rabbitmq .....");
        return ResultVoUtils.success();
    }

    /**
     * 测试直接交换机模式
     * @return ResultVo
     */
    @RequestMapping("/testDirectModel")
    @ResponseBody
    public ResultVo testDirectModel(){
        mqProducerImpl.sendMsgToDirectQueue("hello rabbitmq ..for  DirectModel...");
        return ResultVoUtils.success("testDirectModel");
    }

    @RequestMapping("/testFanoutModel")
    @ResponseBody
    public ResultVo testFanoutModel(){
        mqProducerImpl.sendMsgToFanoutQueue("hello rabbitmq ..for  FanoutModel...");
        return ResultVoUtils.success("testFanoutModel");
    }

    @RequestMapping("/testTopicModel")
    @ResponseBody
    public ResultVo testTopicModel(){
        mqProducerImpl.sendMsgToTopicQueue("hello rabbitmq ..for  topicModel...");
        return ResultVoUtils.success("testTopicModel");
    }

    @RequestMapping("/testHeaderModel")
    @ResponseBody
    public ResultVo testHeaderModel(){
        mqProducerImpl.sendMsgToHeaderQueue("hello rabbitmq ..for  HeaderModel...");
        return ResultVoUtils.success("testHeaderModel");
    }

    /**
     * 重置环境
     * @return
     */
    @RequestMapping("/reset")
    @ResponseBody
    public ResultVo resetSource() {

        return ResultVoUtils.success();
    }

}
