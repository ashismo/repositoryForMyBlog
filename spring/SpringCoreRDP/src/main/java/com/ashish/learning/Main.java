package com.ashish.learning;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ashish.learning.aop.SpringAOPServices;
import com.ashish.learning.autowire.PhoneManufacturer;
import com.ashish.learning.expression.language.Car;
import com.ashish.learning.expression.language.Wheels;
import com.ashish.learning.jdbc.SpringJDBCServices;
import com.ashish.learning.jdbc.UserBean;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		logger.info("xxxxxxxx--------LOG BACK Implementation STARTS -----------xxxxxxxxxxxxxx");
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Beans.xml");
		
		// Below is the another way to include multiple configuration files 
		// if <import resource="Spring-Beans-Classic.xml"/> is not used in the parent configuration file
		//ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"Spring-Beans.xml", "Spring-Beans-Classic.xml"});
		
		springScopeAndLifecycle(context);
		
		constructorBasedDI(context);
		
		setterMethodBasedDI(context);
		
		pNamespaceDI(context);
		
		springCollectionInjection(context);
		
		autowiring(context);
		
		springAOPExample(context);
		
		propertyPlaceholderConfigurerImplementation(context);
		
		resourceBundleMessageSourceImpl(context);
		
		springJdbcExample(context);
		
		transactionManagementCommit(context);
		
		transactionManagementRollback(context);
		
		springExpressionLanguageExample(context);
		logger.info("xxxxxxxx--------LOG BACK Implementation ENDS -----------xxxxxxxxxxxxxx");
		
	}

	/**
	 * Spring Expression Language example
	 * @param context
	 */
	private static void springExpressionLanguageExample(
			ApplicationContext context) {
		
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring Expression Language STARTS>>>>>>>>>>>>\n");
		Car cars = (Car) context.getBean("cars");
		System.out.println("Spring Expression Language: wheelbase: " + cars.getWheels().getWheelbase() + " and wheel type: " + cars.getWheels().getWheelType());
		System.out.println("Spring Expression Language: Populated wheel type in car class: " + cars.getWheelType());
		System.out.println("Spring Expression Language: Populated car make from properties file: " + cars.getMake());
		System.out.println("Spring Expression Language: Random speed: " + cars.getRandomSpeed());
		System.out.println("Spring Expression Language: Car Fuel: " + cars.getCarFuel().fuelType());
		
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring Expression Language ENDS>>>>>>>>>>>>\n");
	}

	/**
	 * This method integrates spring jdbc
	 * @param context
	 */
	private static void springJdbcExample(ApplicationContext context) {
		SpringJDBCServices springJDBCServices = (SpringJDBCServices) context.getBean("springJDBCServices");
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring JDBC Integration STARTS>>>>>>>>>>>>\n");
		springJDBCServices.displayUserDetailsByUsername("ashish");
		
		UserBean ub = new UserBean();
		ub.setUsername("ujan");
		ub.setName("Ujan");
		ub.setPassword("ujan");
		ub.setRoleId(1);
		springJDBCServices.createUser(ub);
		
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring JDBC Integration ENDS>>>>>>>>>>>>\n");
	}
	
	/**
	 * Spring JDBC transaction management - rollback
	 * @param context
	 */
	private static void transactionManagementRollback(ApplicationContext context) {
		SpringJDBCServices springJDBCServices = (SpringJDBCServices) context.getBean("springJDBCServices");
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring JDBC Transaction Management STARTS>>>>>>>>>>>>\n");
		try {
			UserBean ub = new UserBean();
			ub.setUsername("ujan1-rollback");
			ub.setName("Ujan1");
			ub.setPassword("ujan1");
			ub.setRoleId(1);
			
			UserBean ub1 = new UserBean();
			ub1.setUsername("ujan2-rollback");
			ub1.setName("Ujan2");
			ub1.setPassword("ujan2");
			ub1.setRoleId(1);
			
			springJDBCServices.transactionManagement(ub, ub1, true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		springJDBCServices.displayUserDetailsByUsername("ujan1-rollback");
		
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring JDBC Transaction Management ENDS>>>>>>>>>>>>\n");
	}
	
	/**
	 * Spring JDBC transaction management - Commit success
	 * @param context
	 */
	private static void transactionManagementCommit(ApplicationContext context) {
		SpringJDBCServices springJDBCServices = (SpringJDBCServices) context.getBean("springJDBCServices");
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring JDBC Transaction Management STARTS>>>>>>>>>>>>\n");
		try {
			UserBean ub = new UserBean();
			ub.setUsername("ujan1-commit");
			ub.setName("Ujan1");
			ub.setPassword("ujan1");
			ub.setRoleId(1);
			
			UserBean ub1 = new UserBean();
			ub1.setUsername("ujan2-commit");
			ub1.setName("Ujan2");
			ub1.setPassword("ujan2");
			ub1.setRoleId(1);
			
			springJDBCServices.transactionManagement(ub, ub1, false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		springJDBCServices.displayUserDetailsByUsername("ujan1-commit");
		
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring JDBC Transaction Management ENDS>>>>>>>>>>>>\n");
	}

	/**
	 * This method implements ResourceBundleMessageSource to read values from properties file.
	 * Parameterize message can be defined in the properties file
	 * ResourceBundleMessageSource is mainly used in internationalization (i18n) implementation
	 * @param context
	 */
	private static void resourceBundleMessageSourceImpl(
			ApplicationContext context) {
		System.out.println("\n>>>>>>>>>>>>>>>>>ResourceBundleMessageSource STARTS>>>>>>>>>>>>\n");
		
		String message = context.getMessage("greetings", null, "Default message, If not found against the key: greetings", null);
		System.out.println("\nWelcome message for DEFAULT Locale: " + message);
		
		System.out.println("\nWelcome message for en_GB Locale");
		Locale l = new Locale("en_GB");
		message = context.getMessage("greetings", null, "Default message, If not found against the key: greetings", l);
		System.out.println("\nWelcome message for en_GB Locale: " + message);
		
		message = context.getMessage("offer.message", new Object[]{"Ashish","10"}, "Default message, If not found against the key: offer.message", null);
		System.out.println("\nParameterized welcome message: " + message);
		
		message = context.getMessage("offer.message", new Object[]{"Ashish","10"}, "Default message, If not found against the key: offer.message", l);
		System.out.println("\nParameterized welcome message (This key is actually defined in the parent properties file): " + message);
		System.out.println("\n>>>>>>>>>>>>>>>>>ResourceBundleMessageSource ENDS>>>>>>>>>>>>\n");
	}

	/**
	 * This method implements PropertyPlaceholderConfigurer to read values from property file and 
	 * used into the application context or inside the code
	 * @param context
	 */
	private static void propertyPlaceholderConfigurerImplementation(
			ApplicationContext context) { 
		System.out.println("\n>>>>>>>>>>>>>>>>>PropertyPlaceholderConfigurer STARTS>>>>>>>>>>>>\n");
		// Java code reads value from properties file
		SpringAOPServices springAopServices = (SpringAOPServices) context.getBean("springAopServices");
		System.out.println(springAopServices.getWelcomeNote());
		
		// Spring context reads from properties file 
		AbstractDriverBasedDataSource dataSource = (DriverManagerDataSource) context.getBean("dataSource");
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
