package com.ashish.learning.v4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.ashish.learning.v4.config.AppConfig;

public class Main {

	public static void main(String args[]) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		springScopeAndLifecycle(context);
		
//		constructorBasedDI(context);
		
		setterMethodBasedDI(context);
	}
	
	/**
	 * Setter method based dependency injection 
	 */
	private static void setterMethodBasedDI(ApplicationContext context) {
		ShowRoom obj1 = (ShowRoom) context.getBean(ShowRoom.class);
		System.out.println("\n>>>>>>>>>>>>>>>SETTER BASED DI STARTS>>>>>>>>>>>>>\n");
		obj1.getCar();
		System.out.println("\n>>>>>>>>>>>>>>>SETTER BASED DI ENDS>>>>>>>>>>>>>\n");
	}
	
	
	/**
	 * This method describes the
	 * 		a) scope of beans, 
	 * 		b) life cycle of beans
	 * 		c) post processor.
	 */
	private static void springScopeAndLifecycle(ApplicationContext context) {
		
		System.out.println("---------------SINGLETON SCOPE---------------------");
		// Singleton scope
		HelloWorld obj1 = (HelloWorld) context.getBean("helloWorld1");
	    obj1.setMessage("Singleton");
	    obj1.getMessage();

	    HelloWorld obj2 = (HelloWorld) context.getBean("helloWorld1");
	    obj2.getMessage();
	    

	    System.out.println("---------------PROTOTYPE SCOPE---------------------");
	    // Prototype scope
	    HelloWorld obj3 = (HelloWorld) context.getBean("helloWorld2");
	    obj3.setMessage("Prototype");
	    obj3.getMessage();

	    HelloWorld obj4 = (HelloWorld) context.getBean("helloWorld2");
	    obj4.getMessage();

	    System.out.println("--------------SPRING LIFECYCLE----------------------");
	    HelloWorld obj5 = (HelloWorld) context.getBean("helloWorldInitDestroy");
	    obj5.setMessage("init and destroy method");
	    obj5.getMessage();
	    
	    // close() method destroys the all the context beans immediately after it gets executed
	    // registerShutdownHook() method destroys the context beans once JVM is shutdown.
	    // close() should be used at the end of the all context beans call but 
	    // registerShutdownHook() can be used anywhere after the initialization
	    
	    // ALTERNATIVE: ((AbstractApplicationContext)context).close();
	     ((AbstractApplicationContext)context).registerShutdownHook();
	}
	
	
	/**
	 * Constructor based dependency injection 
	 */
	private static void constructorBasedDI(ApplicationContext context) {
		System.out.println("\n>>>>>>>>>>>>>>>CONSTRUCTOR BASED DI STARTS>>>>>>>>>>>>>\n");
		ShowRoom obj1 = (ShowRoom) context.getBean(ShowRoom.class);
		obj1.getCar();
		System.out.println("\n>>>>>>>>>>>>>>>CONSTRUCTOR BASED DI ENDS>>>>>>>>>>>>>\n");
	}
}
