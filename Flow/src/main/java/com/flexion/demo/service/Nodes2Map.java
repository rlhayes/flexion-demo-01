package com.flexion.demo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.integration.annotation.Transformer;
import org.w3c.dom.Node;

public class Nodes2Map {

	@Transformer
	public Map<String,String> toMap(List<Node> nodes) {
		Map<String,String> value = new LinkedHashMap<String, String>(nodes.size());
		
		for (Node n : nodes) {
				String localName = n.getNodeName();
				if (localName != null) {
					value.put(localName, n.getTextContent());
			}
		}
		
		return value;
	}
}
