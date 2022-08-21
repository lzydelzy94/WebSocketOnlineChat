package com.lzy.websocketonlinechat.dao;

import com.lzy.websocketonlinechat.pojo.ChatMessage;
import com.lzy.websocketonlinechat.pojo.ChatMsgType;
import com.lzy.websocketonlinechat.pojo.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

//todo 解决无法映射父类属性的问题
@Mapper
public interface ChatMessageDao {
    @Insert("insert into chat_message values(#{sendTime},#{fromId},#{toId},#{content},#{msgType})")
    public int insertMessage(ChatMessage message);

//    @Select("select * from chat_message where fromId = #{fromId} and toId = #{toId} and msgType = #{msgType}")
//    @Results(id = "chatMsgResultMap",value =
//            {@Result(column = "sendTime", property = "sendTime"),
//                    @Result(column = "fromId", property = "fromId"),
//                    @Result(column = "toId", property = "toId"),
//                    @Result(column = "content", property = "content"),
//                    @Result(column = "msgType", property = "msgType")}
//    )
//    @ResultMap("chatMsgResultMap")
    @Select("select sendTime,fromId,toId,content,msgType from chat_message where fromId = #{fromId} and toId = #{toId} and msgType = #{msgType}")
    public List<ChatMessage> getMessages(@Param("fromId") int fromId,@Param("toId") int toId,@Param("msgType") int msgType);

    @Select("select * from chat_message where toId = #{toId} and msgType=#{msgType} order by sendTime")
    public List<ChatMessage> getLeavingMsgs(int toId, int msgType);

    @Update("update chat_message set msgType = #{msgType} where sendTime=#{sendTime} and fromId=#{fromId} and toId = #{toId}")
    public int updateMsgToReaded(ChatMessage chatMessage,int msgType);

}
