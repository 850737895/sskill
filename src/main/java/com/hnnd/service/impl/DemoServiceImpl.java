package com.hnnd.service.impl;

import com.hnnd.mapper.DemoDao;
import com.hnnd.entity.UserDemo;
import com.hnnd.service.DemoService;
import com.hnnd.service.SSkillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 85073 on 2018/3/27.
 */

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;

    @Autowired
    private SSkillGoodsService sSkillGoodsServiceImpl;

    @Override
    public UserDemo qryUserDemo(Integer id) {
        return demoDao.getUser(id);
    }

    @Override
    @Transactional
    public void testTx() {
        UserDemo u1 = new UserDemo();
        u1.setId(2);
        u1.setName("zs");

        demoDao.inserUser(u1);

        UserDemo u2 = new UserDemo();
        u2.setId(1);
        u2.setName("ls");
        demoDao.inserUser(u2);

    }

}
