package com.ashish.learning.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	@Before("execution(* com.ashish.learning.aop.SpringAOPServices.aMethod(..))")
	public void beforeExecution(JoinPoint jp) {
		System.out.println("Before method: " + jp.getSignature().getName()
				+ ". Class: " + jp.getTarget().getClass().getSimpleName());
	}

	@After("execution(* com.ashish.learning.aop.SpringAOPServices.aMethod(..))")
	public void afterExecution(JoinPoint jp) {
		System.out.println("After method: " + jp.getSignature().getName()
				+ ". Class: " + jp.getTarget().getClass().getSimpleName());
	}
	
	@AfterReturning(pointcut = "execution(* com.ashish.learning.aop.SpringAOPServices.returningAdvice(..))", returning = "result")
	public void afterReturningExecution(JoinPoint jp, Object result) {
		System.out.println("After returning advice: "
				+ jp.getSignature().getName() + ". Class: "
				+ jp.getTarget().getClass().getSimpleName());
		System.out.println("Advice returned: " + result);
	}

	@AfterThrowing(pointcut = "execution(* com.ashish.learning.aop.SpringAOPServices.throwsAdvice(..))", throwing = "ex")
	public void afterThrowingExecution(JoinPoint jp, Exception ex) {
		System.out.println("After throwing advice: "
				+ jp.getSignature().getName() + ". Class: "
				+ jp.getTarget().getClass().getSimpleName());
		System.out.println("Exception: " + ex.getMessage());
	}

	@Around("execution(* com.ashish.learning.aop.SpringAOPServices.testAround*(..))")
	public Object aroundExecution(ProceedingJoinPoint jp) throws Exception {

		System.out.println("Before method: " + jp.getSignature().getName()
				+ ". Class: " + jp.getTarget().getClass().getSimpleName());

		try {
			// Proceed with method invocation
			Object result = jp.proceed();

			System.out.println("Returning: " + result);
			return result;
		} catch (Throwable e) {
			System.out.println("Error: " + e.getMessage());
			throw new Exception("Error", e);
		}
	}
}
