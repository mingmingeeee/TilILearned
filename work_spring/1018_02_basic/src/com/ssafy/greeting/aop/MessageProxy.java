package com.ssafy.greeting.aop;

import java.lang.reflect.Method;

import com.ssafy.greeting.MessageService;

public class MessageProxy implements MessageService {

	private MessageService service; // Proxy 대상 (Target)
	private LogAspect aspect; // Proxy 대본 (Proxy)

	public MessageProxy(MessageService service, LogAspect logAspect) {
		this.service = service;
		this.aspect = logAspect;
	}

	@Override
	public void sayHello() {

		// aspect 클래스 정보 가져옴
		Class clazz = aspect.getClass();

		// method 두 개 정보 저장할 변수
		Method before = null;
		Method after = null;

		// method들 for문 돌면서 탐색 => clazz.getDeclaredMethods()에 정보 들어가있음
		for (Method m : clazz.getDeclaredMethods()) {
			// Method m에 (method들 중 하나에) annotation 존재하면 실행
			if (m.isAnnotationPresent(Aspect.class)) {
				// annotation: annotation 정보
				Aspect annotation = m.getAnnotation(Aspect.class);

				boolean isSayHello = annotation.target().equals("sayHello()");
				boolean isBefore = annotation.timing().equals("before");
				boolean isAfter = annotation.timing().equals("after");

				if (isSayHello && isBefore) {
					before = m; // before라는 변수에 method 담은 것 => logStart
				} else if (isSayHello && isAfter) {
					after = m; // after라는 변수에 method 담은 것 => logEnd
				}
			}
		}

		try {
		// Aspect Before 실행 (Method)
		// before.invoke("method를 실행할 객체", "해당 method의 parameter 값");
		if (before != null)
			before.invoke(aspect, null);

		this.service.sayHello();

		// Aspect After 실행 (Method)
		if (after != null)
			after.invoke(aspect, null);
		} catch(Exception e) {
			e.printStackTrace();
		}

}

}
