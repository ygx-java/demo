package com.core.activemq.service;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.core.activemq.producer.ActiveProducer;

@Service
public class ActiveMqService implements ActiveProducer{

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Override
	public void sendMessage(Destination destination, Object message) {
		jmsTemplate.convertAndSend(destination,message);
	}

	@Override
	public void sendMessage(String destination, Object message) {
		jmsTemplate.convertAndSend(destination,message);
	}

}
