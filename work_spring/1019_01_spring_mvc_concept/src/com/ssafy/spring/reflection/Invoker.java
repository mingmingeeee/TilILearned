package com.ssafy.spring.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Invoker {
	
	// obj: Controller 객체
	// requestUrl: Controller에서 요청 받은 url
	public Object invoke(Object obj, String requestUrl, HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		// Controller 내부의 클래스 정보 가져오기
		Class clazz = obj.getClass();
		
		// 해당 클래스에 존재하는 모든 메서드 목록 가져오기 => 요청 url 처리 method들
		Method[] methods = clazz.getDeclaredMethods();
		
		for(Method method : methods) {
			
			// SsafyRequestMapping이라는 애노테이션이 붙어있는지 체크하고
			if(method.isAnnotationPresent(SsafyRequestMapping.class)) {
				// SsafyRequestMapping 애노테이션 
				SsafyRequestMapping anno = method.getAnnotation(SsafyRequestMapping.class);
				
				// 애노테이션의 value와 요청 URL 주소가 같다면 해당 메서드 호출
				if(anno.value().equals(requestUrl)) {
					return method.invoke(obj, req, resp);
				}
			}
		}
		return null;
		
	}

}
