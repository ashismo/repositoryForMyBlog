package com.ashish.test.learning.v4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ashish.learning.v4.HelloWorld;
import com.ashish.learning.v4.ShowRoom;
import com.ashish.learning.v4.aop.SpringAOPServices;
import com.ashish.learning.v4.config.AppConfig;
import com.ashish.learning.v4.qualifier.DessertService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
public class JUnitTest {
	
	@Autowired
	ApplicationContext context;
	
	@Test
	public void setterMethodBasedDI() {
		ShowRoom obj1 = (ShowRoom) context.getBean(ShowRoom.class);
		System.out.println("\n>>>>>>>>>>>>>>>SETTER BASED DI STARTS>>>>>>>>>>>>>\n");
		obj1.getCar();
		System.out.println("\n>>>>>>>>>>>>>>>SETTER BASED DI ENDS>>>>>>>>>>>>>\n");
	}
	
	@Test
	public void springScopeAndLifecycle() {
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
	
	@Test
	public void springAOPExample() {
		SpringAOPServices springAopServices = (SpringAOPServices) context.getBean(SpringAOPServices.class);
		System.out.println(">>>>>>>>>>>>>>>SPRING AOP STARTS>>>>>>>>>>>>>\n");
		springAopServices.aMethod();
		
		System.out.println("===================================\n");
		Object result = springAopServices.returningAdvice();
		System.out.println("===================================\n");
		try {
			springAopServices.throwsAdvice();
		} catch (Exception e) {
			System.out.println("Exception caught in MainApp: " + e.getMessage());
		}
		System.out.println("===================================\n");
		result = springAopServices.testAroundAdvice();
		System.out.println("===================================\n");
		try {
			springAopServices.testAroundThrowingExceptionAdvice();
		} catch (Exception e) {
			System.out.println("Exception caught in MainApp: " + e.getMessage());
		}
		System.out.println(">>>>>>>>>>>>>>>SPRING AOP END>>>>>>>>>>>>>\n");
	}
	
	@Test
	public void springQualifier() {
		System.out.println(">>>>>>>>>>>>>>>SPRING QUALIFIER STARTS>>>>>>>>>>>>>\n");
		DessertService springQualifier = (DessertService) context.getBean(DessertService.class);
		springQualifier.getDessert();
		System.out.println(">>>>>>>>>>>>>>>SPRING QUALIFIER ENDS>>>>>>>>>>>>>\n");
	}
	
	@Test
	public void propertyPlaceholderConfigurerImplementation() { 
		System.out.println("\n>>>>>>>>>>>>>>>>>PropertyPlaceholderConfigurer STARTS>>>>>>>>>>>>\n");
		// Java code reads value from properties file
		SpringAOPServices springAopServices = (SpringAOPServices) context.getBean("springAopServices");
		System.out.println(springAopServices.getWelcomeNote());
		
		// Spring context reads from properties file 
		AbstractDriverBasedDataSource dataSource = (DriverManagerDataSource) context.getBean("datasource");
		String url = dataSource.getUrl();
		String password = dataSource.getPassword();
		String userName = dataSource.getUsername();
		System.out.println("url: " + url);
		System.out.println("user name: " + userName);
		System.out.println("password: " + password);
		System.out.println("\n>>>>>>>>>>>>>>>>>PropertyPlaceholderConfigurer ENDS>>>>>>>>>>>>\n");
	}
}
