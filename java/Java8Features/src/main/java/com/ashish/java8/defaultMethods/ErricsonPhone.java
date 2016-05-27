package com.ashish.java8.defaultMethods;

public interface ErricsonPhone {
	default void makeCall() {
		System.out.println("Make call implemented by Erricson");
	}
}
