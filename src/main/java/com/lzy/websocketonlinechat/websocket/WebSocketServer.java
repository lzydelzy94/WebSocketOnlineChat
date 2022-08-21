package com.lzy.websocketonlinechat.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzy.websocketonlinechat.pojo.ChatMessage;
import com.lzy.websocketonlinechat.pojo.ChatMsgType;
import com.lzy.websocketonlinechat.pojo.User;
import com.lzy.websocketonlinechat.service.IMessageService;
import com.lzy.websocketonlinechat.service.IUserService;
import com.lzy.websocketonlinechat.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lzy
 * @className ChatEndpoint
 * @date 2022/8/6 17:03
 * @description webSocket类
 */

@ServerEndpoint(value = "/chat",configurator = GetHttpSessionConfigurator.class)
@Component
public class WebSocketServer {

    private static IUserService IUserService;

    private static IMessageService IMessageService;

    //spring容器管理为单例对象，而websocket为多例，所以采用方法注入
    //todo 学习原理
    @Autowired
    public void setUserService(IUserService IUserService){
        WebSocketServer.IUserService = IUserService;
    }

    @Autowired
    public void setMessageService(IMessageService service){
        WebSocketServer.IMessageService = service;
    }

    //用来存储每一个客户端对象对应的ChatEndpoint对象
    //用户名作为key进行表示
    private static Map<String, WebSocketServer> onlineUsers = new ConcurrentHashMap<>();


    //声明session对象
    private Session session;

    //HttpSession，登录时在其中存储了用户信息
    private HttpSession httpSession;

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig){
        try {
            this.session = session;
            //获取HttpSession对象
            this.httpSession = (HttpSession)endpointConfig.getUserProperties().get(HttpSession.class.getName());

            //将当前对象存储到集合中,key为httpsessino中当前用户的uname，value为当前对象
            User user = (User)httpSession.getAttribute("user");
            onlineUsers.put(user.getUname(), this);
            //获取留给当前用户的留言
            List<ChatMessage> leavingMsgs = IMessageService
                    .getLeavingMsgs(user.getUid(), ChatMsgType.LEAVE_MSG);

            //将当前在线的所有用户的用户名推送给所有客户端
            //1.获取当前所有在线的用户名
            Set<String> onlineUserNames = onlineUsers.keySet();
            //2.封装成系统广播消息，通过jackson转换成字符串然后广播
            ChatMessage onlineUserMsg = ChatMessage.builder().sendTime(Utils.getCurrentTime())
                    .msgType(ChatMsgType.SERVER_MSG).content(onlineUserNames).build();
            ObjectMapper objectMapper = new ObjectMapper();
            broadCastMessage(objectMapper.writeValueAsString(onlineUserMsg));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //转换为ChatMessage对象，进行处理，存入数据库
    @OnMessage
    public void OnMessage(String message,Session session){
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            ChatMessage chatMessage = objectMapper.readValue(message, ChatMessage.class);

            if(ChatMsgType.USER_TO_USER_MSG == chatMessage.getMsgType()){ //为在线用户间私聊消息时
                //todo 由于前端技术所限，这里需要再通过接收人name查出接收人id放入message中
                chatMessage.setToId(IUserService.getUserByName(chatMessage.getToName()).getUid());
                //推送给指定用户
                sendMessage(objectMapper.writeValueAsString(chatMessage), chatMessage.getToName());
                chatMessage.setMsgType(ChatMsgType.READED_MSG);
            } else if (ChatMsgType.LEAVE_MSG == chatMessage.getMsgType()) {
                //todo 由于前端技术所限，这里需要再通过接收人name查出接收人id放入message中
                chatMessage.setToId(IUserService.getUserByName(chatMessage.getToName()).getUid());
                //为留言消息时，直接存入数据库
                System.out.println(chatMessage);
            } else if (ChatMsgType.USER_TO_ALL_MSG == chatMessage.getMsgType()) {       //为用户群发消息时
                broadCastMessage(objectMapper.writeValueAsString(chatMessage));
            }
            //存入数据库
            IMessageService.saveMessage(chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session){
        try {
            User user = (User) httpSession.getAttribute("user");
            onlineUsers.remove(user.getUname());

            //1.获取当前所有在线的用户名
            Set<String> onlineUserNames = onlineUsers.keySet();
            //2.封装成系统广播消息，通过jackson转换成字符串然后广播
            ChatMessage onlineUserMsg = ChatMessage.builder().sendTime(Utils.getCurrentTime())
                    .msgType(ChatMsgType.SERVER_MSG).content(onlineUserNames).build();
            ObjectMapper objectMapper = new ObjectMapper();
            broadCastMessage(objectMapper.writeValueAsString(onlineUserMsg));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


    private void broadCastMessage(String message){
        onlineUsers.forEach((k,v)->{
            try{
                if(v.session.isOpen()){
                    v.session.getBasicRemote().sendText(message);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    private void sendMessage(String message,String toName){
        try {
            WebSocketServer webSocketServer = onlineUsers.get(toName);
            if(webSocketServer.session.isOpen()){
                webSocketServer.session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
