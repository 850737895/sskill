package com.hnnd.mapper;

import com.hnnd.entity.SSkillGoods;
import com.hnnd.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 秒杀 商品mapper
 * Created by 85073 on 2018/4/1.
 */
@Mapper
public interface SSkillGoodsMapper {

    @Select("select g.*,sg.sskill_price,sg.sskill_stock_count,sg.sskill_start_time,sg.sskill_end_time" +
            " from sskill_goods sg left join goods g on sg.sskill_goods_id=g.id")
    public List<GoodsVo> queryGoodsVoList();

    @Select("select g.*,sg.sskill_price,sg.sskill_stock_count,sg.sskill_start_time,sg.sskill_end_time" +
            " from sskill_goods sg left join goods g on sg.sskill_goods_id=g.id where  sg.sskill_goods_id=#{id}")
    public GoodsVo queryGoodsVoDetails(@Param("id") long id);

    /**
     * 防止超卖
     * @param sSkillGoods
     * @return
     */
    @Update("update sskill_goods sg set sg.sskill_stock_count=sg.sskill_stock_count-1 where sg.sskill_goods_id=#{sskillGoodsId} and sg.sskill_stock_count>0")
    public int updateSSkillGoodsStock(SSkillGoods sSkillGoods);
}
