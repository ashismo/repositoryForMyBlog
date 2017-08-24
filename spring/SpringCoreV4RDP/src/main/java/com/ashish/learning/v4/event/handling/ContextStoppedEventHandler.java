package com.ashish.learning.v4.event.handling;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextStoppedEventHandler implements ApplicationListener<ContextStoppedEvent>{

	@Override
	public void onApplicationEvent(ContextStoppedEvent event) {
		System.out.println("\n\n ContextStoppedEvent:- This event is published when the ApplicationContext is stopped using the stop() method \n"
				+ "on the ConfigurableApplicationContext interface. You can do required housekeep work after receiving this event.\n");
		
	}


}
