package com.ashish.java8.defaultMethods;

public interface SonyPhone {
	default void makeCall() {
		System.out.println("Make call implemented by Sony");
	}
}
