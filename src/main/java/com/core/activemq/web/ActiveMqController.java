package com.core.activemq.web;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.activemq.producer.ActiveProducer;
import com.core.activemq.service.ActiveMqService;

@RestController
@RequestMapping("/active")
public class ActiveMqController {
	
	@Qualifier("ActiveProducer")
	private ActiveProducer activeProducer;


	@RequestMapping("/test")
	public void send() {
		//群发
		ActiveMQTopic activeMQTopic = new ActiveMQTopic("active");
		activeProducer.sendMessage(activeMQTopic, "active");
		//订阅
//		ActiveMQQueue activeMQQueue = new ActiveMQQueue("active");
//		activeProducer.sendMessage(activeMQQueue, "active");
	}
	
}
