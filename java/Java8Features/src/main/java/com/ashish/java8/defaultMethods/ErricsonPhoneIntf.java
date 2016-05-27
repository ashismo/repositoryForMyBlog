package com.ashish.java8.defaultMethods;

public interface ErricsonPhoneIntf {
	default void makeCall() {
		System.out.println("Make call implemented by Erricson");
	}
}
