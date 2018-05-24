package com.hnnd.entity;

import javax.validation.constraints.Min;

/**
 * Created by 85073 on 2018/3/27.
 */
public class UserDemo {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
