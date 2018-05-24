package com.hnnd.service.impl;

import com.hnnd.service.SSKillUserService;
import com.hnnd.service.SSkillGoodsService;
import com.hnnd.util.MD5Utils;
import com.hnnd.vo.GoodsVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 商品测试类
 * Created by 85073 on 2018/4/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SSkillGoodsServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(SSkillGoodsServiceImplTest.class);

    @Autowired
    private SSkillGoodsService sSkillGoodsServiceImpl;

    @Autowired
    private SSKillUserService ssKillUserService;

    @Test
    public void queryGoodsVoListTest(){
        List<GoodsVo> goodsVoList = sSkillGoodsServiceImpl.queryGoodsVoList();
        logger.info("测试SSkillGoodsService.queryGoodsVoList():结果{}",goodsVoList);
        Assert.assertNotNull(goodsVoList);
    }

    @Test
    public void queryGoodsVoDetails(){
        List<GoodsVo> goodsVoList = sSkillGoodsServiceImpl.queryGoodsVoList();
        logger.info("测试SSkillGoodsService.queryGoodsVoList():结果{}",goodsVoList);
        Assert.assertNotNull(goodsVoList);
    }

    @Test
    public void updatePasswordTest(){
        String token ="9d64dcc1bc834ac3a89f43b23a85e323";
        long id = 17373965471l;
        String newPassword = MD5Utils.firstMd5("726515");
        boolean updateFlag = ssKillUserService.updateSkillUserPassword(token,17373965471l,newPassword);
        Assert.assertTrue(updateFlag);
    }


}