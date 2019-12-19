package com.base.rocket.cunsumer;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
		consumerGroup = "consumerGroup2"
		,topic = "topic-2"
		,selectorExpression = "*"
//		,consumeMode = ConsumeMode.ORDERLY
		)
public class CunsumerNote implements RocketMQListener<String>{

	@Override
	public void onMessage(String message) {
		System.out.println(message);
	}
	
}
