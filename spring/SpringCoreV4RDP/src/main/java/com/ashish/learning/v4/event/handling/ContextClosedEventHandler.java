package com.ashish.learning.v4.event.handling;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextClosedEventHandler implements ApplicationListener<ContextClosedEvent>{

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println("\n\nContextClosedEvent:- This event is published when the ApplicationContext is closed using the close() method \n"
				+ "on the ConfigurableApplicationContext interface. A closed context reaches its end of life; \n"
				+ "it cannot be refreshed or restarted.\n");
		
	}



}
