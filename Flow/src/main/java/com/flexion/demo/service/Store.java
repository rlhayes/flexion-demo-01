package com.flexion.demo.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBAddress;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class Store {

	private String dbName;
	private String collectionName;
	private DBAddress addr;
	
	private DB db;
	private DBCollection collection;
	
	private static final Logger logger = LoggerFactory.getLogger(Store.class);
	
	public void init() {
		try {
			Mongo m;
			if (addr == null) {
				m = new Mongo();
			} else {
				m = new Mongo(addr);
			}
			db = m.getDB(dbName);
			collection = db.getCollection(collectionName);
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	}
	
	public void store(Object x) {
		logger.info("Storing {}", x);

		Map<String,String> m = (Map<String, String>) x;
		DBObject obj = new BasicDBObject(m);
		collection.save(obj);	
	}

	public boolean remove(String kay, String val) {
		BasicDBObject q = new BasicDBObject(kay, val);
		Object prev = collection.findAndRemove(q);
		return prev != null;
	}
	
	public long count() {
		return collection.count();
	}
	
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public DBAddress getAddr() {
		return addr;
	}

	public void setAddr(DBAddress addr) {
		this.addr = addr;
	}
	
}

