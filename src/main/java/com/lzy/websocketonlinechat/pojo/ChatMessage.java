package com.lzy.websocketonlinechat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author lzy
 * @className ResultMessage
 * @date 2022/8/5 19:44
 * @description 聊天消息
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ChatMessage extends Message{
    //为客户端发送时，发送人uid和uname
    //todo id和name一起封装
    private int fromId;

    private String fromName;

    //为客户端发送时，接收人uid和uname
    //todo id和name一起封装
    private int toId;

    private String toName;


}
