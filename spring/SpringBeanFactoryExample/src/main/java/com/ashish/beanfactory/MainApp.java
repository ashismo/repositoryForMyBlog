package com.ashish.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.ashish.bean.HelloWorld;

public class MainApp {
	public static void main(String args[]) {
		Resource resource = new ClassPathResource("beans.xml");  
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		
		HelloWorld helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
		
		System.out.println(helloWorld.getMessage());
	}
}