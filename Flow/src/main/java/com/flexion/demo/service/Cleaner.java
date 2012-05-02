package com.flexion.demo.service;

import java.util.Map;

import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Transformer;

public class Cleaner {

	@Transformer
	public Map<String,String> clean(Object x, @Header(value="filename",required=true) String fn) {
		String fnClean = cleanFileName(fn);
		
		Map<String,String> xm = (Map<String, String>) x;
		
		xm.put("well", fnClean);
		
		xm.put("datetime", findDate(xm));
		
		trimValues(xm);
		
		return xm;
	}
	
	public void trimValues(Map<String, String> xm) {
		for (Map.Entry<String, String> me : xm.entrySet()) {
			if (me.getValue() != null) {
				me.setValue(me.getValue().trim());
			}
		}		
	}

	/** Extract a real date from the map, which must have at least date like 1957-03-22
	 * and may have time like 14:33:21 and zone like CDT.
	 * Time defaults to 00:00:00 and zone defaults to +00
	 * @param m
	 * @return Full date string like 1957-03-22T14:33:21CDT
	 */
	public String findDate(Map<String,String> m) {
		String date = m.get("date");
		if (date == null) {
			return null;
		}
		String time = m.get("time");
		if (time == null || time.trim().isEmpty()) {
			time = "00:00:00";
		}
		// TODO Adjust time to UTC
		String zone = m.get("zone");
		if (zone == null || zone.trim().isEmpty()) {
			zone = "UTC";
		}
		return date + "T" + time;
	}

	public String cleanFileName(String fn) {
		return fn.replaceAll("\\.[^.]*$", "");
	}
 }
