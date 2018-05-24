package com.hnnd.service.impl;

import com.hnnd.Redis.OrderKey;
import com.hnnd.entity.Order;
import com.hnnd.mapper.OrderMapper;
import com.hnnd.service.OrderService;
import com.hnnd.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单接口实现类
 * Created by 85073 on 2018/4/2.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisService redisServiceImpl;

    @Override
    public int insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public Order queryOrderById(String orderId) {
        //先查询缓存
        Order order = redisServiceImpl.get(OrderKey.generaByOrderId,orderId,Order.class);
        if(order!=null) {
            return order;
        }
        order = orderMapper.queryOrderById(orderId);
        if(order != null) {
            redisServiceImpl.set(OrderKey.generaByOrderId,orderId,order);
        }
        return order;
    }
}
