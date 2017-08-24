package com.ashish.learning.v4.event.handling;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedEventHandler implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("\n\n ContextRefreshedEvent:- This event is published when the ApplicationContext is either initialized or refreshed. \n"
				+ "This can also be raised using the refresh() method on the ConfigurableApplicationContext interface.\n");
		
	}

	


}
