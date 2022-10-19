package com.ssafy.board.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component  // Bean 등록
@Aspect  // 공통 관심 코드가 들어있는 클래스라는 뜻으로 Spring에게 알려줌
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
//	@Before(value = "execution(* com.ssafy.board.model..Board*.*(..))")
//	public void login(JoinPoint joinPoint) {
//		logger.debug("before 호출 메서드: {} ", joinPoint.getSignature());
//		logger.debug("메서드 선언부: {} 전달 파라메터: {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
//	}
	
//	@Around(value = "execution(* com.ssafy.board.model..Board*.*(..))")
//	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//		logger.debug("around 호출 메서드: {} ", joinPoint.getSignature());
//		
//		StopWatch stopWatch = new StopWatch();
//		stopWatch.start();
//		
//		Object proceed = joinPoint.proceed();  // 각 원래의 메서드 실행
//		
//		stopWatch.stop();
//		
//		logger.debug("요약: {}", stopWatch.shortSummary());
//		logger.debug("수행시간: {}", stopWatch.getTotalTimeMillis());
//		logger.debug("예쁘게 출력: {}", stopWatch.prettyPrint());
//		
//		return proceed;
//	}
	
//	@AfterReturning(value = "execution(* com.ssafy.board.model..Board*.list*(..))", returning = "obj")
//	public void afterReturningMethod(JoinPoint joinPoint, Object obj) {
//		logger.debug("afterReturning 호출 메서드: {} ", joinPoint.getSignature());
//		logger.debug("return 값: {}", obj);
//	}
	
	@AfterThrowing(value = "execution(* com.ssafy.board.model..Board*.list*(..))", throwing = "exception")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception exception) {
		logger.debug("afterThrowing 호출 메서드: {} ", joinPoint.getSignature());
		logger.debug("exception: {}", exception);
		exception.printStackTrace();
	}
	
	@After(value = "execution(* com.ssafy.board.model..Board*.list*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		logger.debug("after 호출 메서드: {}", joinPoint.getSignature());
	}
}
