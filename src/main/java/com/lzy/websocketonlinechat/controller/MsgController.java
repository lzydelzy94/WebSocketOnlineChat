package com.lzy.websocketonlinechat.controller;

import com.lzy.websocketonlinechat.pojo.ChatMessage;
import com.lzy.websocketonlinechat.pojo.ChatMsgType;
import com.lzy.websocketonlinechat.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzy
 * @className MsgController
 * @date 2022/8/20 2:58

 */

@RestController
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    IMessageService iMessageService;

    @GetMapping("/{toId}")
    public List<ChatMessage> getLeavingMsgs(@PathVariable int toId) {
        List<ChatMessage> list = iMessageService.getLeavingMsgs(toId, ChatMsgType.LEAVE_MSG);
        // 修改为已读状态，测试时关闭此步骤
//        if(null != list){
//            iMessageService.UpdateMsgToReaded(list);
//        }
        return iMessageService.getLeavingMsgs(toId, ChatMsgType.LEAVE_MSG);
    }

}
