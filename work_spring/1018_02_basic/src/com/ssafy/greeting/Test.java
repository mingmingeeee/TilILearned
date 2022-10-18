package com.ssafy.greeting;

public class Test {
	
	public static void main(String[] args) {
		
		MyBeanFactory context = MyBeanFactory.getInstance();
		
		// context.getBean("msg") => 원하는 객체를 찾는 행위: lookup 
		// service = context.getBean("msg");
		// => "msg"원하는 객체 service에 할당 => DI "의존성 주입"
		MessageService service = context.getBean("msg");
		
		service.sayHello();
		
	}

}
