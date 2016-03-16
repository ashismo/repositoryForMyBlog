package com.ashish.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

//	@Before("execution(* com.ashish.app..*(..))")
//	public void logBefore(JoinPoint joinPoint) {
//		logger.debug(">>>>>>>>>>" + joinPoint.getSignature().toShortString() + " START >>>>>>>>>>>>");
//	}
	
	@Around("execution(* com.ashish.app..*(..))")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        logger.debug(">>>>>>>>>>" + pjp.getSignature().toShortString() + " START >>>>>>>>>>>>");
        Object retVal = pjp.proceed();
        long end = System.currentTimeMillis();
        logger.debug("[" + pjp.getSignature().toShortString() + "] method Execution Time: " + (end - start) + " ms.");
        logger.debug( "<<<<<<<<" + pjp.getSignature().toShortString() + " END <<<<<<<<");
        return retVal;
    }

//	@After("execution(* com.ashish.app..*(..))")
//	public void logAfter(JoinPoint joinPoint) {
//		logger.debug( "<<<<<<<<" + joinPoint.getSignature().toShortString() + " END <<<<<<<<");
//	}
}
