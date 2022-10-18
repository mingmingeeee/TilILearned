package com.ssafy.greeting.aop;

// 공통 관심 기능을 여기서 관리하겠음!
public class LogAspect {
	
	@Aspect(target = "sayHello()", timing="before") // Pointcut => 언제 실행되는 지를 작성해주기!
	 public void logStart() {
		 System.out.println(">> sayHello 시작!!");
	 }
	 
	@Aspect(target = "sayHello()", timing="after") // Pointcut
	 public void logEnd() {
		 System.out.println(">> sayHello 끝!!");
	 }

}
