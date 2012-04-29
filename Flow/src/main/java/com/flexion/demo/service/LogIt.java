package com.flexion.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogIt {

	private static final Logger logger = LoggerFactory.getLogger(LogIt.class);
	public void log(Object x) {
		logger.info("Sinking {}", x);
	}
}
