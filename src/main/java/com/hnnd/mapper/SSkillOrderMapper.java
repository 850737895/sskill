package com.hnnd.mapper;

import com.hnnd.entity.SSkillOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 秒杀订单mapper
 * Created by 85073 on 2018/4/1.
 */

@Mapper
public interface SSkillOrderMapper {

    @Select("select * from sskill_order so where so.sskill_goods_id=#{sskillGoodsId} and so.user_id=#{sskillUserId}")
    public SSkillOrder querySSKillOrderByCondition(@Param("sskillUserId") long sskillUserId, @Param("sskillGoodsId") long sskillGoodsId);

    @Insert("insert into sskill_order(order_id,sskill_goods_id,user_id) values(#{orderId},#{sskillGoodsId},#{userId})")
    public int insertSSkillOrder(SSkillOrder sSkillOrder);
}
