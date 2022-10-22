package com.ssafy.ws.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component  // LoggingAspect Bean 등록
@Aspect  // 스프링에게 Aspect(공통 관심 기능) 클래스라는 사실을 알림
public class LoggingAspect {
	
	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before(value = "execution(* com.ssafy.ws.model..*.*(..))")  // Pointcut
	public void logging(JoinPoint joinPoint) {
		logger.debug("메서드 선언부: {} 전달 파라메터: {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
	}
}
