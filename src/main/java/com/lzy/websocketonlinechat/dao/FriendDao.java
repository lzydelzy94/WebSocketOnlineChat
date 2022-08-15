package com.lzy.websocketonlinechat.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lzy
 * @className FriendDao
 * @date 2022/8/7 1:20
 * @description 好友创建和删除
 */
@Mapper
public interface FriendDao {

    @Insert("insert into user_friends values(#{uid},#{friendId})")
    public int createFriend(int uid,int friendId);

    @Select("select uname from user where uid in (select friendId from user_friends where uid = #{uid})")
    public List<String> getFriendsByUid(@Param("uid") int id);

    @Delete("delete from user_friends where uid = #{uid} and friendId = #{friendId}")
    public int deleteFriend(int uid, int friendId);
}
