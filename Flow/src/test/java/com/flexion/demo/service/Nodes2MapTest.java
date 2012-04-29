package com.flexion.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Nodes2MapTest {

	protected Nodes2Map victim;
	
	@Before
	public void init() {
		victim = new Nodes2Map();
	}
	
	private List<Node> makeNodes() throws Exception {
		DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = fact.newDocumentBuilder();

		List<Node> nl = new ArrayList<Node>(4);

		Document doc = db.newDocument();
		Element e;
		e = doc.createElement("a"); e.setTextContent("content of a"); nl.add(e);
		e = doc.createElement("b"); e.setTextContent("content of b"); nl.add(e);
		e = doc.createElement("c"); e.setTextContent("content of c"); nl.add(e);
		e = doc.createElement("d"); e.setTextContent("content of d"); nl.add(e);
		return nl;
	}
	
	@Test
	public void testToMap() throws Exception {
		List<Node> nl = makeNodes();
		Map<String,String> result = victim.toMap(nl);
		assertEquals("found all nodes", 4, result.size());
		
		for (Map.Entry<String, String> me : result.entrySet()) {
			assertEquals("expected text content", "content of " + me.getKey(), me.getValue());
		}
	}

}
