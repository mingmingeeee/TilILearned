package com.ssafy.reflection.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * 자바의 리플렉션(Reflection)
 * 
 * Reflection은 자바에서 기본적으로 제공하는 API로써
 * 클래스, 인터페이스, 메서드들을 찾을 수 있고,
 * 객체를 생성하거나 변수를 변경할 수 있고
 * 메서드를 호출할 수도 있습니다.
 * 
 * Reflection은 다음과 같은 정보를 가져올 수 있습니다.
 * Class, Constructor, Method, Field
 * 
 * 이 정보들을 가져와서 객체를 생성하거나 메서드를 호출하거나 변수의 값을 변경 가능합니다.
 */
public class Invoker {
	
	// obj 외부에서 생성한 객체를 전달 받음
	// 전달받은 객체에 존재하는 메서드 정보를 가져옴
	// 가져온 메서드들을 제어
	public void invoke(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		// 1. 클래스 정보 가져오기
		Class clazz = obj.getClass();
		
		// 2. 클래스에 존재하는 메서드 정보들을 가져오기
		Method[] methods = clazz.getDeclaredMethods();
		
		// 3. Order 애노테이션의 number 값을 확인하여 순서대로 메서드 저장할 배열
		Method[] orders = new Method[methods.length];
		
		// 4. Order 애노테이션 정보를 바탕으로 orders 배열에 메서드 저장
		for (Method m : methods) {
			if (m.isAnnotationPresent(Order.class)) {
				Order annotation = m.getAnnotation(Order.class);
				orders[annotation.number() - 1] = m;
			}
		}
		
		// 5. orders 배열에 있는 메서드를 순서대로 호출
		for (Method m : orders) {
			m.invoke(obj, null);
		}
	}
}
