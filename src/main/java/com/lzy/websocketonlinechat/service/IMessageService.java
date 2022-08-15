package com.lzy.websocketonlinechat.service;

import com.lzy.websocketonlinechat.pojo.ChatMessage;

import java.util.List;

public interface IMessageService {

    public int saveMessage(ChatMessage message);

    public List<ChatMessage> getMessages(int fromId, int toId, int msgType);
}
