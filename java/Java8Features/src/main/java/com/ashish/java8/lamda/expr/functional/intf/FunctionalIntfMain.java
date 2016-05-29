package com.ashish.java8.lamda.expr.functional.intf;

public class FunctionalIntfMain {

	public static void main(String[] args) {
		// **************************************
		// Traditional approach - Anonymous class
		// **************************************
		SomeImplClass someImplClass = new SomeImplClass();
		someImplClass.execute(new FunctionalIntf() {

			@Override
			public void doSomeWork() {
				System.out.println("Traditional approach: Do some work using anonymous class");
			}
		});

		// **************************************
		// Java8 approach - Using Lamda Expression
		// **************************************
		someImplClass.execute(() -> {
			System.out.println("Java8: Do some work using Lamda Expression");
		});
	}

}
