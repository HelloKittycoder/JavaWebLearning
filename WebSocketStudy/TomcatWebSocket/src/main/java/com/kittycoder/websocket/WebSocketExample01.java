package com.kittycoder.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by shucheng on 2020/5/12 0:14
 * 参考链接：https://github.com/matruskan/websocket-example/blob/master/src/main/java/com/matruskan/websocketexample/example1/WebSocketEndpointExample1.java
 */
@ServerEndpoint("/example01")
public class WebSocketExample01 {

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("收到消息====" + message);
        try {
            session.getBasicRemote().sendText("服务端已收到消息" + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("建立websocket连接");
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("关闭websocket连接");
    }
}
