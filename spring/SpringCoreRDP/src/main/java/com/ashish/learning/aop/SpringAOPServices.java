package com.ashish.learning.aop;

import org.springframework.stereotype.Component;


@Component("springAopServices")
public class SpringAOPServices {
	public void aMethod() {
		System.out.println("Inside aMethod()");
	}

	public String returningAdvice() {
		System.out.println("Inside returningAdvice");
		return new String("Hello world from returningAdvice");
	}

	public void throwsAdvice() {
		System.out.println("Inside throwsAdvice");
		throw new RuntimeException("Exception from throwsAdvice");
	}

	public String testAroundAdvice() {
		System.out.println("Inside testAroundAdvice");
		return new String("Hello world from testAroundAdvice");
	}

	public void testAroundThrowingExceptionAdvice() throws Exception {
		System.out.println("Inside testAroundThrowingExceptionAdvice");
		throw new RuntimeException("Exception from testAroundThrowingExceptionAdvice");
	}
}
