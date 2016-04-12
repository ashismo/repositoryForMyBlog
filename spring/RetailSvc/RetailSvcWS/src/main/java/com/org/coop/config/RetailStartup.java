package com.org.coop.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RetailStartup implements ApplicationListener<ContextRefreshedEvent> {
	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		// On application startup this function will get executed
	}
}
