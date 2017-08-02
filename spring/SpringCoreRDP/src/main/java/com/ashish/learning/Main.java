package com.ashish.learning;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Beans.xml");
		
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

}
