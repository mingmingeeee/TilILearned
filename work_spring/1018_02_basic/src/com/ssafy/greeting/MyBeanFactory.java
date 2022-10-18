package com.ssafy.greeting;

import com.ssafy.greeting.aop.LogAspect;
import com.ssafy.greeting.aop.MessageProxy;

// 모든 객체들을 여기서 생성해서 return => "main => MessageService" 강한 결합 끊어주기 위함 
public class MyBeanFactory {
	
	// 강한 결합 끊어주기 위해 Singleton 적용 => 느슨한...관계로...
	private MyBeanFactory() {}
	
	private static MyBeanFactory instance;
	
	public static MyBeanFactory getInstance() {
		if(instance == null)
			instance = new MyBeanFactory();
		return instance;
	}

	public MessageService getBean(String name) {

		if ("msg".contentEquals(name)) {
			MessageService service = new MessageServiceImpl();
			service = new MessageProxy(service, new LogAspect());
			return service;
		}

		return null;

	}

}
