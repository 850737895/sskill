package com.hnnd.controller;

import com.hnnd.Redis.GoodsKey;
import com.hnnd.entity.Order;
import com.hnnd.entity.SSkillUser;
import com.hnnd.enumuration.SystemCode;
import com.hnnd.exception.SSkillException;
import com.hnnd.service.MQProducer;
import com.hnnd.service.RedisService;
import com.hnnd.service.SSKillService;
import com.hnnd.service.SSkillGoodsService;
import com.hnnd.util.ResultVoUtils;
import com.hnnd.vo.GoodsVo;
import com.hnnd.vo.ResultVo;
import com.hnnd.vo.SkillOrderMsgBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 秒杀controller
 * Created by 85073 on 2018/4/1.
 */
@Controller
@RequestMapping("/sskill")
public class SSkillController implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(SSkillController.class);

    @Autowired
    private SSKillService ssKillServiceImpl;

    @Autowired
    private SSkillGoodsService sSkillGoodsServiceImpl;

    @Autowired
    private RedisService redisServiceImpl;
    @Autowired
    private MQProducer mqProducerImpl;

    /**
     * 内存标记 减少redis的访问
     */
    private ConcurrentMap<String, Boolean> isNotStock = new ConcurrentHashMap<>();

    /**
     * 秒杀
     * @return orderDetail
     */
    @RequestMapping("/doSkill")
    public String doSSkill(SSkillUser skillUser, @RequestParam("sskillGoodsId") long sskillGoodsId,Model model) {
        //判断库存
        logger.info("开始秒杀");
        GoodsVo goodsVo = sSkillGoodsServiceImpl.queryGoodsVoDetails(sskillGoodsId);
        if(goodsVo == null) {
            model.addAttribute("errMsg", SystemCode.SSKILL_GOODS_NOT_EXISTS.getMsg());
            return "sskillFail";
        }
        if(goodsVo.getSskillStockCount()<=0) {
            model.addAttribute("errMsg", SystemCode.SSKILL_GOODS_NOT_STOCK.getMsg());
            return "sskillFail";
        }

        //判断该用户是否秒杀过
        if((!ssKillServiceImpl.isSkilled(skillUser.getUserId(),sskillGoodsId))) {
            model.addAttribute("errMsg", SystemCode.DO_NOT_REPEAT_SKILL.getMsg());
            return "sskillFail";
        }
        Order order = ssKillServiceImpl.doSkill(skillUser,goodsVo);
        model.addAttribute("orderInfo",order);
        model.addAttribute("goodsVo",goodsVo);
        //开启秒杀
        return "orderDetail";
    }

    /**
     * 前后端分离的秒杀
     * @return orderDetail
     */
    @RequestMapping("/doSkill2")
    @ResponseBody
    public ResultVo<String> doSSkill2(SSkillUser skillUser, @RequestParam("sskillGoodsId") long sskillGoodsId) {

        //判断库存
        GoodsVo goodsVo = sSkillGoodsServiceImpl.queryGoodsVoDetails(sskillGoodsId);
        if(goodsVo == null) {
            throw new SSkillException(SystemCode.SSKILL_GOODS_NOT_EXISTS);
        }
        if(goodsVo.getSskillStockCount()<=0) {
            throw new SSkillException(SystemCode.SSKILL_GOODS_NOT_STOCK);
        }

        //判断该用户是否秒杀过
        if((ssKillServiceImpl.isSkilled(skillUser.getUserId(),sskillGoodsId))) {
            throw new SSkillException(SystemCode.DO_NOT_REPEAT_SKILL);
        }
        Order order = ssKillServiceImpl.doSkill(skillUser,goodsVo);
        //开启秒杀
        return ResultVoUtils.success(order.getOrderId());
    }

    /**
     * 加入rabbitmq异步下单
     * @return orderDetail
     */
    @RequestMapping("/doSkill3/{randomStr}")
    @ResponseBody
    public ResultVo<SystemCode> doSSkill3(SSkillUser skillUser, @RequestParam("sskillGoodsId") long sskillGoodsId,
                                          @PathVariable("randomStr") String randomStr) {
        if(!ssKillServiceImpl.checkRealAddr(skillUser.getUserId(),sskillGoodsId,randomStr)){
            throw new SSkillException(SystemCode.ILLEGAL_REQUEST);
        }
        //加入内存标记  减少redis的访问
        boolean isNoStockFlag = isNotStock.get(sskillGoodsId+"");
        if(isNoStockFlag){//没有库存
            throw new SSkillException(SystemCode.SSKILL_GOODS_NOT_STOCK);
        }

        //判断是否重复秒杀
        if((ssKillServiceImpl.isSkilled(skillUser.getUserId(),sskillGoodsId))) {
            logger.warn("用户:{}进行了重复秒杀商品{}",skillUser.getUserId(),sskillGoodsId);
            throw new SSkillException(SystemCode.DO_NOT_REPEAT_SKILL);
        }
        //预减库存
        long remainStock = redisServiceImpl.decr(GoodsKey.GoodsId2Count,sskillGoodsId+"");
        if(remainStock<0) {
            isNotStock.put(sskillGoodsId+"",true);
            logger.warn("商品已经秒杀完,库存不足.......");
            throw new SSkillException(SystemCode.SSKILL_GOODS_NOT_STOCK);
        }

        //写入消息队列,进行异步生单,返回排队中
        SkillOrderMsgBo skillOrderMsgBo = new SkillOrderMsgBo();
        skillOrderMsgBo.setSsKillUser(skillUser);
        skillOrderMsgBo.setSskillGoodsId(sskillGoodsId);
        mqProducerImpl.sendSkillMsgToSkillOrder(skillOrderMsgBo);

        return ResultVoUtils.success(SystemCode.ORDER_IN_QUEUE);
    }

    /**
     * 轮询查询秒杀商品情况
     * 成功:返回订单id
     * 排队中:3001
     * 库存不足 :2002
     * @param skillUser 用户信息
     * @param sskillGoodsId 商品id
     * @return ResultVo<String>
     */
    @GetMapping("/querySkillResult")
    @ResponseBody
    public ResultVo<String > querySkillResult(SSkillUser skillUser, @RequestParam("sskillGoodsId") long sskillGoodsId) {
        String resultCode = ssKillServiceImpl.querySkillResult(skillUser.getUserId(),sskillGoodsId);
        return ResultVoUtils.success(resultCode);
    }

    /**
     * 获取秒杀真正地址
     * @param skillUser 用户
     * @param sskillGoodsId 商品Id
     * @return  秒杀地址中的随机字符串
     */
    @GetMapping("/getRealSkillAddr")
    @ResponseBody
    public ResultVo<String> getRealSkillAddr(SSkillUser skillUser, @RequestParam("sskillGoodsId") long sskillGoodsId){
        String randomStr = ssKillServiceImpl.generatorRandomStr(skillUser.getUserId(),sskillGoodsId);
        return ResultVoUtils.success(randomStr);
    }

        /**
         * 容器初始化 回调  用来加载秒杀商品到redis
         * @throws Exception 异常
         */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsVoList = sSkillGoodsServiceImpl.queryGoodsVoList();
        for (GoodsVo goodsVo: goodsVoList ) {
            redisServiceImpl.set(GoodsKey.GoodsId2Count,goodsVo.getId()+"",goodsVo.getSskillStockCount());
            isNotStock.put(goodsVo.getId()+"",false);
        }

    }
}
