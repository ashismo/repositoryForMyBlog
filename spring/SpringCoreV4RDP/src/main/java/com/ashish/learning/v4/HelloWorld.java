package com.ashish.learning.v4;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component(value="helloWorld1")
public class HelloWorld {
   private String message;

   public void setMessage(String message){
      this.message  = message;
   }
   public void getMessage(){
      System.out.println("Your Message : " + message);
   }
   
   // This is equivalent to init-method configuration in Spring XML configuration
   // This runs every time the bean gets initialized. This is equivalent of default-init-method in beans
   @PostConstruct
   public void init() {
	   System.out.println("Initializing the bean");
   }
   
   // This is equivalent to destroy-method configuration in Spring XML configuration
   // This runs every time the bean gets initialized. This is equivalent of default-destroy-method in beans
   @PreDestroy
   public void destroy() {
	   System.out.println("Destroying the bean");
   }
}