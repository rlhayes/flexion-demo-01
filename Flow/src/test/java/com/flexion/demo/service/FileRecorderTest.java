package com.flexion.demo.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FileRecorderTest {

	private Store s;
	private FileRecorder fr;
	
	@Before
	public void setUp() throws Exception {
		Store store = new Store();
		store.setDbName("gwdp");
		store.setCollectionName("files_done");
		
		s = store;
		
		fr = new FileRecorder(store);
	}

	@Test
	public void testRecord() {
		fr.record("/tmp/foobar.txt");
		assertTrue("Survived", true);
		
		List<FileRecorder.FileRecord> rr = fr.processed();
		
		assertTrue("got somthing", ! rr.isEmpty());
		
		for (FileRecorder.FileRecord r : rr) {
			System.out.printf("%s at %s\n", r.getFilename(), r.getProcessed());
		}
	}

	@Test
	public void testRecordAndGet() {
		fr.record("/tmp/foobar.txt");
		assertTrue("Survived", true);
		
		Date now = new Date();
		
		Date then = fr.whenProcessed("/tmp/foobar.txt");
		assertNotNull("then", then);
	}
}
