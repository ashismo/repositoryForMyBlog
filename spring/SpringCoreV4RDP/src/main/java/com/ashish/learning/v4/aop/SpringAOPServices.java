package com.ashish.learning.v4.aop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("springAopServices")
public class SpringAOPServices {
	
	@Value("${welcome.note}") private String welcomeNote;
	
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

	public String getWelcomeNote() {
		return welcomeNote;
	}

	public void setWelcomeNote(String welcomeNote) {
		this.welcomeNote = welcomeNote;
	}
}
