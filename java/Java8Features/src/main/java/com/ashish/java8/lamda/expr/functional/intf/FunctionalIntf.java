package com.ashish.java8.lamda.expr.functional.intf;

@FunctionalInterface
public interface FunctionalIntf {
	/**
	 * Only one abstract interface is allowed in a functional interface
	 */
	public abstract void doSomeWork();
	
	/**
	 * More than one interface needs to have default implementation
	 */
	public default void doSomeWork1() {
		System.out.println("Some default implementation in functional interface");
	}
}
