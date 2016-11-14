package com.ashish.poc.config;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@Aspect
public class LoggingAspect {
	private static final Logger log = Logger.getLogger(LoggingAspect.class);

//	@Around("execution(* com.ashish.poc.services..*(..)) || "
//			+ "execution(* com.ashish.poc.ws..*(..))")
	@Around("execution(* com.ashish.poc.ws.*.*(..)) || execution(* com.ashish.poc.services.*.*(..)))")
	public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		log.debug(">>>>>>>>>>" + pjp.getSignature().toShortString()
				+ " START >>>>>>>>>>>>");
		Object retVal = pjp.proceed();
		long end = System.currentTimeMillis();
		log.debug("[" + pjp.getSignature().toShortString()
				+ "] method Execution Time: " + (end - start) + " ms.");
		log.debug("<<<<<<<<" + pjp.getSignature().toShortString()
				+ " END <<<<<<<<");
		return retVal;
	}
}
