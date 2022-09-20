package com.ssafy.was;

import java.util.HashMap;
import java.util.Map;

public class SsafySession {
	
	private Map<String, Object> storage = new HashMap<>();
	
	public void setAttribute(String name, Object value) {
		storage.put(name, value);
	}
	
	public Object getAttribute(String name) {
		return storage.get(name);
	}
	
	public void removeAttribute(String name) {
		storage.remove(name);
	}
	
	public void invalidate() {
		storage.clear();
	}

}
