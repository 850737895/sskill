package com.hnnd.mapper;

import com.hnnd.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 订单表
 *
 private String orderId;

 private long userId;

 private long goodsId;

 private String orderAddress;

 private String goodsName;

 private double goodsPrice;

 private Integer goodsCount;

 private Integer orderChannel=1;

 private Integer orderStatus=0;

 private Date createDate;

 private Date payDate;
 * Created by 85073 on 2018/4/1.
 */

@Mapper
public interface OrderMapper {

    @Insert("insert into orders(order_id,user_id,goods_id,order_address,goods_name,goods_count,goods_price," +
            "order_channel,order_status,create_date,pay_date) values(#{orderId},#{userId},#{goodsId},#{orderAddress}," +
            "#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{orderStatus},#{createDate},#{payDate})")
    public int insertOrder(Order order);

    @Select("select * from orders where order_id=#{orderId}")
    public Order queryOrderById(@Param("orderId") String orderId);
}
