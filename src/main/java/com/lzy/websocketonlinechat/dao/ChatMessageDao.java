package com.lzy.websocketonlinechat.dao;

import com.lzy.websocketonlinechat.pojo.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMessageDao {

    @Insert("insert into chat_message values(#{sendTime},#{fromId},#{toId},#{content},#{msgType})")
    public int insertMessage(ChatMessage message);

    @Select("select * from chat_message where fromId = #{fromId} and toId = #{toId} and msgType = #{msgType}")
    public List<ChatMessage> getMessages(int fromId, int toId, int msgType);

}
