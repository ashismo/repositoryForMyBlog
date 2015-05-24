package com.ashish;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ashish.services.SpringServices;

public class MainApp {
	public static void main(String args[]) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
		
		SpringServices springServices = (SpringServices) appContext.getBean("springServices");
		
		springServices.aMethod();
		
		System.out.println("===================================\n");
		Object result = springServices.returningAdvice();
		System.out.println("===================================\n");
		try {
			springServices.throwsAdvice();
		} catch (Exception e) {
			System.out.println("Exception caught in MainApp: " + e.getMessage());
		}
		System.out.println("===================================\n");
		result = springServices.testAroundAdvice();
		System.out.println("===================================\n");
		try {
			springServices.testAroundThrowingExceptionAdvice();
		} catch (Exception e) {
			System.out.println("Exception caught in MainApp: " + e.getMessage());
		}
		System.out.println("===================================\n");
	}
}