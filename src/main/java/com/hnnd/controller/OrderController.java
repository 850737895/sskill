package com.hnnd.controller;

import com.hnnd.entity.Order;
import com.hnnd.entity.SSkillUser;
import com.hnnd.enumuration.SystemCode;
import com.hnnd.exception.SSkillException;
import com.hnnd.service.OrderService;
import com.hnnd.service.SSkillGoodsService;
import com.hnnd.util.ResultVoUtils;
import com.hnnd.vo.GoodsVo;
import com.hnnd.vo.OrderDeailVo;
import com.hnnd.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单controller
 * Created by 85073 on 2018/4/4.
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderServiceImpl;

    @Autowired
    private SSkillGoodsService skillGoodsServiceImpl;
    /**
     * 订单详情信息
     * @param sSkillUser 用户对象
     * @param orderId 订单id
     * @return ResultVo<Order>
     */

    @GetMapping("/orderDetail")
    public ResultVo<OrderDeailVo> orderDetail(SSkillUser sSkillUser, @RequestParam("orderId") String orderId){
        OrderDeailVo orderDeailVo = new OrderDeailVo();

        orderDeailVo.setsSkillUser(sSkillUser);

        Order order = orderServiceImpl.queryOrderById(orderId);
        if(order == null) {
            logger.error("查询无次订单  orderId:{}",orderId);
            throw new SSkillException(SystemCode.ORDER_NOT_EXIST);
        }
        //查询goodsVo
        GoodsVo goodsVo = skillGoodsServiceImpl.queryGoodsVoDetails(order.getGoodsId());
        if(goodsVo == null) {
            logger.error("秒杀商品不存在");
            throw  new SSkillException(SystemCode.GOODS_NOT_EXISTS);
        }
        orderDeailVo.setOrder(order);
        orderDeailVo.setGoodsVo(goodsVo);
        return ResultVoUtils.success(orderDeailVo);
    }
}
