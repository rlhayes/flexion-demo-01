package com.flexion.demo.service;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.w3c.dom.Document;

public class XML2DOMTest {

	@Test
	public void testParse() {
		XML2DOM victim = new XML2DOM();
		
		File f = new File("/tmp/dropbox/test-order-fulfillment.xml");
		
		Document dom = victim.parse(f);
		
		assertNotNull("document", dom);
		System.out.println("dom is " + dom);
		System.out.println("dom uri is " + dom.getDocumentURI());
		System.out.println("dom node name is " + dom.getNodeName());
	}

}
