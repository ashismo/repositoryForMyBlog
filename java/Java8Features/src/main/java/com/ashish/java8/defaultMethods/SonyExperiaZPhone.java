package com.ashish.java8.defaultMethods;

public class SonyExperiaZPhone implements SonyPhoneIntf {
	
	/**
	 * Below method overrides the makeCall() method in parent interface
	 */
	@Override
	public void makeCall() {
		System.out.println("Make call implemented by Sony. Also some new features added");
	}
}
