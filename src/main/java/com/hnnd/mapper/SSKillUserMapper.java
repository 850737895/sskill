package com.hnnd.mapper;

import com.hnnd.entity.SSkillUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by 85073 on 2018/3/30.
 */

@Mapper
public interface SSKillUserMapper {

    @Select("select * from sskill_user where user_id=#{id}")
    SSkillUser findbyId(@Param("id") long id);

    @Update("update sskill_user su set su.password=#{password} where  su.user_id=#{userId}")
    int updateById(SSkillUser sSkillUser);
}
