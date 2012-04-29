package com.flexion.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.integration.handler.MessageProcessor;

public class Transformer implements MessageProcessor<Object>{
	private static Logger logger = LoggerFactory.getLogger(Transformer.class);
	
	@Override
	public Object processMessage(Message<?> m) {
		logger.info("Got message hdrs {}", m.getHeaders());
		logger.info("Payload is of type {}", m.getPayload().getClass());
		return m.getPayload();
	}
}
