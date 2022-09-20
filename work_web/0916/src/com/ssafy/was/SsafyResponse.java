package com.ssafy.was;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SsafyResponse {
	
	private List<SsafyCookie> cookies = new ArrayList<>();
	
	private String msg;
	
	public void print(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void addCookie(SsafyCookie userId) {
		this.cookies.add(userId);
	}
	
	public List<SsafyCookie> getCookies() {
		return this.cookies;
	}
	
	// 쿠키들을 응답 Header에 추가하기 위한 문자열 생성 메서드
	public String getCookiesHeaderString() {
		StringBuilder sb = new StringBuilder();
		
		for (SsafyCookie cookie : this.getCookies()) {
			sb.append("Set-Cookie: ");
			
			// 쿠키의 이름과 값
			sb.append(cookie.getName()).append("=").append(cookie.getValue());
			
			// 쿠키의 유효 기간
			if (cookie.getMaxAge() != -1) {
				sb.append("; ");
				
				// 유효 기간 계산
				// 현재 시간 + 설정한 유효 기간
				long expiry = System.currentTimeMillis() + cookie.getMaxAge();
				
				// 유효 기간을 형식에 맞춰 문자열 생성
				Date expiryDate = new Date(expiry);
				DateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
				df.setTimeZone(TimeZone.getTimeZone("GMT"));
				sb.append("Expires=").append(df.format(expiryDate));
			}
			
			// 쿠키의 도메인
			if (cookie.getDomain() != null) {
				sb.append("; ");
				sb.append("Domain=").append(cookie.getDomain());
			}
			
			// 쿠키의 경로
			if (cookie.getPath() != null) {
				sb.append("; ");
				sb.append("Path=").append(cookie.getPath());
			}
			
			// 하나의 쿠키 정보를 헤더에 작성
			sb.append("\r\n");
		}
		
		return sb.toString();
	}
	
}












