package com.hnnd.service;

import com.hnnd.entity.UserDemo;

/**
 * Created by 85073 on 2018/3/27.
 */
public interface DemoService {

    public UserDemo qryUserDemo(Integer id);

    public void testTx();

}
