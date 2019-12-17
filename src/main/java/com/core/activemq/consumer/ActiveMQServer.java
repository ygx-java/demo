package com.core.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ActiveMQServer {

	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "active")
    public void receiveQueue(String text) {
        if(!StringUtils.isEmpty(text)){
            System.out.println("ActiveMQServer收到的报文为:"+text);
        }
    }
    
    @JmsListener(destination = "active")
    public void receiveQueue2(String text) {
        if(!StringUtils.isEmpty(text)){
            System.out.println("ActiveMQServer2收到的报文为:"+text);
        }
    }
}
