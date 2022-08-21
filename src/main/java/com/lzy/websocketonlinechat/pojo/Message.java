package com.lzy.websocketonlinechat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author lzy
 * @className Message
 * @date 2022/8/8 3:07
 * @description Message
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Message{
    private int msgType;

    private Object content;

    //发送时间
    private String sendTime;
}
