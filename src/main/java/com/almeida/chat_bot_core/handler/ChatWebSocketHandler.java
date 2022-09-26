package com.almeida.chat_bot_core.handler;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> listWebSocketsSession = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        listWebSocketsSession.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage text) throws Exception{
        for(WebSocketSession webSocketSession : listWebSocketsSession){
            webSocketSession.sendMessage(text);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        listWebSocketsSession.remove(session);
    }

}
