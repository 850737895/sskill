package com.hnnd.service;

import com.hnnd.entity.Order;
import com.hnnd.entity.SSkillUser;
import com.hnnd.vo.GoodsVo;

/**
 * 秒杀service
 * Created by 85073 on 2018/4/1.
 */
public interface SSKillService {

    /**
     * 判断是否还有多余库存
     * @param sskillGoodsId 秒杀商品ID
     * @return true|fasle
     */
    public boolean hasRemainStock(long sskillGoodsId);

    /**
     * 判断该用户是否秒杀过该商品
     * @param skillUserId 秒杀用户
     * @param sskillGoodsId  秒杀商品id
     * @return  true|false
     */
    public boolean isSkilled(long skillUserId,long sskillGoodsId);

    /**
     * 开启秒杀
     * @return
     */
    public  Order doSkill(SSkillUser sSkillUser,GoodsVo goodsVo);

    /**
     * 查询秒杀情况
     * @param skillUserId 用户id
     * @param skillGoodsId 商品id
     * @return
     */
    public String querySkillResult(long skillUserId,long skillGoodsId);

    /**
     * 生成秒杀地址中随机字符串(生成规则md5(uuid+scret))
     * @param skillUserId 用户Id
     * @param skillGoodsId 商品id
     * @return
     */
    public String generatorRandomStr(long skillUserId,long skillGoodsId);

    /**
     * 校验真正的秒杀地址
     * @param skillUserId 用户id
     * @param skillGoodsId 商品id
     * @param clientRandomStr 客户端带过来的秒杀地址随机字符串
     * @return true or false
     */
    public boolean checkRealAddr(long skillUserId,long skillGoodsId,String clientRandomStr);


}
