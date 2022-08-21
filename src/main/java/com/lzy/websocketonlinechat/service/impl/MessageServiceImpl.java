package com.lzy.websocketonlinechat.service.impl;

import com.lzy.websocketonlinechat.dao.ChatMessageDao;
import com.lzy.websocketonlinechat.pojo.ChatMessage;
import com.lzy.websocketonlinechat.pojo.ChatMsgType;
import com.lzy.websocketonlinechat.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {
    @Autowired
    private ChatMessageDao chatMessageDao;

    @Override
    public int saveMessage(ChatMessage message) {
        return chatMessageDao.insertMessage(message);
    }

    @Override
    public List<ChatMessage> getMessages(int fromId, int toId, int msgType) {
        return chatMessageDao.getMessages(fromId, toId, msgType);
    }

    @Override
    public List<ChatMessage> getLeavingMsgs(int toId, int msgType) {
        return chatMessageDao.getLeavingMsgs(toId, msgType);
    }

    @Override
    public void UpdateMsgToReaded(List<ChatMessage> list) {
        list.forEach(e -> chatMessageDao.updateMsgToReaded(e, ChatMsgType.READED_MSG));
    }


}
