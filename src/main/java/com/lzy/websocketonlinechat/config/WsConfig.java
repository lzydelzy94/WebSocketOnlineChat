package com.lzy.websocketonlinechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author lzy
 * @className WsConfig
 * @description websockt配置类
 */

@Configuration
public class WsConfig {
    @Bean
    //注入serverEndpointExporter bean对象，自动注册使用@ServerEndpoint注解的bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }



//    @Autowired
//    public void setUserService(UserService userService){
//        ChatEndpoint.userService = userService;
//    }
//
//    @Autowired
//    public void setMessageService(MessageService messageService){
//        ChatEndpoint.messageService = messageService;
//    }
}
