package com.base.rocket.cunsumer;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
		consumerGroup = "consumerGroup 2",
		topic = "topic-2",
		selectorExpression = "*",
		consumeMode = ConsumeMode.ORDERLY)
public class CunsumerNote {
	
}
