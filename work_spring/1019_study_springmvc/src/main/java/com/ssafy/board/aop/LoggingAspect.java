package com.ssafy.board.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component // Bean 등록
@Aspect // 공통 관심사 코드가 들어 있는 클래스라는 뜻으로 Spring에게 알려줌
public class LoggingAspect {

	// 로그 찍을 때 좀 더 효율적으로... 원하는 양식대로 찍기 위해 만든 것
	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
//
//	// 어느쪽에다 공통 관심 기능 실행할지 작성한 것
//	// @Before: 앞에
//	// *[접근 제한자] com.ssafy.board.model..[com.ssafy.board.model로 시작하는 모든 클래스]
//	// Board*.* [에 있는 Board로 시작하는 클래스의 메서드] (..) [파라미터 제한 X] 인 애들
//	@Before(value = "execution(* com.ssafy.board.model..Board*.*(..))") // Pointcut
//	public void login(JoinPoint joinPoint) {
//		logger.debug("before 호출 메서드: {}", joinPoint.getSignature());
//		logger.debug("메서드 선언부: {} 전달 파라메터: {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
//	}
//
//	// @Around: 지정된 패턴에 해당하는 메서드의 실행되기 전, 후에서 모두 동작
//	@Around(value = "execution(* com.ssafy.board.model..Board*.*(..))")
//	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//		// 시간 측정 예제
//		logger.debug("around 호출 메서드: {} ", joinPoint.getSignature());
//
//		StopWatch stopWatch = new StopWatch();
//		stopWatch.start(); // 시작
//
//		Object proceed = joinPoint.proceed(); // 각 원래의 메서드 실행
//
//		stopWatch.stop(); // 스탑
//
//		logger.debug("요약: {}", stopWatch.shortSummary());
//		logger.debug("수행시간: {}", stopWatch.getTotalTimeMillis());
//		logger.debug("예쁘게 출력: {}", stopWatch.prettyPrint());
//
//		return proceed;
//	}
	
	// @AfterReturning
	// obj: return되는 객체 => "obj"로 일치시켜주기
	// execution(* com.ssafy.board.model..Board*.list*(..) 이런 애들의 return 값만 가져오기 
//	@AfterReturning(value = "execution(* com.ssafy.board.model..Board*.list*(..))", returning = "obj")
//	public void afteReturningMEthod(JoinPoint joinPoint, Object obj) {
//		logger.debug("afterReturning 호출 메서드: {}", joinPoint.getSignature());
//		logger.debug("return 값: {}", obj);
//	}
//	
	
	// @AfterThrowing => 에러가 발생할 때 쓰임
	@AfterThrowing(value = "execution(* com.ssafy.board.model..Board*.list*(..))", throwing = "exception")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception exception) {
		logger.debug("afterThrowing 호출 메서드: {}", joinPoint.getSignature());
		logger.debug("exception: {}", exception);
	}
	
	// list~가 끝나는 시점마다 처리될 것
	// 메서드 호출 이후에 출력됨
	@After(value = "execution(* com.ssafy.board.model..Board*.list*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		logger.debug("after 호출 메서드: {}", joinPoint.getSignature());
	}
	
	
	

}
