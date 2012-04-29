package com.flexion.demo.service;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.integration.handler.MessageProcessor;
import org.w3c.dom.Document;

public class XML2DOM implements MessageProcessor<Document> {

	private static final Logger logger = LoggerFactory.getLogger(XML2DOM.class);
	@Override
	public Document processMessage(Message<?> m) {
		
		File f = (File)m.getPayload();
		
		Document dom = parse(f);
		
		logger.info("Parsed {} to {}", f, dom);
		return dom;
	}
	
	public Document parse(File f) {
		try {
			DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = fact.newDocumentBuilder();

			return db.parse(f);
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	}

}
