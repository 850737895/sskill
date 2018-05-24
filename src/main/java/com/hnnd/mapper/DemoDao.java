package com.hnnd.mapper;

import com.hnnd.entity.UserDemo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by 85073 on 2018/3/27.
 */

@Mapper
public interface DemoDao {

    @Select("select * from userdemo where id=#{id}")
    public UserDemo getUser(Integer id);

    @Insert("insert into userdemo(id,name) values(#{id},#{name})")
    public int inserUser(UserDemo userDemo);
}
