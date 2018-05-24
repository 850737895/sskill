package com.hnnd.service.impl;

import com.hnnd.Redis.GoodsKey;
import com.hnnd.Redis.OrderKey;
import com.hnnd.Redis.SSkillKey;
import com.hnnd.constants.MDA;
import com.hnnd.entity.Order;
import com.hnnd.entity.SSkillGoods;
import com.hnnd.entity.SSkillOrder;
import com.hnnd.entity.SSkillUser;
import com.hnnd.enumuration.ChannelType;
import com.hnnd.enumuration.OrderStatus;
import com.hnnd.enumuration.SystemCode;
import com.hnnd.exception.SSkillException;
import com.hnnd.mapper.SSkillOrderMapper;
import com.hnnd.service.*;
import com.hnnd.util.KeyUtil;
import com.hnnd.util.MD5Utils;
import com.hnnd.util.TokenUtils;
import com.hnnd.vo.GoodsVo;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 秒杀service的实现
 * Created by 85073 on 2018/4/1.
 */
@Service
public class SSkillServiceImpl  implements SSKillService {

    @Autowired
    private SSkillGoodsService sSkillGoodsServiceImpl;

    @Autowired
    private SSkillOrderService sSkillOrderServiceImpl;

    @Autowired
    private OrderService orderServiceImpl;

    @Autowired
    private RedisService redisServiceImpl;

    @Override
    public boolean hasRemainStock(long sskillGoodsId) {
        GoodsVo goodsVo = sSkillGoodsServiceImpl.queryGoodsVoDetails(sskillGoodsId);
        boolean hasRemainFlag = true;
        if(goodsVo == null ) {
            throw new SSkillException(SystemCode.SSKILL_GOODS_NOT_EXISTS);
        }
        if(goodsVo.getSskillStockCount()<=0) {
            hasRemainFlag = false;
        }
        return hasRemainFlag;
    }

    @Override
    public boolean isSkilled(long skillUserId, long sskillGoodsId) {
        boolean isSkilled = false;
        //直接查询缓存
        SSkillOrder sSkillOrder = redisServiceImpl.get(OrderKey.generaByGoodsIdUserId,sskillGoodsId+skillUserId+"",SSkillOrder.class);
        if(sSkillOrder != null) {
            isSkilled = true;
        }
        return isSkilled;
    }

    @Override
    @Transactional
    public  Order doSkill(SSkillUser sSkillUser, GoodsVo goodsVo) {

        boolean flag = sSkillGoodsServiceImpl.updateSSkillGoodsStock(goodsVo);
        if(flag) {
           return createOrder(sSkillUser,goodsVo);
        }else {
            //在redis中插入商品秒杀完的标识
            setGoodsOver(goodsVo.getId());
            return null;
        }

    }

    @Override
    public String querySkillResult(long skillUserId, long skillGoodsId) {
        SSkillOrder skillOrder = redisServiceImpl.get(OrderKey.generaByGoodsIdUserId,skillGoodsId+skillUserId+"",SSkillOrder.class);
        if(skillOrder !=null) {
            //秒杀成功
            return skillOrder.getOrderId();
        }else{
            if(getGoodsOver(skillGoodsId)){//秒杀库存不足
                return SystemCode.SSKILL_GOODS_NOT_STOCK.getCode()+"";
            }else{
                return SystemCode.ORDER_IN_QUEUE.getCode()+"";
            }
        }
    }

    @Override
    public String generatorRandomStr(long skillUserId, long skillGoodsId) {
        if(StringUtils.isEmpty(skillUserId)){
            throw new SSkillException(SystemCode.SSKILL_GOODS_NOT_EXISTS);
        }
        String randomStr = MD5Utils.md5(TokenUtils.generatorToken()+MDA.RANDOM_STR_PASS);
        redisServiceImpl.set(SSkillKey.generaRandomStr,skillUserId+":"+skillGoodsId,randomStr);
        return randomStr;
    }

    @Override
    public boolean checkRealAddr(long skillUserId, long skillGoodsId, String clientRandomStr) {
        if(StringUtils.isEmpty(skillUserId)) {
            throw new SSkillException(SystemCode.USER_NOT_LOGIN);
        }
        if(StringUtils.isEmpty(skillGoodsId)) {
            throw new SSkillException(SystemCode.GOODS_NOT_EXISTS);
        }
        if(StringUtils.isEmpty(clientRandomStr)) {
            throw new SSkillException(SystemCode.ILLEGAL_REQUEST);
        }
        String serverRandomStr = redisServiceImpl.get(SSkillKey.generaRandomStr,skillUserId+":"+skillGoodsId,String.class);
        if(serverRandomStr.equals(clientRandomStr)) {
            return true;
        }
        return false;
    }


    /**
     * 在redis中标志 skillGoodsId商品已经秒杀完
     * @param skillGoodsId 商品id
     */
    private void setGoodsOver(Long skillGoodsId) {
        redisServiceImpl.set(GoodsKey.GoodsIsNotStock, ""+skillGoodsId, true);
    }

    private boolean getGoodsOver(long goodsId) {
        return redisServiceImpl.isExists(GoodsKey.GoodsIsNotStock, ""+goodsId);
    }

    @Transactional
    private Order createOrder(SSkillUser sSkillUser, GoodsVo goodsVo){
        //插入订单表
        String orderId = KeyUtil.genUniqueKey();

        Order order = new Order();
        order.setOrderId(orderId);
        order.setCreateDate(new Date());
        order.setGoodsCount(MDA.SSKILL_COUNT);
        order.setGoodsName(goodsVo.getGoodsName());
        order.setGoodsId(goodsVo.getId());
        order.setGoodsPrice(goodsVo.getSskillPrice());
        order.setOrderChannel(ChannelType.PC.getCode());
        order.setOrderAddress(MDA.DELIVRY_ADDERSS);
        order.setOrderStatus(OrderStatus.NEW_CREATE.getCode());
        order.setPayDate(new Date());
        order.setUserId(sSkillUser.getUserId());
        orderServiceImpl.insertOrder(order);

        //插入秒杀订单表
        SSkillOrder sSkillOrder = new SSkillOrder();
        sSkillOrder.setOrderId(orderId);
        sSkillOrder.setSskillGoodsId(goodsVo.getId());
        sSkillOrder.setUserId(sSkillUser.getUserId());
        sSkillOrderServiceImpl.insertSSkillOrder(sSkillOrder);

        //把订单写入缓存  给秒杀成功后 前后端分离 发送请求查询一次order
        redisServiceImpl.set(OrderKey.generaByOrderId,orderId,order);
        redisServiceImpl.set(OrderKey.generaByGoodsIdUserId,goodsVo.getId()+sSkillUser.getUserId()+"",sSkillOrder);
        return order;
    }
}
