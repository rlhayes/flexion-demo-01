package com.flexion.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class FileRecorder {

	private static final String DATE_KEY = "when";
	private static final String FILENAME_KEY = "filename";
	private Store store;
	
	public static class FileRecord {
		private String filename;
		private Date processed;
		
		public FileRecord(String filename, Date processed) {
			super();
			this.filename = filename;
			this.processed = processed;
		}

		public String getFilename() {
			return filename;
		}

		public Date getProcessed() {
			return processed;
		}
	}
	
	public FileRecorder(Store s) {
		s.init();
		store = s;
	}
	
	public void record(String fn) {
		Map<String,Object> v = new HashMap<String, Object>();
		
		v.put(FILENAME_KEY, fn);
		v.put(DATE_KEY, new Date());
		
		store.store(v);
	}
	
	public void record(FileRecord fr) {
		Map<String,Object> v = new HashMap<String, Object>();
		
		v.put(FILENAME_KEY, fr.getFilename());
		Date processed = fr.getProcessed();
		if (processed == null) {
			processed = new Date();
		}
		v.put(DATE_KEY, processed);
		
		store.store(v);		
	}
	
	public List<FileRecord> processed() {
		List<FileRecord> value = new ArrayList<FileRecord>();
		
		Iterable<DBObject> rr = store.contentsSorted(DATE_KEY, -1);
		
		for (DBObject r : rr) {
			String filename = (String)r.get(FILENAME_KEY);
			Date processed = (Date)r.get(DATE_KEY);
			FileRecord fr = new FileRecord(filename, processed);
			value.add(fr);
		}
		
		return value;
	}
	
	public Date whenProcessed(String fn) {
		DBCollection collection = store.getCollection();
		
		DBObject example = new BasicDBObject(FILENAME_KEY, fn);
		DBObject sort = new BasicDBObject(DATE_KEY, -1);
		DBCursor c = collection.find(example).sort(sort).limit(1);
		DBObject result = c.next();
		
		if (result != null) {
			return (Date) result.get(DATE_KEY);
		}
		return null;
	}
}
