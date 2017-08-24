package com.ashish.learning.v4.event.handling;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextStartedEventHandler implements ApplicationListener<ContextStartedEvent>{

	@Override
	public void onApplicationEvent(ContextStartedEvent arg0) {
		System.out.println("\n\n ContextStartedEvent:- This event is published when the ApplicationContext is started using the start() \n"
				+ "method on the ConfigurableApplicationContext interface. You can poll your database or \n"
				+ "you can restart any stopped application after receiving this event.");
	}

}
