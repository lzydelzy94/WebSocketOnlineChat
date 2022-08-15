package com.lzy.websocketonlinechat.dao;

import com.lzy.websocketonlinechat.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {


    @Select("select * from user where uname = #{uname}")
    public User getUserByName(String uname);

    @Select("select * from user where uid=#{uid}")
    public User getUserByUid(int uid);

    @Insert("insert into user(uname,psw) values(#{uname},#{psw})")
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    public void createUser(User user);

    @Update("update user set uname = #{uname},psw = #{psw} where uname=#{uname}")
    public int updateUser(User user);

}
