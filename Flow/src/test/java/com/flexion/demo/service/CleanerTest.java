package com.flexion.demo.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CleanerTest {

	private Cleaner victim;
	
	@Before
	public void setup() throws Exception {
		victim = new Cleaner();
		System.out.println(sdf.format(new Date()));
		
		checkDate("2011-05-01T19:22:33");
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	private Date checkDate(String d) throws Exception {
		sdf.setLenient(true);
		Date dt = sdf.parse(d);
		assertNotNull("expected to get date", dt);
		return dt;
	}
	
	@Test
	public void testFindDate() throws Exception {
		Map<String,String> m = new HashMap<String,String>();
		m.put("date", "1957-02-11");
		
		String d;
		d = victim.findDate(m);
		assertEquals("expect Z defaults", "1957-02-11T00:00:00", d);
		checkDate(d);
		
		m.put("time", "14:23:45");
		d = victim.findDate(m);
		assertEquals("expect tz default", "1957-02-11T14:23:45", d);
		checkDate(d);
		
		m.put("zone", "CDT");
		d = victim.findDate(m);
		assertEquals("expect full input preserved", "1957-02-11T14:23:45", d);
		checkDate(d);
		
		m.remove("time");
		d = victim.findDate(m);
		assertEquals("expect defaulted time with preserved tz", "1957-02-11T00:00:00", d);
		checkDate(d);
	}

	@Test
	public void testCleanFileName() {
		assertEquals("expect no change", "c:/x/y/z", victim.cleanFileName("c:/x/y/z"));
		assertEquals("expect suffix removal", "base_name", victim.cleanFileName("base_name.xml"));
		assertEquals("expect dot removal", " Some silly filename", victim.cleanFileName(" Some silly filename."));
		assertEquals("expect removal of only one layer", "pathed/file/name.tar", victim.cleanFileName("pathed/file/name.tar.GZ"));
	}

	@Test
	public void testCleanMap() {
		Map<String,String> m = new HashMap<String,String>();
		m.put("date", "1957-02-11");
		m.put("Empty", "");
		m.put("Padded", "   spaces\t\t");
		m.put("Spaces", "    ");
		m.put("Interior", "Start end.");
		m.put("Null", null);
		
		Map<String,String> mc = new HashMap<String, String>(m);
		
		victim.trimValues(m);
		
		mc.put("Padded", "spaces");
		mc.put("Spaces", "");
		
		assertEquals("expect only a few changes", mc, m);

	}
}
