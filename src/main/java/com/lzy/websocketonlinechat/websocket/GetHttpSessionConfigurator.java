package com.lzy.websocketonlinechat.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author lzy
 * @className GetHttpSessionConfigurator
 * @date 2022/8/6 17:15
 * @description websocket获取httpssion对象
 */

public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    //此方法用来获取httpssion对象
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        if(httpSession != null) {
            sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
        }
    }
}
