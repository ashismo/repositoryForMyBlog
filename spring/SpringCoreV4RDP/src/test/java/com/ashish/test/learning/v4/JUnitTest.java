package com.ashish.test.learning.v4;

import java.util.Locale;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ashish.learning.v4.HelloWorld;
import com.ashish.learning.v4.ShowRoom;
import com.ashish.learning.v4.aop.SpringAOPServices;
import com.ashish.learning.v4.config.AppConfig;
import com.ashish.learning.v4.dao.SpringJDBCServices;
import com.ashish.learning.v4.dao.UserBean;
import com.ashish.learning.v4.duplicate.beans.DuplicateBean;
import com.ashish.learning.v4.inheritance.Country;
import com.ashish.learning.v4.inheritance.India;
import com.ashish.learning.v4.qualifier.DessertService;
import com.ashish.learning.v4.service.CollectionInjectionServices;
import com.ashish.learning.v4.spring.expression.language.SpELServices;
import com.ashish.learning.v4.spring.expression.language.Wheels;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
@ActiveProfiles("devEmbedded") // Beans marked with @Profiles("dev") will get loaded and other beans also will get loaded
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
	
	@Test(expected=ClassCastException.class)
	public void propertyPlaceholderConfigurerImplementation() { 
		System.out.println("\n>>>>>>>>>>>>>>>>>PropertyPlaceholderConfigurer STARTS>>>>>>>>>>>>\n");
		// Java code reads value from properties file
		SpringAOPServices springAopServices = (SpringAOPServices) context.getBean("springAopServices");
		System.out.println(springAopServices.getWelcomeNote());
		
		// Spring context reads from properties file 
		AbstractDriverBasedDataSource dataSource = (DriverManagerDataSource) context.getBean(DataSource.class);
		String url = dataSource.getUrl();
		String password = dataSource.getPassword();
		String userName = dataSource.getUsername();
		System.out.println("url: " + url);
		System.out.println("user name: " + userName);
		System.out.println("password: " + password);
		System.out.println("\n>>>>>>>>>>>>>>>>>PropertyPlaceholderConfigurer ENDS>>>>>>>>>>>>\n");
	}
	
	
	@Test
	public void springExpressionLanguage() {
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring Expression Language STARTS>>>>>>>>>>>>\n");
		SpELServices spELServices = (SpELServices) context.getBean(SpELServices.class);
		// To upper case - ternary operation
		System.out.println("SpEL ternary operation - null check: " + spELServices.getWelcomeNote());
		
		spELServices.getShowroom().getFourWheelers().prepareFourwheelers();
		spELServices.getFourWheelers().prepareFourwheelers();
		
		System.out.println("Null value injection: " + spELServices.getNullValue());
		System.out.println("Random Speed: " + spELServices.getRandomSpeed());
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring Expression Language ENDS>>>>>>>>>>>>\n");
	}
	
	@Test
	public void collectionInjection() {
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring Collection Injection STARTS>>>>>>>>>>>>\n");
		CollectionInjectionServices coll = (CollectionInjectionServices) context.getBean(CollectionInjectionServices.class);
		System.out.println("Map injection: " + coll.getCountryCurrencyMap());
		
		System.out.println("List injection: " + coll.getCountries());
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring Collection Injection ENDS>>>>>>>>>>>>\n");
	}
	
	
	@Test
	public void resourceBundleMessageSourceImpl() {
		System.out.println("\n>>>>>>>>>>>>>>>>>ResourceBundleMessageSource STARTS>>>>>>>>>>>>\n");
		
		String message = context.getMessage("greetings", null, "Default message, If not found against the key: greetings", null);
		System.out.println("\nWelcome message for DEFAULT Locale: " + message);
		
		System.out.println("\nWelcome message for en_GB Locale");
		Locale locale = new Locale("en_GB");
		message = context.getMessage("greetings", null, "Default message, If not found against the key: greetings", locale);
		System.out.println("\nWelcome message for en_GB Locale: " + message);
		
		message = context.getMessage("offer.message", new Object[]{"Ashish","10"}, "Default message, If not found against the key: offer.message", null);
		System.out.println("\nParameterized welcome message: " + message);
		
		message = context.getMessage("offer.message", new Object[]{"Ashish","10"}, "Default message, If not found against the key: offer.message", locale);
		System.out.println("\nParameterized welcome message (This key is actually defined in the parent properties file): " + message);
		
		message = context.getMessage("service.failure", null, "Default message: Service Failed, if not found against the key: service.failure", null);
		System.out.println("\nError message : " + message);
		
		System.out.println("\n>>>>>>>>>>>>>>>>>ResourceBundleMessageSource ENDS>>>>>>>>>>>>\n");
	}
	
	
	@Test
	public void springJdbcExample() {
		SpringJDBCServices springJDBCServices = (SpringJDBCServices) context.getBean(SpringJDBCServices.class);
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring JDBC Integration STARTS>>>>>>>>>>>>\n");
		
		// This call should return result after 5 seconds as the query execution slowed down by 5 seconds intentionally to see the caching effect
		springJDBCServices.displayUserDetailsByUsername("ashish"); 
		
		// This call should return result immediately from cache
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
	 * XML configuration has been incorporated with Annotation based configuration
	 */
	@Test
	public void springXmlConfigurationInjection() {
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring XML configuration STARTS>>>>>>>>>>>>\n");
		Wheels wheel = (Wheels)context.getBean(Wheels.class);
		
		System.out.println("Wheelbase: " + wheel.getWheelbase());
		System.out.println("Wheeltype: " + wheel.getWheelType());
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring XML configuration ENDS>>>>>>>>>>>>\n");
	}
	
	/**
	 * Duplicate beans can be loaded into IoC if they are loaded from two different configuration file
	 */
	@Test
	public void duplicateBean() {
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring duplicateBean STARTS>>>>>>>>>>>>\n");
		DuplicateBean duplicateBean = (DuplicateBean)context.getBean(DuplicateBean.class);
		
		System.out.println("duplicateBean: " + duplicateBean.getName());
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring duplicateBean ENDS>>>>>>>>>>>>\n");
	}
	
	/**
	 * Inheritance of the bean
	 */
	@Test
	public void inheritanceBean() {
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring inheritanceBean STARTS>>>>>>>>>>>>\n");
//		Country country = (Country)context.getBean(Country.class);
//		System.out.println("States injected: " + country.getStates());
		
		India india = (India)context.getBean(India.class);
		india.setCountryName("India");
		india.setLanguage("Bengali");
		System.out.println("Bean inheritance: " + india);
		System.out.println("\n>>>>>>>>>>>>>>>>>Spring inheritanceBean ENDS>>>>>>>>>>>>\n");
	}
}
