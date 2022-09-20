package com.ssafy.was;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SsafyRequest {
	
	private Map<String, String> headers;
	private Map<String, String> parameters;
	private String sessionId;  // 현재 요청에서 사용하는 sessionId

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	
	public String getParameter(String parameterName) {
		return parameters.get(parameterName);
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
	
	public String getHeader(String name) {
		return headers.get(name);
	}

	public SsafyCookie[] getCookies() {
		List<SsafyCookie> cookies = new ArrayList<>();
		
		String headerCookie = this.getHeader("Cookie");
		if (headerCookie == null) {
			return null;
		}
		
		String[] keyValues = headerCookie.split("; ");
		for (String keyValue : keyValues) {
			String[] item = keyValue.split("=");
			cookies.add(new SsafyCookie(item[0], item[1]));
		}
		
		return cookies.toArray(new SsafyCookie[cookies.size()]);
	}
	
	public SsafySession getSession() {
		return getSession(true);
	}

	public SsafySession getSession(boolean isCreate) {
		
		// SSAFYSESSIONID 쿠키 찾기
		String sessionId = null;
		SsafyCookie[] cookies = getCookies();
		if (cookies != null) {
			for (SsafyCookie cookie : cookies) {
				if (cookie.getName().equals("SSAFYSESSIONID")) {
					sessionId = cookie.getValue();
					break;
				}
			}
		}
		
		SsafySessionStorage storage = SsafySessionStorage.getInstance();
		if (sessionId != null) {
			// 클라이언트가 세션 아이디를 가지고 있다면 세션 저장소에서 해당 아이디로 세션 객체 찾기
			SsafySession ssafySession = storage.getSsafySession(sessionId);
			if (ssafySession != null) {
				// 찾았다면 해당 세션 객체 리턴
				this.sessionId = sessionId;
				return ssafySession;
			}
			else {
				// 찾지 못했다면 새로운 클라이언트 이므로 새로운 세션 객체 생성
				if (isCreate) {
					SsafySession newSession = new SsafySession();
					String newSessionId = String.valueOf(System.currentTimeMillis());  // 현재 시간 값으로 id 생성
					storage.addSsafySession(newSessionId, newSession);

					this.sessionId = newSessionId;
					return newSession;
				}
				else {
					// Session 객체가 서버의 저장소에 존재하지 않을 때 새로 객체를 생성하지 않을 경우는 null 리턴
					return null;
				}
			}
		}
		else {
			// 클라이언트가 세션 아이디를 가지고 있지 않다면 새로운 세션 객체 생성
			if (isCreate) {
				SsafySession newSession = new SsafySession();
				String newSessionId = String.valueOf(System.currentTimeMillis());
				storage.addSsafySession(newSessionId, newSession);

				this.sessionId = newSessionId;
				return newSession;
			}
			else {
				// Session 객체가 서버의 저장소에 존재하지 않을 때 새로 객체를 생성하지 않을 경우는 null 리턴
				return null;
			}
		}
	}

	public String getSessionId() {
		return this.sessionId;
	}
}










