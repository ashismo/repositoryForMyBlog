package com.ashish.beanfactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ashish.bean.HelloWorld;

public class MainApp {
	public static void main(String args[]) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
		
		HelloWorld helloWorld = (HelloWorld) appContext.getBean("helloWorld");
		
		System.out.println(helloWorld.getMessage());
		
	}
}