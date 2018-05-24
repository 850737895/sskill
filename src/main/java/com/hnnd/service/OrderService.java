package com.hnnd.service;

import com.hnnd.entity.Order;

/**
 * 订单service
 * Created by 85073 on 2018/4/2.
 */
public interface OrderService {

    /**
     * 插入订单表
     * @param order 订单对象
     * @return 成功的行数 1成功 0失败
     */
    public int insertOrder(Order order);

    /**
     * 查询订单信息
     * @param orderId 订单id
     * @return order对象
     */
    public Order queryOrderById(String orderId);
}
