package com.lzy.websocketonlinechat.pojo;

/**
 * @author lzy
 * @className MessageType
 * @date 2022/8/6 19:32
 * @description 常量类，消息
 */

public final class ChatMsgType {
    public static final Integer USER_TO_USER_MSG = 10;      //在线用户之间私聊消息

    public static final Integer SERVER_MSG = 20;        //服务器广播消息

    public static final Integer USER_TO_ALL_MSG = 30;       //用户群聊消息

    public static final Integer LEAVE_MSG = 40;       //未读取留言

    public static final Integer READED_MSG = 50; //已读消息

}
