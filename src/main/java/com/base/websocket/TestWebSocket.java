package com.base.websocket;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import com.core.websocket.publish.WebSocket;

@Service
@ServerEndpoint("/testWebsocket")
public class TestWebSocket extends WebSocket{

}
