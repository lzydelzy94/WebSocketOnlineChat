package com.lzy.websocketonlinechat;

import com.lzy.websocketonlinechat.pojo.ChatMessage;
import com.lzy.websocketonlinechat.pojo.ChatMsgType;
import com.lzy.websocketonlinechat.service.IMessageService;
import com.lzy.websocketonlinechat.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//当springboot项目集成了websocket时，此时单元测试类启动后就会报错，因为test启动时不会启动服务器
// 因此要添加测试环境
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketOnlineChatApplicationTests {
    @Autowired
    IMessageService messageService;


    @Test
    void contextLoads() {
        try {
            ChatMessage msg1 = ChatMessage.builder().fromId(2)
                    .toId(9).msgType(ChatMsgType.LEAVE_MSG).build();
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1500);
                msg1.setSendTime(Utils.getCurrentTime());
                msg1.setContent("第"+i+"次"+"你好啊");
                messageService.saveMessage(msg1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getLeavingMsgsTest(){
        //messageService.getLeavingMsgs(9,ChatMsgType.LEAVE_MSG).forEach(e-> System.out.println(e));
        messageService.getMessages(1,2,ChatMsgType.USER_TO_USER_MSG).forEach(e-> System.out.println(e));
    }

}
