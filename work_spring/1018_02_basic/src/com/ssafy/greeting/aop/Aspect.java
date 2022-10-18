package com.ssafy.greeting.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Aspect
 * 여러 객체에 공통으로 적용되는 공통 관심 사항을 Aspect라고 한다.
 * 트랜잭션이나 보안, 시간 측정 등이 Aspect의 좋은 예이다. => 한데 묶어놓은 것
 * 
 * Advice
 * 언제 공통 관심 기능을 핵심 로직에 적용할지를 정의하고 있다.
 * 예를 들어, '메서드를 호출하기 전' (언제)에 '성능 측정을 시작한다'(공통 기능) 기능을
 * 적용한다는 것을 정의하고 있다.
 * 
 * Joinpoint
 * Advice를 적용 가능한 지점을 의미한다. 메서드 호출, 필드 값 변경 등이
 * Joinpoint에 해당한다.
 * 
 * Pointcut
 * Joinpoint의 부분 집합으로써 실제로 Advice가 적용되는 Joinpoint를 나타낸다.
 * 스프링에서는 정규 표현식이나 AspectJ의 문법을 이용하여 Pointcut을 정의할 수 있다.
 * 
 * Weaving
 * Advice를 핵심 로직 코드에 적용하는 것을 Weaving이라고 한다.
 * 공통 코드를 핵심 로직 코드에 삽입하는 것이 Weaving 이다.
 * 
 * */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
	
	// Pointcut => "실제로" Advice가 적용되는 Joinpoint
	String target(); // 어느 핵심 로직에 Aspect를 적용할지 작성
	String timing(); // 어느 시점에 Aspect를 실행할지 작성

}
