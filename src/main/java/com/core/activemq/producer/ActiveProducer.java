package com.core.activemq.producer;

import javax.jms.Destination;

public interface ActiveProducer {
	
	public void sendMessage(Destination destination,Object message);
	
	public void sendMessage(String destination,Object message);
}
