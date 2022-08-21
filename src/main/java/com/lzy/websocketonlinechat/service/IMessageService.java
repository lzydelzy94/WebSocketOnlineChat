package com.lzy.websocketonlinechat.service;

import com.lzy.websocketonlinechat.pojo.ChatMessage;

import java.util.List;

public interface IMessageService {

    public int saveMessage(ChatMessage message);

    public List<ChatMessage> getMessages(int fromId, int toId, int msgType);

    public List<ChatMessage> getLeavingMsgs(int toId, int msgType);

    public void UpdateMsgToReaded(List<ChatMessage> list);

}
