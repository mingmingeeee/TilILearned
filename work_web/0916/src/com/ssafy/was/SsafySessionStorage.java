package com.ssafy.was;

import java.util.HashMap;
import java.util.Map;

public class SsafySessionStorage {
	
	// SSAFYSESSIONID, SsafySession 쌍으로 저장
	// 전달받은 SSAFYSESSIONID 쿠키 값으로 접근
	private Map<String, SsafySession> storage = new HashMap<>();
	
	// 저장소는 유일해야 하므로 싱글턴 패턴 적용
	private SsafySessionStorage() {};
	
	private static SsafySessionStorage instance = new SsafySessionStorage();
	public static SsafySessionStorage getInstance() {
		return instance;
	}
	
	public SsafySession getSsafySession(String sessionId) {
		return storage.get(sessionId);
	}
	
	public void addSsafySession(String sessionId, SsafySession session) {
		storage.put(sessionId, session);
	}

}
