package com.base.rocket.util;

import javax.annotation.Resource;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class RocketUtil {

	@Resource
	private RocketMQTemplate rocketMQTemplate;

	//局部顺序发送
	public void sendOrder(String topic,String msg,String queueID) {
		rocketMQTemplate.setMessageQueueSelector(
				(list, message, o) -> list.get(Integer.parseInt(o.toString())));
        SendResult result=rocketMQTemplate.syncSendOrderly(topic,msg,queueID);
	}
	//普通发送
	public void send(String topic,String msg) {
		rocketMQTemplate.send(topic, MessageBuilder.withPayload(msg).build());
	}
	
	//延时发送
	public void sendDelay(String topic,String msg,int delayLevel) {
		SendResult result = 
				rocketMQTemplate.syncSend(
						topic, MessageBuilder.withPayload(msg).build(),
				2000, delayLevel);
	}
	//同步发送
	public void syncSend(String topic, String msg) {
		SendResult syncSend = rocketMQTemplate.syncSend(topic, msg);
	}
	//异步发送
	public void asyncSend(String topic, String msg, int delayLevel,SendCallback sendCallback) {
//		SendCallback sendCallback = new SendCallback() {
//			@Override
//			public void onSuccess(SendResult sendResult) {
//			}
//	
//			@Override
//			public void onException(Throwable e) {
//			}
//		};
		rocketMQTemplate.asyncSend(topic, msg, sendCallback);
	}
	// 单向发送
	public void sendOneWay(String topic, String msg, int delayLevel) {
		rocketMQTemplate.sendOneWay(topic, msg);
	}

}
