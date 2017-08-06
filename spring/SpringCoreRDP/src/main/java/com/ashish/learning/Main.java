package com.ashish.learning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ashish.learning.aop.SpringAOPServices;
import com.ashish.learning.autowire.PhoneManufacturer;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		logger.info("xxxxxxxx--------LOG BACK Implementation STARTS -----------xxxxxxxxxxxxxx");
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Beans.xml");
		
		springScopeAndLifecycle(context);
		
		constructorBasedDI(context);
		
		setterMethodBasedDI(context);
		
		pNamespaceDI(context);
		
		springCollectionInjection(context);
		
		autowiring(context);
		
		springAOPExample(context);
		
		propertyPlaceholderConfigurerImplementation(context);
		
		logger.info("xxxxxxxx--------LOG BACK Implementation ENDS -----------xxxxxxxxxxxxxx");
		
	}

	/**
	 * This method implements PropertyPlaceholderConfigurer to read values from property file and 
	 * used into the application context
	 * @param context
	 */
	private static void propertyPlaceholderConfigurerImplementation(
			ApplicationContext context) {
		AbstractDriverBasedDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");
		System.out.println("\n>>>>>>>>>>>>>>>>>PropertyPlaceholderConfigurer STARTS>>>>>>>>>>>>\n");
		String url = dataSource.getUrl();
		String password = dataSource.getPassword();
		String userName = dataSource.getUsername();
		System.out.println("url: " + url);
		System.out.println("user name: " + userName);
		System.out.println("password: " + password);
		System.out.println("\n>>>>>>>>>>>>>>>>>PropertyPlaceholderConfigurer ENDS>>>>>>>>>>>>\n");
	}

	private static void springAOPExample(ApplicationContext context) {
		SpringAOPServices springAopServices = (SpringAOPServices) context.getBean("springAopServices");
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

	/**
	 * Autowiring example
	 * @param context
	 */
	private static void autowiring(ApplicationContext context) {
		PhoneManufacturer obj1 = (PhoneManufacturer) context.getBean("manufacturerByName");
		String osName = obj1.getPhone().getOsName();
		System.out.println("Autowire by name: " + osName);
		
		
		obj1 = (PhoneManufacturer) context.getBean("manufacturerByType");
		osName = obj1.getPhone().getOsName();
		System.out.println("Autowire by type: " + osName);
	}

	/**
	 * p-namespace Dependency injection
	 */
	private static void pNamespaceDI(ApplicationContext context) {
		ShowRoom obj1 = (ShowRoom) context.getBean("showRoomPNameSpace");
		System.out.println("\n>>>>>>>>>>>>>>>p-NAMESPACE BASED DI STARTS>>>>>>>>>>>>>\n");
		obj1.getCustomCar("p-namespace: Your custom car really looks great");
		System.out.println("\n>>>>>>>>>>>>>>>p-NAMESPACE BASED DI ENDS>>>>>>>>>>>>>\n");
	}

	/**
	 * Constructor based dependency injection 
	 */
	private static void constructorBasedDI(ApplicationContext context) {
		ShowRoom obj1 = (ShowRoom) context.getBean("showRoom");
		System.out.println("\n>>>>>>>>>>>>>>>CONSTRUCTOR BASED DI STARTS>>>>>>>>>>>>>\n");
		obj1.getCar();
		System.out.println("\n>>>>>>>>>>>>>>>CONSTRUCTOR BASED DI ENDS>>>>>>>>>>>>>\n");
	}
	
	/**
	 * Setter method based dependency injection 
	 */
	private static void setterMethodBasedDI(ApplicationContext context) {
		ShowRoom obj1 = (ShowRoom) context.getBean("showRoomSetter");
		System.out.println("\n>>>>>>>>>>>>>>>SETTER BASED DI STARTS>>>>>>>>>>>>>\n");
		obj1.getCar();
		System.out.println("\n>>>>>>>>>>>>>>>SETTER BASED DI ENDS>>>>>>>>>>>>>\n");
	}
	
	/**
	 * Injecting car names into list 
	 */
	private static void springCollectionInjection(ApplicationContext context) {
		ShowRoom obj1 = (ShowRoom) context.getBean("showRoomCollections");
		System.out.println("\n>>>>>>>>>>>>>>>SPRING COLLECTIN INJECTION STARTS>>>>>>>>>>>>>\n");
		System.out.println("Car List: " +obj1.getCars());
		
		System.out.println("Car Map: " + obj1.getCarsMap());
		
		// Get 2nd object
		BMWCar bmwCar = (BMWCar)obj1.getCarsMap().get("K2");
		bmwCar.prepareFourwheelers();
		
		// Get 3rd object - Inner bean
		SomeInnerBean innerBean = (SomeInnerBean)obj1.getCarsMap().get("K3");
		System.out.println("Some innerbean: " + innerBean);
		System.out.println("\n>>>>>>>>>>>>>>>SPRING COLLECTIN INJECTION ENDS>>>>>>>>>>>>>\n");
		
	}
	

	/**
	 * This method describes the
	 * 		a) scope of beans, 
	 * 		b) life cycle of beans
	 * 		c) post processor.
	 */
	private static void springScopeAndLifecycle(ApplicationContext context) {
//		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Beans.xml");
		
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
