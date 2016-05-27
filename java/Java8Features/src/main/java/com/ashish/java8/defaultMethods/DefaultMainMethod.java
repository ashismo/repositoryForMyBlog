package com.ashish.java8.defaultMethods;

public class DefaultMainMethod {
	public static void main(String args[]) {
		SonyErricsonPhone sonyErricsonPhone = new SonyErricsonPhone();
		sonyErricsonPhone.makeCall();
		
		SonyExperiaCPhone sonyExperiaCPhone = new SonyExperiaCPhone();
		sonyExperiaCPhone.makeCall();
		
		SonyExperiaZPhone sonyExperiaZPhone = new SonyExperiaZPhone();
		sonyExperiaZPhone.makeCall();
	}
}
