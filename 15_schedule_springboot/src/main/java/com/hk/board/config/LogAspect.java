package com.hk.board.config;

import java.lang.System.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// @Component: 객체 등록의 공통적인 방법
// @Controller, @Service, @Repository...: 클래스의 기능적인 부분으로 구분
//		- 클래스의 기능적인 부분으로 구분해서 표현
// @Configuration: 환경설정 정의하는 객체

@Component //내가 만든 클래스를 등록
@Aspect    //AOP로 사용할 객체 선언
@Slf4j     //log4j가 출력할 내용을 정의할 수 있는 객체
public class LogAspect {
	//@Slf4j를 사용하면 필요없음(권장)
//	Logger logger=LoggerFactory.getLogger(getClass());
	
	//poingtcut 정의 : 기능(advice)을 실행할 위치
	// - within(): 클래스 단위로 설정
	// - execution(): 메서드 단위로 설정
	@Pointcut(value = "within(com.hk.board.controller.*)")
//	@Pointcut(value = "execution(com.hk.board.controller.Calcontroller.*Board(..)")
	public void controller() {}
	
	//before
	@Before(value="controller()")
	public void before(JoinPoint join) {
		log.info("before메서드실행:",join.getSignature().getName());
	}
	//afterreturning
	@AfterReturning(pointcut = "controller()", returning = "returnVal")
	public void afterReturning(JoinPoint joinPoint, Object returnVal) {
		log.info("afterReturning:{}, 리턴값{}", joinPoint.getSignature().getName(),returnVal);
	}
	//afterThrowing
	@AfterThrowing(pointcut = "controller()", throwing = "e")
	public void afterThrowing(JoinPoint join, Exception e) {
		log.error("afterThrowing:{}, 오류내용:{}", join.toShortString(), e.getMessage());
	}
	//Around: 메서드 실행 전과 후 모두 적용
	//ProceedingJoinPoint는 JoinPoint를 상속한 인터페이스
	//-Around에서만 사용가능
	@Around(value = "controller()")
	public Object around(ProceedingJoinPoint join) throws Throwable {
		log.info("Before:{}",join.getSignature().getName());
		Object result=join.proceed();
		log.info("After:{}, 반환값:{}", join.getSignature().getName(), result);
		return result;
	}
}
