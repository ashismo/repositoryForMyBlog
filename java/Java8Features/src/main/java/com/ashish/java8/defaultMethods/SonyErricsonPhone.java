package com.ashish.java8.defaultMethods;

public class SonyErricsonPhone implements SonyPhone, ErricsonPhone {
	
	/**
	 * This method breaks the java diamond problem at the time of multiple inheritance.
	 * This method clearly specifies the which version of makeCall() method to be taken
	 */
	public void makeCall() {
		ErricsonPhone.super.makeCall();
	}
}
